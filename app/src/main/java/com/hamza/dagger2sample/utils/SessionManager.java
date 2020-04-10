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
    private MediatorLiveData<AuthResource<User>> cachedUser=new MediatorLiveData<>();
    @Inject
    public SessionManager() {
    }

    public void authenticateWithUserId(final LiveData<AuthResource<User>> source){
        if(cachedUser!=null){
             cachedUser.setValue(AuthResource.loading(null));
             cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                 @Override
                 public void onChanged(AuthResource<User> userAuthResource) {
                     cachedUser.setValue(userAuthResource);
                     cachedUser.removeSource(source);
                 }
             });
        }
    }
    public void logOut(){
        Log.d(TAG, "logOut: logging out...");
        cachedUser.setValue(AuthResource.logout());
    }
    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }
}
