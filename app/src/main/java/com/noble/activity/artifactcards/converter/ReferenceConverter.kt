package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.card.Reference
import com.noble.activity.artifactcards.utils.SEP

class ReferenceConverter {

    @TypeConverter
    fun fromRefs(refs: List<Reference>): String {
        return refs.joinToString(transform = {it.cardId.toString()}, separator = SEP)
    }

    @TypeConverter
    fun toRefs(data: String): List<Reference> {
        if (data.isNullOrEmpty())
            return listOf()
        return data.split(SEP).map { Reference(it.toInt(), "", 0) }
    }

}