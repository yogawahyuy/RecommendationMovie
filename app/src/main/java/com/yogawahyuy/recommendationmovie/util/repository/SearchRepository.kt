package com.yogawahyuy.recommendationmovie.util.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yogawahyuy.recommendationmovie.model.ResultTrending
import com.yogawahyuy.recommendationmovie.model.TrendingListModel
import com.yogawahyuy.recommendationmovie.util.NetworkService
import retrofit2.Response
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class SearchRepository @Inject constructor(private val networkService: NetworkService) {
    private val compositeSubscription: CompositeSubscription = CompositeSubscription()
    fun getSearchResult(livedata: MutableLiveData<List<ResultTrending>>,searchString: String){
        prosesObservableTrending(networkService.getSearchResult(searchString),livedata)
    }
    private fun prosesObservableTrending(observable: Observable<Response<TrendingListModel>>, livedata: MutableLiveData<List<ResultTrending>>){
        compositeSubscription.add(observable.subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Response<TrendingListModel>>(){
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    Log.d("MainRepo", "onError: "+e?.message)
                }

                override fun onNext(t: Response<TrendingListModel>?) {
                    if (t!=null){
                        if (t.isSuccessful){
                            livedata.postValue(t.body()?.result)
                        }
                    }
                }

            }))
    }
}