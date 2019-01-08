package com.noble.activity.artifactcards.deck.coder

import android.util.Base64
import com.noble.activity.artifactcards.deck.model.CardRef
import com.noble.activity.artifactcards.deck.model.Deck
import com.noble.activity.artifactcards.deck.model.HeroRef
import com.noble.activity.artifactcards.deck.model.state.CardState
import com.noble.activity.artifactcards.deck.model.state.Chunk
import com.noble.activity.artifactcards.deck.model.state.CoderState
import java.lang.Exception

class ArtifactDeckDecoder {

    companion object {
        private const val currentVersion = 2
        const val encodePrefix = "ADC"
    }

    fun decode(deckCode: String): Deck {
        val deckBytes = decodeDeckString(deckCode)
        return parseDeckInternal(deckBytes)
    }

    private fun decodeDeckString(deckCode: String): IntArray {
        if (deckCode.substring(0, encodePrefix.length) != encodePrefix) {
            throw Exception("Artifact Deck Code prefix missing!")
        }

        var stripDeckCode = deckCode.substring(encodePrefix.length)
        stripDeckCode = stripDeckCode.replace('-', '/').replace('_', '=')

        val decoded = Base64.decode(stripDeckCode, Base64.DEFAULT)
        return decoded.map { it.toInt() and 0xFF }.toIntArray()
    }

    private fun readBitsChunk(chunk: Int, numBits: Int, currShift: Int, outBits: Int): Chunk {
        val continueBit = 1 shl numBits
        val newBits = chunk and (continueBit - 1)

        val bits = outBits or (newBits shl currShift)
        val result = (chunk and continueBit) != 0

        return Chunk(result = result, bits = bits)
    }

    private fun readVarEncodedUint32(
        baseValue: Int, baseBits: Int, data: IntArray,
        indexStart: Int, indexEnd: Int, outValue: Int
    ): CoderState {
        var deltaShift = 0

        var updatedIndexStart = indexStart

        var chunk = readBitsChunk(baseValue, baseBits, deltaShift, outValue)

        if ((baseBits == 0) || chunk.result) {
            deltaShift += baseBits

            while (true) {
                if (indexStart > indexEnd)
                    return CoderState(
                        result = false,
                        indexStart = updatedIndexStart,
                        outValue = chunk.bits
                    )

                val nextByte = data[updatedIndexStart++]

                chunk = readBitsChunk(nextByte, 7, deltaShift, chunk.bits)
                if (!chunk.result) {
                    break
                }

                deltaShift += 7
            }
        }

        return CoderState(
            result = true,
            indexStart = updatedIndexStart,
            outValue = chunk.bits
        )
    }

    private fun readSerializedCard(
        data: IntArray, indexStart: Int, indexEnd: Int,
        prevCardBase: Int, outCount: Int, outCardId: Int
    ): CardState {
        if (indexStart > indexEnd) {
            return CardState(
                result = false,
                indexStart = indexStart,
                prevCardBase = prevCardBase,
                outCount = outCount,
                outCardId = outCardId
            )
        }

        var updatedIndexStart = indexStart

        val header = data[updatedIndexStart++]
        val hasExtendedCount = ((header shr 6) == 0x03)

        var cardDelta = 0

        val chunk = readVarEncodedUint32(header, 5, data, updatedIndexStart, indexEnd, cardDelta)

        cardDelta = chunk.outValue

        if (!chunk.result) {
            return CardState(
                result = false,
                indexStart = chunk.indexStart,
                prevCardBase = prevCardBase,
                outCount = outCount,
                outCardId = outCardId
            )

        }

        val updatedOutCardId = prevCardBase + cardDelta
        var updatedOutCount = outCount

        if (hasExtendedCount) {
            val chunk = readVarEncodedUint32(0, 0, data, chunk.indexStart, indexEnd, outCount)

            if (!chunk.result) {
                return CardState(
                    result = false,
                    indexStart = chunk.indexStart,
                    prevCardBase = prevCardBase,
                    outCount = outCount,
                    outCardId = updatedOutCardId
                )
            }
        } else {
            updatedOutCount = (header shr 6) + 1
        }

        return CardState(
            result = true,
            indexStart = chunk.indexStart,
            prevCardBase = updatedOutCardId,
            outCount = updatedOutCount,
            outCardId = updatedOutCardId
        )
    }

    private fun parseDeckInternal(deckBytes: IntArray): Deck {
        var currentByteIndex = 0
        val totalBytes = deckBytes.size

        //check version num
        val versionAndHeroes = deckBytes[currentByteIndex++]
        val version = versionAndHeroes shr 4
        if (currentVersion != version && version != 1) {
            throw Exception("Invalid code version")
        }

        val checksum = deckBytes[currentByteIndex++]

        var stringLength = 0
        if (version > 1) {
            stringLength = deckBytes[currentByteIndex++]
        }

        val totalCardBytes = totalBytes - stringLength

        var computedChecksum = 0
        for (i in currentByteIndex until totalCardBytes) {
            computedChecksum += deckBytes[i]
        }

        val masked: Int = computedChecksum and 0xFF
        if (checksum != masked) {
            throw Exception("checksum does not match")
        }

        var numHeroes = 0
        val chunk = readVarEncodedUint32(versionAndHeroes, 3, deckBytes, currentByteIndex, totalCardBytes, numHeroes)

        numHeroes = chunk.outValue

        if (!chunk.result) {
            throw Exception("Missing hero count")
        }

        val heroes = mutableListOf<HeroRef>()
        var heroCard = CardState(false, chunk.indexStart, 0, 0, 0)

        for (currHero in 0 until numHeroes) {
            heroCard.outCount = 0
            heroCard.outCardId = 0

            heroCard = readSerializedCard(
                deckBytes, heroCard.indexStart, totalCardBytes,
                heroCard.prevCardBase, heroCard.outCount, heroCard.outCardId
            )

            if (!heroCard.result) {
                throw Exception("Missing hero data")
            }

            heroes.add(
                HeroRef(
                    id = heroCard.outCardId,
                    turn = heroCard.outCount
                )
            )
        }

        val cards = mutableListOf<CardRef>()
        var notHeroCard =
            CardState(false, heroCard.indexStart, 0, 0, 0)

        while (notHeroCard.indexStart < totalCardBytes) {
            notHeroCard.outCount = 0
            notHeroCard.outCardId = 0

            notHeroCard = readSerializedCard(
                deckBytes, notHeroCard.indexStart, totalBytes,
                notHeroCard.prevCardBase, notHeroCard.outCount, notHeroCard.outCardId
            )

            if (!notHeroCard.result) {
                throw Exception("Missing card data")
            }

            cards.add(
                CardRef(
                    id = notHeroCard.outCardId,
                    count = notHeroCard.outCount
                )
            )
        }

        var name = ""
        if (notHeroCard.indexStart < totalBytes) {
            val bytes = deckBytes
                .drop(deckBytes.size - stringLength)
                .map { it.toByte() }
                .toByteArray()

            name = bytes.toString(Charsets.UTF_8)
        }

        return Deck(heroes = heroes, cards = cards, name = name)
    }
}
