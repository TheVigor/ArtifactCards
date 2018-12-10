package com.noble.activity.artifactcards.model

import com.google.gson.annotations.SerializedName

data class MiniImage constructor(

    @SerializedName("default")
    var default: String? = ""
)