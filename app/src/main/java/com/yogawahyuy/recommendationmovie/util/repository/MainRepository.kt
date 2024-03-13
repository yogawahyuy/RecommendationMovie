package com.yogawahyuy.recommendationmovie.util.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yogawahyuy.recommendationmovie.model.MovieListModel
import com.yogawahyuy.recommendationmovie.model.Result
import com.yogawahyuy.recommendationmovie.model.ResultTrending
import com.yogawahyuy.recommendationmovie.model.TrendingListModel
import com.yogawahyuy.recommendationmovie.util.Constanta
import com.yogawahyuy.recommendationmovie.util.NetworkService
import retrofit2.Response
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class MainRepository @Inject constructor(private val networkService: NetworkService) {
    private val compositeSubscription: CompositeSubscription = CompositeSubscription()
    fun getApiNowPlaying(livedata:MutableLiveData<List<Result>>){
    prosesObservable(networkService.getNowPlaying(),livedata)
    }
    fun getApiUpcoming(livedata: MutableLiveData<List<Result>>){
        prosesObservable(networkService.getUpcomingMovie(),livedata)
    }
    fun getApiPopular(livedata: MutableLiveData<List<Result>>){
        prosesObservable(networkService.getPopular(),livedata)
    }
    fun getApiTopRated(livedata: MutableLiveData<List<Result>>){
        prosesObservable(networkService.getTopRated(),livedata)
    }
    fun getApiTrendingAll(livedata: MutableLiveData<List<ResultTrending>>){
        prosesObservableTrending(networkService.getTrendingAll(Constanta.TIME_DAY),livedata)
    }

    private fun prosesObservable(observable:Observable<Response<MovieListModel>>,livedata: MutableLiveData<List<Result>>){
        compositeSubscription.add(observable.subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Subscriber<Response<MovieListModel>>(){
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    Log.d("MainRepo", "onError: "+e?.message)
                }

                override fun onNext(t: Response<MovieListModel>?) {
                    if (t!=null){
                        if (t.isSuccessful){
                            livedata.postValue(t.body()?.result)
                        }
                    }
                }

            }))
    }
    private fun prosesObservableTrending(observable:Observable<Response<TrendingListModel>>,livedata: MutableLiveData<List<ResultTrending>>){
        compositeSubscription.add(observable.subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Subscriber<Response<TrendingListModel>>(){
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