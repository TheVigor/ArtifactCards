package com.noble.activity.artifactcards.model.card

import com.google.gson.annotations.SerializedName

data class CardSet constructor(
    @SerializedName("version")
    var version: String,

    @SerializedName("set_info")
    var setInfo: SetInfo,

    @SerializedName("card_list")
    var cardList: List<Card>

)