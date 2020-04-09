package com.hamza.dagger2sample.di.modules;

import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.di.annotations.ViewModelKey;
import com.hamza.dagger2sample.viewmodels.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);
}
