package com.karthik.splash.models.PhotosLists

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by karthikrk on 20/12/15.
 */
@Parcelize
data class Links(@SerializedName("self")
            var self: String? = null,
            @SerializedName("html")
            var html: String? = null,
            @SerializedName("photos")
            var photos: String? = null,
            @SerializedName("likes")
            var likes: String? = null,
            @SerializedName("portfolio")
            @Expose
            var portfolio: String? = null):Parcelable
