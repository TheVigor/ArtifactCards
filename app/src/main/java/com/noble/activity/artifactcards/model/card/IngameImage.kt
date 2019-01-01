package com.noble.activity.artifactcards.model.card

import com.google.gson.annotations.SerializedName

data class IngameImage constructor(

    @SerializedName("default")
    var default: String? = "",

    @SerializedName("russian")
    var russian: String? = ""

)