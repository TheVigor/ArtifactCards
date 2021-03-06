package com.noble.activity.artifactcards.deck.model

data class Deck constructor(var name: String, var heroes: List<HeroRef>, var cards: List<CardRef>) {
    fun getIds(): List<Int> {
        val result = mutableListOf<Int>()

        heroes.forEach { result.add(it.id) }
        cards.forEach { result.add(it.id) }

        return result
    }

    fun getMap(): MutableMap<Int, Int> {
        val result = mutableMapOf<Int, Int>()

        heroes.forEach { result[it.id] = it.turn }
        cards.forEach { result[it.id] = it.count }

        return result
    }

}