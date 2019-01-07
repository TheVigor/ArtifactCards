package com.noble.activity.artifactcards.deck.model

import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.ITEM_CARD_TYPE

data class CardDeck constructor(
    var name: String,
    var heroes: List<Card>,
    var cards: List<Card>,
    var mapCountTurn: MutableMap<Int, Int>) {

    fun getCountCards(): String {
        var sum = 0
        cards.forEach {
            if (it.cardType != ITEM_CARD_TYPE) {
                sum += mapCountTurn[it.cardId]!!
            }
        }
        return sum.toString()
    }

    fun getCountItems(): String {
        var sum = 0
        cards.forEach {
            if (it.cardType == ITEM_CARD_TYPE) {
                sum += mapCountTurn[it.cardId]!!
            }
        }
        return sum.toString()
    }

}