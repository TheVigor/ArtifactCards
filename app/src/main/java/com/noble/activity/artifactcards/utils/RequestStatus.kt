package com.noble.activity.artifactcards.utils

import com.noble.activity.artifactcards.model.card.Card

class RequestStatus {

    var refreshStatus: Int = 0

    var data: List<Card> = listOf()

    companion object {
        val REFRESH = 1000
        val LOAD_MORE = 1001
    }
}