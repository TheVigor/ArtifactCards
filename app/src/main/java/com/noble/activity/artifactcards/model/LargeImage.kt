package com.noble.activity.artifactcards.model

import com.google.gson.annotations.SerializedName

data class LargeImage constructor(

    @SerializedName("default")
    var default: String? = "",

    @SerializedName("german")
    var german: String? = "",

    @SerializedName("french")
    var french: String? = "",

    @SerializedName("italian")
    var italian: String? = "",

    @SerializedName("koreana")
    var koreana: String? = "",

    @SerializedName("spanish")
    var spanish: String? = "",

    @SerializedName("schinese")
    var schinese: String? = "",

    @SerializedName("tchinese")
    var tchinese: String? = "",

    @SerializedName("russian")
    var russian: String? = "",

    @SerializedName("japanese")
    var japanese: String? = "",

    @SerializedName("brazilian")
    var brazilian: String? = "",

    @SerializedName("latam")
    var latam: String? = ""
)