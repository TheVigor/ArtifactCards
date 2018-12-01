package com.noble.activity.artifactcards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CardSetInfo constructor(

    @SerializedName("cdn_root")
    @Expose
    var cdn_root: String = "",

    @SerializedName("url")
    @Expose
    var url: String = "",

    @SerializedName("expire_time")
    @Expose
    var expire_time: Long = 0L
)