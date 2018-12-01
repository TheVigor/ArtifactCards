package com.noble.activity.artifactcards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CardSet constructor(
    @SerializedName("version")
    @Expose
    var version: String,

    @SerializedName("set_info")
    @Expose
    var setInfo: SetInfo,

    @SerializedName("card_list")
    @Expose
    var cardList: List<Card>

)