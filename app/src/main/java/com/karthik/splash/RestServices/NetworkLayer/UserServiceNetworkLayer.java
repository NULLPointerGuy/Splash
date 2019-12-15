package com.karthik.splash.restservices.networklayer;

import com.karthik.splash.models.PhotosLists.Photos;
import com.karthik.splash.restservices.services.UserService;
import com.karthik.splash.storage.Cache;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by karthikrk on 30/11/17.
 */

public class UserServiceNetworkLayer {

    private UserService userService;
    private Cache localCache;

    @Inject
    public UserServiceNetworkLayer(Retrofit retrofit,Cache localCache){
        userService = retrofit.create(UserService.class);
        this.localCache = localCache;
    }

    public Single<ArrayList<Photos>> getUserLikedPhotos(){
        if(localCache.getUserName()!=null){
            return getPhotos(localCache.getUserName());
        }else{
            return getUserProfileAndLikedPhotos();
        }
    }

    private Single<ArrayList<Photos>> getPhotos(String username){
        return userService.getUserLikePhotos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<ArrayList<Photos> > getUserProfileAndLikedPhotos(){
       return userService.getUserProfile()
                .subscribeOn(Schedulers.io())
                .flatMap(profile -> {
                    localCache.setUserName(profile.getUsername());
                    return getPhotos(profile.getUsername());
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
