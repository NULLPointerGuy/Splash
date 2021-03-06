package com.karthik.network.photodetailscreen.models


import com.google.gson.annotations.SerializedName
import com.karthik.network.home.bottomliketab.models.Links


data class Photo(
        @SerializedName("color") var color: String? = null,
        @SerializedName("width") var width: Int = 0,
        @SerializedName("description") var description: String? = null,
        @SerializedName("links") var links: Links? = null,
        @SerializedName("id") var id: String? = null,
        @SerializedName("liked_by_user") var likedByUser: Boolean = false,
        @SerializedName("height") var height: Int = 0,
        @SerializedName("likes") var likes: Int = 0
)
