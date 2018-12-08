package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.IngameImage
import com.noble.activity.artifactcards.utils.SEP

class IngameImageConverter {
    @TypeConverter
    fun fromIngameImage(ingameImage: IngameImage): String {
        val en = if (ingameImage.default.isNullOrEmpty()) "" else ingameImage.default
        val ru = if (ingameImage.russian.isNullOrEmpty()) "" else ingameImage.russian

        return "$en$SEP$ru"
    }

    @TypeConverter
    fun toIngameImage(data: String): IngameImage{
        if (data.isNullOrEmpty())
            return IngameImage("", "")

        val langs = data.split(SEP)

        if (langs.isEmpty())
            return IngameImage("", "")

        if (langs.size == 1)
            return IngameImage(langs[0], "")

        if (langs.size >= 2)
            return IngameImage(langs[0], langs[1])

        return IngameImage("", "")
    }
}
