package com.hamza.dagger2sample.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hamza.dagger2sample.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
abstract public class ViewModelFactoryModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);
}
