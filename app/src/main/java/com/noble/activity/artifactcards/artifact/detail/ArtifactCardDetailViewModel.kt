package com.noble.activity.artifactcards.artifact.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.LoadStatus
import com.noble.activity.artifactcards.utils.RequestStatus
import com.noble.activity.artifactcards.utils.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArtifactCardDetailViewModel(
    artifactRepository: ArtifactRepository,
    cardId: String,
    cardName: String,
    firstRefId: String,
    secondRefId: String,
    thirdRefId: String,
    loc: String
) : ViewModel() {
    companion object {
        private const val STEAM_COMMUNITY_URL =
            "https://steamcommunity.com/market/search/render/?appid=583950&norender=1&query="
    }

    val cardName: String = cardName
    val card: LiveData<Card> = artifactRepository.getCardById(cardId)
    val locale: String = loc

    val firstRefCard: LiveData<Card> = artifactRepository.getCardById(firstRefId)
    val secondRefCard: LiveData<Card> = artifactRepository.getCardById(secondRefId)
    val thirdRefCard: LiveData<Card> = artifactRepository.getCardById(thirdRefId)

    val loadStatusLiveData: MutableLiveData<LoadStatus> = MutableLiveData()
    val priceLiveData: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getCardPrice() {
        loadStatusLiveData.value = LoadStatus.LOADING

        val url = STEAM_COMMUNITY_URL + cardName

        ArtifactRepository.get().getRemoteCardPrice(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ cardPrices ->
                if (cardPrices.totalCount != 1) {
                    priceLiveData.value = "N/A"
                } else {
                    priceLiveData.value = cardPrices.results.first().sellPriceText
                }
                loadStatusLiveData.value = LoadStatus.LOADED
            },
            { error ->
                priceLiveData.value = "N/A"
                loadStatusLiveData.value = LoadStatus.LOADED
                app.showToast("XXX")
            })
    }

}