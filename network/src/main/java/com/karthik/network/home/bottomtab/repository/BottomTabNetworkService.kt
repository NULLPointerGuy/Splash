package com.karthik.network.home.bottomtab.repository



import com.karthik.network.home.bottomliketab.models.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

/**
 * Created by karthikrk on 16/11/17.
 */

internal interface BottomTabNetworkService {

    @GET("photos")
    suspend fun getPhotos(
            @Query("order_by") orderby: String,
            @Query("page") pageNo: Int
    ): Response<ArrayList<Photos>>
}
