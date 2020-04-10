package com.hamza.dagger2sample.di.modules.main;

import com.hamza.dagger2sample.network.MainApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
@Module
public class MainModule {


    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
