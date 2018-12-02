package com.noble.activity.artifactcards.artifact

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.Log
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.model.*
import com.ruzhan.lion.model.LoadStatus
import com.ruzhan.lion.model.RequestStatus
import com.ruzhan.lion.rx.Subscriber
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class ArtifactCardViewModel(app: Application) : AndroidViewModel(app) {

    private val requestStatus: RequestStatus<List<Card>> = RequestStatus()

    val loadStatusLiveData: MutableLiveData<LoadStatus> = MutableLiveData()
    val requestStatusLiveData: MutableLiveData<RequestStatus<List<Card>>> = MutableLiveData()

    private var disposable: Disposable? = null

    init {
        requestStatusLiveData.value = null
    }

    fun getAllCards(type: String) {
        loadStatusLiveData.value = LoadStatus.LOADING
        getAllCardsFromRemote()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                cardSets ->
                loadStatusLiveData.value = LoadStatus.LOADED
                requestStatus.data = cardSets.cardSet.cardList.filter { it.cardType == type }
                requestStatusLiveData.value = requestStatus
                cardSets.cardSet.cardList?.let { saveArtifactCardsToLocalDb(it) }
            },
            {
                    error ->
                loadStatusLiveData.value = LoadStatus.LOADED
                Log.d("Шляпа", "Ошибка")
            })
    }


    fun getAllCardsFromRemote(): Single<CardSets> {
        return Single.zip(
            getCardSet("00"),
            getCardSet("01"),
            BiFunction<CardSets, CardSets, CardSets>
            {
                first, second ->
                    val result = arrayListOf<Card>()
                    result.addAll(first.cardSet.cardList)
                    result.addAll(second.cardSet.cardList)
                    CardSets(
                        CardSet(
                            "1", SetInfo(
                                1, 1,
                                Name("CardSet", "CardSet")),
                            result.toList())
                    )

            }
        )

    }

    fun getCardSet(id: String): Single<CardSets> {
        return ArtifactRepository.get().getRemoteCardSetInfo(id)
            .flatMap { cardSetInfo -> mapCardSetInfoToCardSet(cardSetInfo.cdn_root + cardSetInfo.url) }
    }

    fun mapCardSetInfoToCardSet(url: String): Single<CardSets> {
        return ArtifactRepository.get().getRemoteCardSet(url)
    }

    fun loadLocalArtifactCards(type: String) {
        if (requestStatusLiveData.value != null) {
            return
        }
        disposable = ArtifactRepository.get().loadArtifactCardListByType(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(Throwable::printStackTrace)
            .doOnNext { cardList ->
                if (requestStatusLiveData.value == null) {
                    requestStatus.refreshStatus = RequestStatus.REFRESH
                    requestStatus.data = cardList
                    requestStatusLiveData.value = requestStatus
                }
                disposable?.dispose()
            }
            .subscribe({ }, { })
    }

//    fun getCardSetDestById(cardSetId : String, type: String) {
//        ArtifactRepository.get()
//            .getRemoteCardSetInfo(cardSetId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnError {}
//            .doOnSubscribe {
//                if (RequestStatus.REFRESH == requestStatus.refreshStatus) {
//                    loadStatusLiveData.value = LoadStatus.LOADING
//                }
//            }
//            .doFinally {
//                loadStatusLiveData.value = LoadStatus.LOADED
//                requestStatus.isNetworkRequest = false
//            }
//            .doOnNext { cardSet ->
//                if (!TextUtils.isEmpty(cardSet.cdn_root) && !TextUtils.isEmpty(cardSet.url)) {
//                    Log.d("Шляпа", "Шляпа $cardSetId")
//
//                    getCardSetByUrl(cardSet.cdn_root + cardSet.url, type)
//                }
//            }
//            .subscribe(Subscriber.create())
//    }

    private fun saveArtifactCardsToLocalDb(localNewsList: List<Card>) {
        Flowable.create<Any>({ e ->
            ArtifactRepository.get().insertArtifactCardList(localNewsList)
            e.onComplete()

        }, BackpressureStrategy.LATEST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(Throwable::printStackTrace)
            .doOnComplete { }
            .subscribe(Subscriber.create())
    }

//    fun getCardSetByUrl(url: String, type: String) {
//        ArtifactRepository.get()
//            .getRemoteCardSet(url)
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnError {}
//            .doOnSubscribe {
//                if (RequestStatus.REFRESH == requestStatus.refreshStatus) {
//                    loadStatusLiveData.value = LoadStatus.LOADING
//                }
//            }
//            .doFinally {
//                loadStatusLiveData.value = LoadStatus.LOADED
//                requestStatus.isNetworkRequest = false
//            }
//            .doOnNext { cardSets ->
//                Log.d("Шляпа2", "Шляпа2 ${cardSets.cardSet.version} $url")
//                requestStatus.data = cardSets.cardSet.cardList.filter { it.cardType == type }
//                requestStatusLiveData.value = requestStatus
//                cardSets.cardSet.cardList?.let { saveArtifactCardsToLocalDb(it) }
//            }
//            .subscribe(Subscriber.create())
//    }


    fun getArtifactCardsList(refreshStatus: Int, type: String) {
        if (requestStatus.isNetworkRequest) return

        Log.d("ШляПа", "ШляПа")

        requestStatus.isNetworkRequest = true

        requestStatus.refreshStatus = refreshStatus
        requestStatus.setPage(refreshStatus)

        //getCardSetDestById("00", type)
        //getCardSetDestById("01", type)

    }

}