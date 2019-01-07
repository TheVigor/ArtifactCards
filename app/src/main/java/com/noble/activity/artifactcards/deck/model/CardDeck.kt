package com.noble.activity.artifactcards.deck.model

import com.noble.activity.artifactcards.model.card.Card

data class CardDeck constructor(
    var name: String,
    var heroes: List<Card>,
    var cards: List<Card>)