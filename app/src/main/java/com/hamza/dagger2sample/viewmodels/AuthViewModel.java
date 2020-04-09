package com.hamza.dagger2sample.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.models.User;
import com.hamza.dagger2sample.network.AuthApi;
import com.hamza.dagger2sample.utils.ApiResource;
import com.hamza.dagger2sample.utils.SessionManager;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private final AuthApi authApi;
    private final SessionManager sessionManager;
    private static final String TAG = "AuthViewModel";

    @Inject
    public AuthViewModel(AuthApi authApi,SessionManager sessionManager) {
        Log.d(TAG, "AuthViewModel: ViewModel is working.....");
        this.authApi = authApi;
        this.sessionManager=sessionManager;

    }
    public void autheticateWithId(int id){
        Log.d(TAG, "autheticateWithId: Attempting to login");
        sessionManager.authenticateWithUserId(queryUserId(id));
    }
    private LiveData<ApiResource<User>> queryUserId(int id){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(id).
                        onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User user = new User();
                                user.setId(-1);
                                // user.setEmail("error@email.com");
                                return user;
                            }
                        }).map(new Function<User, ApiResource<User>>() {
                    @Override
                    public ApiResource<User> apply(User user) throws Exception {
                        if (user.getId() == -1)
                            return ApiResource.error("Could not login", null);
                        else
                            return ApiResource.authenticated(user);
                    }
                }).subscribeOn(Schedulers.io())
        );
    }
    public LiveData<ApiResource<User>> observeAuthUserState(){
        return sessionManager.getAuthUser();
    }
}
