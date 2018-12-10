package com.noble.activity.artifactcards.model

import com.google.gson.annotations.SerializedName

data class CardSetInfo constructor(

    @SerializedName("cdn_root")
    var cdn_root: String = "",

    @SerializedName("url")
    var url: String = "",

    @SerializedName("expire_time")
    var expire_time: Long = 0L
)