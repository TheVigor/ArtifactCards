package com.noble.activity.artifactcards.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "cards")
data class Card constructor(
    @NonNull
    @PrimaryKey
    var id: String,
    var title: String = "",
    @Embedded(prefix = "cover_url")
    var cover_url: CoverUrl,
    var description: String = ""
)