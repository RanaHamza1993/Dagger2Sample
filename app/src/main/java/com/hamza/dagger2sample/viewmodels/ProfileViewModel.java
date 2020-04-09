package com.hamza.dagger2sample.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.fragments.ProfileFragment;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";
    @Inject
    public ProfileViewModel(){
        Log.d(TAG, "ProfileViewModel: is ready.....");
    }
}
