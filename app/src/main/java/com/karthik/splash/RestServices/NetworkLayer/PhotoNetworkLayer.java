package com.karthik.splash.restservices.networklayer;

import com.karthik.splash.models.likephoto.LikeResponse;
import com.karthik.splash.models.photodetail.PhotoDetailInfo;
import com.karthik.splash.restservices.services.PhotoService;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by karthikrk on 09/12/17.
 */

public class PhotoNetworkLayer {
    private PhotoService photoService;

    @Inject
    public PhotoNetworkLayer(Retrofit retrofit){
        photoService = retrofit.create(PhotoService.class);
    }

    public Single<PhotoDetailInfo> getPhotoInfo(String id){
       return photoService.getPhotoInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<LikeResponse> likePhoto(String id){
        return photoService.likePhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
