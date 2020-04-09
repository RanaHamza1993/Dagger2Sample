package com.hamza.dagger2sample.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.models.User;
import com.hamza.dagger2sample.network.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private final AuthApi authApi;
    private static final String TAG = "AuthViewModel";
    private MediatorLiveData mediatorLiveData = new MediatorLiveData<User>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.d(TAG, "AuthViewModel: ViewModel is working.....");
        this.authApi = authApi;

    }
    public void autheticateWithId(int id){
        final LiveData<User> source= LiveDataReactiveStreams.fromPublisher(
          authApi.getUser(id).
          subscribeOn(Schedulers.io())
        );
        mediatorLiveData.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mediatorLiveData.setValue(user);
                mediatorLiveData.removeSource(source);

            }
        });
    }
    public LiveData<User> getUserLiveData(){
        return mediatorLiveData;
    }
}
