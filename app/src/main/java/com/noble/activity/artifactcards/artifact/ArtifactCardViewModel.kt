package com.noble.activity.artifactcards.artifact

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.Log
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.model.Card
import com.ruzhan.lion.model.LoadStatus
import com.ruzhan.lion.model.RequestStatus
import com.ruzhan.lion.rx.Subscriber
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArtifactCardViewModel(app: Application) : AndroidViewModel(app) {

    companion object {

        const val NEW_ID: Int = 0
    }

    private val requestStatus: RequestStatus<List<Card>> = RequestStatus()
    private var page: Int = 1

    val loadStatusLiveData: MutableLiveData<LoadStatus> = MutableLiveData()
    val requestStatusLiveData: MutableLiveData<RequestStatus<List<Card>>> = MutableLiveData()

    private var disposable: Disposable? = null

    init {
        requestStatusLiveData.value = null
    }

    fun loadLocalArtifactCards(newId: String) {
        if (requestStatusLiveData.value != null) {
            return
        }
        disposable = ArtifactRepository.get().loadArtifactCardList()
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

    fun getCardSetDestById(cardSetId : String) {
        ArtifactRepository.get()
            .getCardSetInfo(cardSetId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {}
            .doOnSubscribe {
                if (RequestStatus.REFRESH == requestStatus.refreshStatus) {
                    loadStatusLiveData.value = LoadStatus.LOADING
                }
            }
            .doFinally {
                loadStatusLiveData.value = LoadStatus.LOADED
                requestStatus.isNetworkRequest = false
            }
            .doOnNext { cardSet ->
                if (!TextUtils.isEmpty(cardSet.cdn_root) && !TextUtils.isEmpty(cardSet.url)) {
                    Log.d("Шляпа", "Шляпа $cardSetId")

                    getCardSetByUrl(cardSet.cdn_root + cardSet.url)
                }
            }
            .subscribe(Subscriber.create())
    }

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

    fun getCardSetByUrl(url: String) {
        ArtifactRepository.get()
            .getCardSet(url)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {}
            .doOnSubscribe {
                if (RequestStatus.REFRESH == requestStatus.refreshStatus) {
                    loadStatusLiveData.value = LoadStatus.LOADING
                }
            }
            .doFinally {
                loadStatusLiveData.value = LoadStatus.LOADED
                requestStatus.isNetworkRequest = false
            }
            .doOnNext { cardSets ->
                Log.d("Шляпа2", "Шляпа2 ${cardSets.cardSet.version} $url")
                requestStatus.data = cardSets.cardSet.cardList
                requestStatusLiveData.value = requestStatus
                cardSets.cardSet.cardList?.let { saveArtifactCardsToLocalDb(it) }
            }
            .subscribe(Subscriber.create())
    }


    fun getArtifactCardsList(refreshStatus: Int, newId: String) {
        if (requestStatus.isNetworkRequest) return

        Log.d("ШляПа", "ШляПа")

        requestStatus.isNetworkRequest = true

        requestStatus.refreshStatus = refreshStatus
        requestStatus.setPage(refreshStatus)
        
        //getCardSetDestById("00")
        getCardSetDestById("01")

    }

}