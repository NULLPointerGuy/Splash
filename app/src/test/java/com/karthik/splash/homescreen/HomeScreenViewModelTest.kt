package com.karthik.splash.homescreen


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.karthik.network.IMemoryCache
import com.karthik.network.home.IHomeScreenOAuthRepository
import com.karthik.network.home.models.HomeScreenLoginState
import com.karthik.network.home.models.OAuthBody
import com.karthik.network.home.models.UserAuth
import com.karthik.splash.observeForTesting
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class HomeScreenViewModelTest {

    private lateinit var memoryCache: IMemoryCache
    private lateinit var homeScreenOAuthRepository: IHomeScreenOAuthRepository
    private lateinit var homeViewmodel: HomeScreenViewModel

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        memoryCache = mock()
        homeScreenOAuthRepository = mock()
        homeViewmodel = HomeScreenViewModel(
                memoryCache = memoryCache,
                homeScreenOAuthRepository = homeScreenOAuthRepository
        )
    }


    @Test
    fun `given code is null or empty user login state is login failed`(){
        homeViewmodel.getUserInfo(null)
        homeViewmodel.userloginstate.observeForTesting {  }
        Assert.assertTrue(homeViewmodel.userloginstate.value is HomeScreenLoginState.LoginFailed)
    }

    @Test
    fun `given repository returns success in post of OAuth login state is set as success`() {
        val oAuthBody = OAuthBody("abcdw")
        val userAuth = UserAuth(accessToken = "as",tokenType = "cd",scope = "sas",createdAt = "123456")
        Mockito.`when`(runBlocking { homeScreenOAuthRepository.postOAuth(oAuthBody) })
                .thenReturn(HomeScreenLoginState.LoginSuccess(userAuth))
        homeViewmodel.getUserInfo("abcdw")
        homeViewmodel.userloginstate.observeForTesting {  }
        Assert.assertTrue(homeViewmodel.userloginstate.value is HomeScreenLoginState.LoginSuccess)
    }

    @Test
    fun `given repository returns failure in post of OAuth login state is set as failed`() {
        val oAuthBody = OAuthBody("saasa")
        Mockito.`when`(runBlocking { homeScreenOAuthRepository.postOAuth(oAuthBody) })
                .thenReturn(HomeScreenLoginState.LoginFailed(IllegalArgumentException()))
        homeViewmodel.getUserInfo("saasa")
        homeViewmodel.userloginstate.observeForTesting {  }
        Assert.assertTrue(homeViewmodel.userloginstate.value is HomeScreenLoginState.LoginFailed)
    }
}
