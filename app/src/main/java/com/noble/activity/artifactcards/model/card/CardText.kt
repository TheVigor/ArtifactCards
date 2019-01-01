package com.noble.activity.artifactcards.model.card

import com.google.gson.annotations.SerializedName

data class CardText constructor(

    @SerializedName("english")
    var english: String? = "",

    @SerializedName("russian")
    var russian: String? = ""

)