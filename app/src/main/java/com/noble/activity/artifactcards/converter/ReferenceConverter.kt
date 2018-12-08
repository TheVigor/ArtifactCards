package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.Reference
import com.noble.activity.artifactcards.utils.SEP

class ReferenceConverter {

    @TypeConverter
    fun fromRefs(refs: List<Reference>): String {
        val res = refs.joinToString(transform = {it.cardId.toString()}, separator = SEP)
        return res
    }

    @TypeConverter
    fun toRefs(data: String): List<Reference> {
        val res = data.split(SEP).map { Reference(it.toInt(), "", 0) }
        return res
    }

}