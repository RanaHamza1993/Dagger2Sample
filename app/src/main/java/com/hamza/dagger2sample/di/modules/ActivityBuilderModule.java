package com.hamza.dagger2sample.di.modules;

import com.hamza.dagger2sample.activities.AuthActivity;
import com.hamza.dagger2sample.activities.MainActivity;
import com.hamza.dagger2sample.di.modules.auth.AuthModule;
import com.hamza.dagger2sample.di.modules.auth.AuthViewModelModule;
import com.hamza.dagger2sample.di.modules.main.MainFragmentsBuilderModile;
import com.hamza.dagger2sample.fragments.ProfileFragment;

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

    @ContributesAndroidInjector(
            modules = {MainFragmentsBuilderModile.class}
    )
    abstract MainActivity contributeMainActivity();

}
