package com.hamza.dagger2sample.di.modules.main;

import android.app.Activity;
import android.app.Application;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamza.dagger2sample.activities.MainActivity;
import com.hamza.dagger2sample.adapters.PostsRecyclerAdapter;
import com.hamza.dagger2sample.network.MainApi;
import com.hamza.dagger2sample.utils.VerticalSpacingItemDecoration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
@Module
public class MainModule {
    @MainScope
    @Provides
    static PostsRecyclerAdapter providePostRecyclerAdapter(){
        return new PostsRecyclerAdapter();
    }
    @MainScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager(MainActivity activity){
        return new LinearLayoutManager(activity);
    }
    @MainScope
    @Provides
    static VerticalSpacingItemDecoration provideVerticalSpacingDecoration(){
        return new VerticalSpacingItemDecoration(15);
    }
    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
