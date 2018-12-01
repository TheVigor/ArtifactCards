package com.noble.activity.artifactcards.model

import com.google.gson.annotations.SerializedName

data class Reference constructor(
    @SerializedName("card_id")
    val cardId: Int,

    @SerializedName("ref_type")
    val refType: String,

    @SerializedName("count")
    val count: Int
)