package com.hamza.dagger2sample.di.modules;

import com.hamza.dagger2sample.activities.AuthActivity;
import com.hamza.dagger2sample.di.modules.auth.AuthModule;
import com.hamza.dagger2sample.di.modules.auth.AuthViewModelModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            })
    abstract AuthActivity contributeAuthActivity();


}
