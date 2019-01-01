package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.card.CardText
import com.noble.activity.artifactcards.utils.SEP

class CardTextConverter {
    @TypeConverter
    fun fromCardText(cardText: CardText): String {
        val en = if (cardText.english.isNullOrEmpty()) "" else cardText.english
        val ru = if (cardText.russian.isNullOrEmpty()) "" else cardText.russian

        return "$en$SEP$ru"
    }

    @TypeConverter
    fun toCardText(data: String): CardText {
        if (data.isNullOrEmpty())
            return CardText("", "")

        val langs = data.split(SEP)

        if (langs.isEmpty()) {
            return CardText("", "")
        }

        if (langs.size == 1) {
            return CardText(langs[0], "")
        }

        if (langs.size >= 2) {
            return CardText(langs[0], langs[1])
        }

        return CardText(english = "", russian = "")
    }
}