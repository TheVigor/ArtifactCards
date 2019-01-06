package com.noble.activity.artifactcards.deck.model

data class Deck constructor(var name: String, var heroes: List<HeroRef>, var cards: List<CardRef>)