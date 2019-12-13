package com.karthik.splash.homescreen.bottomliketab

import com.karthik.splash.BuildConfig
import com.karthik.splash.Models.PhotosLists.Photos
import com.karthik.splash.RestServices.NetworkLayer.UserServiceNetworkLayer
import com.karthik.splash.Storage.Cache
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class BottomTabLikePresenter(private val view: BottomLikeTabContract.view,
                             private val cache: Cache,
                             private val userServiceNetworkLayer: UserServiceNetworkLayer):BottomLikeTabContract.presenter {

    private val disposable = CompositeDisposable()
    private val userscope = "public+read_user+read_photos+write_likes";

    override fun decideScreenType() {
        if(cache.isUserLoggedIn){
            getUserLikeList()
            return
        }
        view.hideProgress()
        view.showLoginScreen()
    }

    override fun login() {
        val url = "${BuildConfig.SPLASH_LOGIN_URL}?client_id=${BuildConfig.SPLASH_KEY}" +
                "&redirect_uri=${BuildConfig.SPLASH_LOGIN_CALLBACK}&response_type=code&scope=$userscope"
        view.openLoginOauthUrl(url)
    }

    override fun clearResources() {
        if(!disposable.isDisposed){
            disposable.dispose()
        }
    }

    private fun getUserLikeList(){
        disposable.add(userServiceNetworkLayer.userLikedPhotos.subscribeWith(object: DisposableSingleObserver<List<Photos>>() {
            override fun onSuccess(photos: List<Photos>) {
                view.hideProgress()
                view.showLikesList(photos)
            }

            override fun onError(e: Throwable) {
                view.hideProgress()
                view.showEmptyLikedScreen()
            }
        }))
    }
}