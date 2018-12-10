package com.noble.activity.artifactcards.artifact

import android.media.AudioRecord.MetricsConstants.SOURCE
import android.support.annotation.IntDef
import com.noble.activity.artifactcards.model.Card


class RequestStatus {

    var refreshStatus: Int = 0

    var data: List<Card> = listOf()

    companion object {
        val REFRESH = 1000
        val LOAD_MORE = 1001
    }
}