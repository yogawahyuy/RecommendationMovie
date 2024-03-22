package com.yogawahyuy.recommendationmovie.util.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yogawahyuy.recommendationmovie.model.Cast
import com.yogawahyuy.recommendationmovie.model.CreditMovieModel
import com.yogawahyuy.recommendationmovie.model.DetailMovieModel
import com.yogawahyuy.recommendationmovie.model.MovieListModel
import com.yogawahyuy.recommendationmovie.model.Result
import com.yogawahyuy.recommendationmovie.model.ResultVideos
import com.yogawahyuy.recommendationmovie.model.VideosProviderModel
import com.yogawahyuy.recommendationmovie.util.NetworkService
import retrofit2.Response
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class DetailRepository @Inject constructor(private val networkService: NetworkService) {
    private val compositeSubscription: CompositeSubscription = CompositeSubscription()

    fun getDetailMovie(livedata: MutableLiveData<DetailMovieModel>, movieId:Int){
        prosesObservable(networkService.getDetailMovie(movieId),livedata)
    }
    fun getCreditMovie(livedata:MutableLiveData<List<Cast>>,movieId: Int){
        prosesObservableCredit(networkService.getCreditMovies(movieId),livedata)
    }
    fun getVideosProvider(liveData: MutableLiveData<List<ResultVideos>>,movieId: Int){
        prosesObservableVideos(networkService.getVideoProvider(movieId),liveData)
    }
    private fun prosesObservable(observable: Observable<Response<DetailMovieModel>>, livedata: MutableLiveData<DetailMovieModel>){
        compositeSubscription.add(observable.subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Response<DetailMovieModel>>(){
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    Log.d("MainRepo", "onError: "+e?.message)
                }

                override fun onNext(t: Response<DetailMovieModel>?) {
                    if (t!=null){
                        if (t.isSuccessful){
                            livedata.postValue(t.body())
                        }
                    }
                }

            }))
    }

    private fun prosesObservableCredit(observable: Observable<Response<CreditMovieModel>>, livedata: MutableLiveData<List<Cast>>){
        compositeSubscription.add(observable.subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Response<CreditMovieModel>>(){
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    Log.d("MainRepo", "onError: "+e?.message)
                }

                override fun onNext(t: Response<CreditMovieModel>?) {
                    if (t!=null){
                        if (t.isSuccessful){
                            livedata.postValue(t.body()?.cast)
                        }
                    }
                }

            }))
    }

    private fun prosesObservableVideos(observable: Observable<Response<VideosProviderModel>>, livedata: MutableLiveData<List<ResultVideos>>){
        compositeSubscription.add(observable.subscribeOn(Schedulers.newThread())
            .unsubscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Response<VideosProviderModel>>(){
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    Log.d("MainRepo", "onError: "+e?.message)
                }

                override fun onNext(t: Response<VideosProviderModel>?) {
                    if (t!=null){
                        if (t.isSuccessful){
                            livedata.postValue(t.body()?.resultVideos)
                        }
                    }
                }

            }))
    }
}