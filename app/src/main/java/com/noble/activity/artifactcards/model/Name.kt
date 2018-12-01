package com.noble.activity.artifactcards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Name constructor(

    @SerializedName("english")
    @Expose
    var english: String,
    @SerializedName("german")
    @Expose
    var german: String,
    @SerializedName("french")
    @Expose
    var french: String,
    @SerializedName("italian")
    @Expose
    var italian: String,
    @SerializedName("koreana")
    @Expose
    var koreana: String,
    @SerializedName("spanish")
    @Expose
    var spanish: String,
    @SerializedName("schinese")
    @Expose
    var schinese: String,
    @SerializedName("tchinese")
    @Expose
    var tchinese: String,
    @SerializedName("russian")
    @Expose
    var russian: String,
    @SerializedName("thai")
    @Expose
    var thai: String,
    @SerializedName("japanese")
    @Expose
    var japanese: String,
    @SerializedName("portuguese")
    @Expose
    var portuguese: String,
    @SerializedName("polish")
    @Expose
    var polish: String,
    @SerializedName("danish")
    @Expose
    var danish: String,
    @SerializedName("dutch")
    @Expose
    var dutch: String,
    @SerializedName("finnish")
    @Expose
    var finnish: String,
    @SerializedName("norwegian")
    @Expose
    var norwegian: String,
    @SerializedName("swedish")
    @Expose
    var swedish: String,
    @SerializedName("hungarian")
    @Expose
    var hungarian: String,
    @SerializedName("czech")
    @Expose
    var czech: String,
    @SerializedName("romanian")
    @Expose
    var romanian: String,
    @SerializedName("turkish")
    @Expose
    var turkish: String,
    @SerializedName("brazilian")
    @Expose
    var brazilian: String,
    @SerializedName("bulgarian")
    @Expose
    var bulgarian: String,
    @SerializedName("greek")
    @Expose
    var greek: String,
    @SerializedName("ukrainian")
    @Expose
    var ukrainian: String,
    @SerializedName("latam")
    @Expose
    var latam: String,
    @SerializedName("vietnamese")
    @Expose
    var vietnamese: String
)