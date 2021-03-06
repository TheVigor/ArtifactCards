package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.card.MiniImage

class MiniImageConverter {

    @TypeConverter
    fun fromMini(mini: MiniImage): String {

        if (mini.default == null) {
            return ""
        }

        return mini.default.toString()
    }

    @TypeConverter
    fun toMini(data: String): MiniImage {

        if (data.isNullOrEmpty()) {
            return MiniImage("")
        }

        return MiniImage(data)
    }

}