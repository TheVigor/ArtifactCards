package com.noble.activity.artifactcards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngameImage constructor(
    @SerializedName("default")
    @Expose
    var default: String? = "",
    @SerializedName("german")
    @Expose
    var german: String? = "",
    @SerializedName("french")
    @Expose
    var french: String? = "",
    @SerializedName("italian")
    @Expose
    var italian: String? = "",
    @SerializedName("koreana")
    @Expose
    var koreana: String? = "",
    @SerializedName("spanish")
    @Expose
    var spanish: String? = "",
    @SerializedName("schinese")
    @Expose
    var schinese: String? = "",
    @SerializedName("tchinese")
    @Expose
    var tchinese: String? = "",
    @SerializedName("russian")
    @Expose
    var russian: String? = "",
    @SerializedName("japanese")
    @Expose
    var japanese: String? = "",
    @SerializedName("brazilian")
    @Expose
    var brazilian: String? = "",
    @SerializedName("latam")
    @Expose
    var latam: String? = ""
)