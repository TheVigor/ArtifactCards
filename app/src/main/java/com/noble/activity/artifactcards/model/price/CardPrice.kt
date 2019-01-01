package com.noble.activity.artifactcards.model.price

import com.google.gson.annotations.SerializedName

data class CardPrice constructor(

    @SerializedName("name")
    val name: String,

    @SerializedName("hash_name")
    val hashName: String,

    @SerializedName("sell_listings")
    val sellListings: Int,

    @SerializedName("sell_price")
    val sellPrice: Int,

    @SerializedName("sell_price_text")
    val sellPriceText: String,

    @SerializedName("app_icon")
    val appIcon: String,

    @SerializedName("app_name")
    val appName: String

)