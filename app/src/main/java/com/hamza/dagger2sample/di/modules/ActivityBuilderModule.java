package com.hamza.dagger2sample.di.modules;

import com.hamza.dagger2sample.activities.AuthActivity;
import com.hamza.dagger2sample.activities.MainActivity;
import com.hamza.dagger2sample.di.modules.auth.AuthModule;
import com.hamza.dagger2sample.di.modules.auth.AuthScope;
import com.hamza.dagger2sample.di.modules.auth.AuthViewModelModule;
import com.hamza.dagger2sample.di.modules.main.MainFragmentsBuilderModule;
import com.hamza.dagger2sample.di.modules.main.MainModule;
import com.hamza.dagger2sample.di.modules.main.MainScope;
import com.hamza.dagger2sample.di.modules.main.MainViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilderModule {
    @AuthScope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            })
    abstract AuthActivity contributeAuthActivity();
    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentsBuilderModule.class,
                    MainModule.class,
                    MainViewModelsModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
