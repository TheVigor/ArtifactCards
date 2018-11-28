package com.noble.activity.artifactcards

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.ruzhan.lion.model.LoadStatus
import com.ruzhan.lion.model.RequestStatus
import com.ruzhan.lion.rx.Subscriber
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArtifactDataTypeViewModel(app: Application) : AndroidViewModel(app) {

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

    fun loadLocalOtherNews(newId: String) {
        if (requestStatusLiveData.value != null) {
            return
        }
        disposable = ArtifactRepository.get().loadNewsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(Throwable::printStackTrace)
            .doOnNext { newsEntity ->
                if (requestStatusLiveData.value == null) {
                    requestStatus.refreshStatus = RequestStatus.REFRESH
                    requestStatus.data = newsEntity
                    requestStatusLiveData.value = requestStatus
                }
                disposable?.dispose()
            }
            .subscribe({ }, { })
    }

    fun getOtherNewsList(refreshStatus: Int, newId: String) {
        if (requestStatus.isNetworkRequest) return

        requestStatus.isNetworkRequest = true

        requestStatus.refreshStatus = refreshStatus
        requestStatus.setPage(refreshStatus)

        page = if (RequestStatus.REFRESH == requestStatus.refreshStatus) 1 else (++page)

        ArtifactRepository.get().getCardsList(TOKEN, page, newId.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {}
            .doOnSubscribe {
                if (RequestStatus.REFRESH == requestStatus.refreshStatus) {
                    loadStatusLiveData.value = LoadStatus.LOADING
                }
            }
            .map { result -> result.data }
            .doFinally {
                loadStatusLiveData.value = LoadStatus.LOADED
                requestStatus.isNetworkRequest = false
            }
            .doOnNext { newsList ->
                requestStatus.data = newsList
                requestStatusLiveData.value = requestStatus

                newsList?.let { setOtherNewsToLocalDb(newsList, newId) }
            }
            .subscribe(Subscriber.create())
    }

    private fun setOtherNewsToLocalDb(localNewsList: List<Card>, newId: String) {
        Flowable.create<Any>({ e ->
            ArtifactRepository.get().insertNewsList(localNewsList)
            e.onComplete()

        }, BackpressureStrategy.LATEST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(Throwable::printStackTrace)
            .doOnComplete { }
            .subscribe(Subscriber.create())
    }
}