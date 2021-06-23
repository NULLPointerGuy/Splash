package com.karthik.splash.photodetailscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.karthik.network.IMemoryCache
import com.karthik.network.photodetailscreen.IPhotoDetailScreenRepository
import com.karthik.network.photodetailscreen.models.LikeResponse
import com.karthik.network.photodetailscreen.models.PhotoDetailInfo
import com.karthik.network.photodetailscreen.models.PhotoDetailsNetworkState
import com.karthik.network.photodetailscreen.repository.PhotoDetailScreenRepository
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response
import retrofit2.Retrofit

class PhotoDetailScreenViewModelTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private lateinit var memoryCache: IMemoryCache
    private lateinit var photodetailRepository: IPhotoDetailScreenRepository
    private lateinit var photoDetailScreenViewModel: PhotoDetailScreenViewModel

    @Before
    fun setup() {
        memoryCache = mock()
        photoDetailScreenViewModel = PhotoDetailScreenViewModel(
                memoryCache = memoryCache,
                photoRepository = photodetailRepository
        )
    }

    /*@Test
    fun `given an success in getting getPhotoInfo() state is set has PhotoDetailsNetworkLoadSuccess`(){
        Mockito.`when`(runBlocking { photoService.getPhotoInfo("123") })
                .thenReturn(Response.success(200, PhotoDetailInfo()))
        runBlocking {
            photodetailRepository.getPhotoInfo("123")
            Assert.assertTrue(photoDetailScreenViewModel.networkState.value is PhotoDetailsNetworkState.PhotoDetailsNetworkLoadSuccess)
        }
    }

    @Test
    fun `given an success in getting likeThePhoto() state is set has PhotoDetailsNetworkLoadSuccess`(){
        Mockito.`when`(runBlocking { photoService.likePhoto("123") })
                .thenReturn(Response.success(200, LikeResponse()))
        runBlocking {
            photodetailRepository.likePhoto("123")
            Assert.assertTrue(photoDetailScreenViewModel.networkState.value is PhotoDetailsNetworkState.PhotoDetailsNetworkLoadSuccess)
        }
    }

    @Test
    fun `given an error in getting likeThePhoto() state is set has PhotoDetailsNetworkLoadError`(){
        Mockito.`when`(runBlocking { photoService.likePhoto("123") })
                .thenReturn(Response.error(400,ResponseBody.create(MediaType.parse("application/json"),
                        "{}")))
        runBlocking {
            photodetailRepository.likePhoto("123")
            Assert.assertTrue(photoDetailScreenViewModel.networkState.value is PhotoDetailsNetworkState.PhotoLikeNetworkLoadError)
        }
    }

    @Test
    fun `given an error in getting getPhotoInfo() state is set has PhotoDetailsNetworkLoadError`(){
        Mockito.`when`(runBlocking { photoService.getPhotoInfo("123") })
                .thenReturn(Response.error(400, ResponseBody.create(MediaType.parse("application/json"),
                        "{}")))
        runBlocking {
            photodetailRepository.getPhotoInfo("123")
            Assert.assertTrue(photoDetailScreenViewModel.networkState.value is PhotoDetailsNetworkState.PhotoDetailsNetworkLoadError)
        }
    }*/
}
