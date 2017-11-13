package com.karthik.splash.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.karthik.splash.R;

import javax.inject.Inject;

/**
 * Created by karthikrk on 12/11/17.
 */

public class Cache {
    private SharedPreferences preferences;
    private Context context;

    @Inject
    public Cache(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(
                context.getString(R.string.local_cache),Context.MODE_PRIVATE);
    }

    public boolean isUserLoggedIn(){
        return preferences.getBoolean(context.getString(R.string.is_user_logged_in)
                ,false);
    }

    public void setUserLoggedIn(){
        preferences.edit()
                .putBoolean(context.getString(R.string.is_user_logged_in),true)
                .commit();
    }

    public boolean isCacheAvail(){
        return preferences.getBoolean(context
                        .getString(R.string.is_response_cache_avail),false);
    }

    public void setCacheAvail(){
        preferences.edit()
                .putBoolean(context.getString(R.string.is_response_cache_avail),true)
                .commit();

    }
}
