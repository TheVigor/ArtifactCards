package com.noble.activity.artifactcards.converter

import android.arch.persistence.room.TypeConverter
import com.noble.activity.artifactcards.model.Reference

class ReferenceConverter {

    @TypeConverter
    fun fromRefs(refs: List<Reference>): String {
        return ""
    }

    @TypeConverter
    fun toRefs(data: String): List<Reference> {
        return listOf()
    }

}