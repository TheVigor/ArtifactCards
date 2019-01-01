package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.card.CardName
import com.noble.activity.artifactcards.utils.SEP

class CardNameConverter {

    @TypeConverter
    fun fromCardName(cardName: CardName): String {
        val en = if (cardName.english.isNullOrEmpty()) "" else cardName.english
        val ru = if (cardName.russian.isNullOrEmpty()) "" else cardName.russian

        return "$en$SEP$ru"
    }

    @TypeConverter
    fun toCardName(data: String): CardName {
        if (data.isNullOrEmpty())
            return CardName("", "")

        val langs = data.split(SEP)

        if (langs.isEmpty()) {
            return CardName("", "")
        }

        if (langs.size == 1) {
            return CardName(langs[0], "")
        }

        if (langs.size >= 2) {
            return CardName(langs[0], langs[1])
        }

        return CardName("", "")
    }

}