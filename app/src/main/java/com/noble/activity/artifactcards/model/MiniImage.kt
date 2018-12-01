package com.noble.activity.artifactcards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MiniImage constructor(
    @SerializedName("default")
    @Expose
    var default: String? = ""
)