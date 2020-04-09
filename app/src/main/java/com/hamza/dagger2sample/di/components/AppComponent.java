package com.hamza.dagger2sample.di.components;

import android.app.Application;

import com.hamza.dagger2sample.app.BaseApplication;
import com.hamza.dagger2sample.di.modules.ActivityBuilderModule;
import com.hamza.dagger2sample.di.modules.AppModule;
import com.hamza.dagger2sample.di.modules.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                ViewModelFactoryModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
