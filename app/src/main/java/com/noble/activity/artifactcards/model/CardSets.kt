package com.noble.activity.artifactcards.model

import com.google.gson.annotations.SerializedName

data class CardSets constructor(
    @SerializedName("card_set")
    val cardSet: CardSet
)