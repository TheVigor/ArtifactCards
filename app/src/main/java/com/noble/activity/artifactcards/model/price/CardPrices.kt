package com.noble.activity.artifactcards.model.price

import com.google.gson.annotations.SerializedName

data class CardPrices constructor(

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("start")
    val start: Int,

    @SerializedName("pagesize")
    val pagesize: Int,

    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("results")
    val results: List<CardPrice>


)