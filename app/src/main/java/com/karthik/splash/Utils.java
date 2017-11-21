package com.karthik.splash;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.karthik.splash.Models.Photos;
import com.karthik.splash.RestServices.UserOfflineException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthikrk on 12/11/17.
 */

public class Utils {
    public enum NetworkErrorType {OFFLINE,IOEXCEPTION}
    public enum ResponseType {NEW,TRENDING,FEATURED,LIKES}
    public static boolean isInternetAvailable(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static String convertArrayListToString(List<? extends Object> responseList){
        return new Gson().toJson(responseList);
    }

    @Nullable
    public static ArrayList<? extends Object> convertStringToArrayList(String response,ResponseType responseType){
        if(responseType==ResponseType.NEW||responseType==ResponseType.TRENDING||responseType==ResponseType.FEATURED){
            Type type = new TypeToken<ArrayList<Photos>>() {}.getType();
            return new Gson().fromJson(response,type);
        }
        return null;
    }

    public static Utils.NetworkErrorType getErrorType(Throwable e){
        if(e instanceof UserOfflineException)
            return Utils.NetworkErrorType.OFFLINE;
        return Utils.NetworkErrorType.IOEXCEPTION;
    }

}
