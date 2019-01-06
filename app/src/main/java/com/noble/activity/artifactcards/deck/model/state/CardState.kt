package com.noble.activity.artifactcards.deck.model.state

data class CardState constructor(
    var result: Boolean,
    var indexStart: Int,
    var prevCardBase: Int,
    var outCount: Int,
    var outCardId: Int)