package com.karthik.splash.homescreen


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karthik.splash.homescreen.network.HomeScreenOAuthRepository
import com.karthik.splash.models.oauth.OAuthBody
import com.karthik.splash.models.oauth.UserAuth
import com.karthik.splash.storage.MemoryCache
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class HomeScreenViewModelFactory(private val memoryCache: MemoryCache,
                                 private val homeScreenOAuthRepository: HomeScreenOAuthRepository)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            HomeScreenViewModel(memoryCache, homeScreenOAuthRepository) as T
}

class HomeScreenViewModel @Inject constructor(private val memoryCache: MemoryCache,
                                              private val homeScreenOAuthRepository: HomeScreenOAuthRepository)
    : ViewModel() {
    private val state: MutableLiveData<HomeScreenLoginState> = MutableLiveData()
    val userloginstate: LiveData<HomeScreenLoginState> = state
    private val disposable = CompositeDisposable()

    fun getUserInfo(code: String?) {
        disposable.add(homeScreenOAuthRepository.postOAuth(OAuthBody(code)).subscribeWith(
                object : DisposableSingleObserver<UserAuth>() {
                    override fun onSuccess(userAuth: UserAuth) {
                        memoryCache.setUserLoggedIn()
                        memoryCache.setAuthCode(userAuth.accessToken)
                        state.value = HomeScreenLoginState.LoginSuccess(userAuth)
                    }

                    override fun onError(e: Throwable) {
                        state.value = HomeScreenLoginState.LoginFailed(e)
                    }
                }))
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}