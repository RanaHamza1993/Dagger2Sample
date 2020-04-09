package com.hamza.dagger2sample.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.hamza.dagger2sample.R;
import com.hamza.dagger2sample.databinding.ActivityAuthBinding;
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
        binding=ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel= new ViewModelProvider(this,providerFactory).get(AuthViewModel.class);
        setLogo();
       // Log.d(TAG, something);


    }
    private void setLogo(){
        requestManager.load(logo)
                .into(binding.loginLogo);
    }
}
