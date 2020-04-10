package com.hamza.dagger2sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.hamza.dagger2sample.models.User;
import com.hamza.dagger2sample.utils.AuthResource;
import com.hamza.dagger2sample.utils.SessionManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Inject
    SessionManager sessionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }
    protected void subscribeObservers(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case ERROR:
                        case LOADING: {
                            break;
                        }
                        case AUTHENTICATED: {

                            Log.d(TAG, "onChanged: "+ userAuthResource.data.getEmail());
                            break;
                        }
                        case NOT_AUTHENTICATED:
                         {
                             navAuthScreen();
                            break;
                        }
                    }
                }
            }
        });
    }
    protected void navAuthScreen(){
        Intent intent=new Intent(this,AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
