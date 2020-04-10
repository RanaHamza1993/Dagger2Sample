package com.hamza.dagger2sample.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.network.MainApi;
import com.hamza.dagger2sample.utils.SessionManager;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";
    SessionManager sessionManager;
    MainApi mainApi;

    @Inject
    public PostsViewModel(MainApi mainApi, SessionManager sessionManager) {
        this.mainApi = mainApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "PostsViewModel: viewmodel is working.....");
    }
}
