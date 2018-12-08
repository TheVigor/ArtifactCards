package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.LargeImage
import com.noble.activity.artifactcards.utils.SEP

class LargeImageConverter {
    @TypeConverter
    fun fromLargeImage(largeImage: LargeImage): String {
        val en = if (largeImage.default.isNullOrEmpty()) "" else largeImage.default
        val ru = if (largeImage.russian.isNullOrEmpty()) "" else largeImage.russian

        return "$en$SEP$ru"
    }

    @TypeConverter
    fun toLargeImage(data: String): LargeImage{
        if (data.isNullOrEmpty())
            return LargeImage("", "")

        val langs = data.split(SEP)

        if (langs.isEmpty())
            return LargeImage("", "")

        if (langs.size == 1)
            return LargeImage(langs[0], "")

        if (langs.size >= 2)
            return LargeImage(langs[0], langs[1])

        return LargeImage("", "")
    }
}