package com.hamza.dagger2sample.utils;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.hamza.dagger2sample.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";
    private MediatorLiveData<ApiResource<User>> cachedUser=new MediatorLiveData<>();
    @Inject
    public SessionManager() {
    }

    public void authenticateWithUserId(final LiveData<ApiResource<User>> source){
        if(cachedUser!=null){
             cachedUser.setValue(ApiResource.loading(null));
             cachedUser.addSource(source, new Observer<ApiResource<User>>() {
                 @Override
                 public void onChanged(ApiResource<User> userApiResource) {
                     cachedUser.setValue(userApiResource);
                     cachedUser.removeSource(source);
                 }
             });
        }
    }
    public void logOut(){
        Log.d(TAG, "logOut: logging out...");
        cachedUser.setValue(ApiResource.logout());
    }
    public LiveData<ApiResource<User>> getAuthUser(){
        return cachedUser;
    }
}
