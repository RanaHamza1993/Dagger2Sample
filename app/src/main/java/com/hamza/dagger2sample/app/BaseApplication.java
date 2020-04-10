package com.hamza.dagger2sample.app;

import android.app.Activity;

import com.hamza.dagger2sample.activities.MainActivity;
import com.hamza.dagger2sample.di.components.AppComponent;
import com.hamza.dagger2sample.di.components.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
