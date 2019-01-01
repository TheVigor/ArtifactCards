package com.noble.activity.artifactcards.model.card

import com.google.gson.annotations.SerializedName

data class MiniImage constructor(

    @SerializedName("default")
    var default: String? = ""
)