package com.hamza.dagger2sample.di.modules;

import com.hamza.dagger2sample.activities.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
            modules = AuthViewModelModule.class
    )
    abstract AuthActivity contributeAuthActivity();


}
