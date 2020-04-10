package com.hamza.dagger2sample.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.models.User;
import com.hamza.dagger2sample.utils.AuthResource;
import com.hamza.dagger2sample.utils.SessionManager;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private final SessionManager sessionManager;
    private static final String TAG = "ProfileViewModel";
    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        this.sessionManager=sessionManager;
        Log.d(TAG, "ProfileViewModel: is ready.....");
    }
    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }


}
