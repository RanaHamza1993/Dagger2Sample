package com.hamza.dagger2sample.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.hamza.dagger2sample.R;
import com.hamza.dagger2sample.databinding.ActivityAuthBinding;
import com.hamza.dagger2sample.models.User;
import com.hamza.dagger2sample.utils.ApiResource;
import com.hamza.dagger2sample.viewmodels.AuthViewModel;
import com.hamza.dagger2sample.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    Drawable logo;
    @Inject
    RequestManager requestManager;
    ActivityAuthBinding binding;
    private static final String TAG = "AuthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);
        setLogo();
        setListeners();
        subscribeObservers();
    }

    private void setListeners() {
        binding.loginButton.setOnClickListener(v -> {
            attemptLogin();
        });
    }

    private void setLogo() {
        requestManager.load(logo)
                .into(binding.loginLogo);
    }

    private void subscribeObservers() {
        viewModel.observeAuthUserState().observe(this, new Observer<ApiResource<User>>() {
            @Override
            public void onChanged(ApiResource<User> userApiResource) {
                if (userApiResource != null) {
                    switch (userApiResource.status) {
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: "+userApiResource.data.getEmail());
                            loginSuccess();
                            break;
                        }
                        case NOT_AUTHENTICATED:
                        case ERROR: {
                            showProgressBar(false);
                            showToast(userApiResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void showProgressBar(boolean isVisible){
        if(isVisible)
            binding.progressBar.setVisibility(View.VISIBLE);
        else
            binding.progressBar.setVisibility(View.GONE);
    }
    private void attemptLogin() {
        if (TextUtils.isEmpty(binding.userIdInput.getText().toString())) {
            return;
        }
        viewModel.autheticateWithId(Integer.parseInt(binding.userIdInput.getText().toString()));
    }
    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }
    private void loginSuccess(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
