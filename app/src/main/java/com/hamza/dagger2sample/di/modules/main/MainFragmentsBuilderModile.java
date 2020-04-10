package com.hamza.dagger2sample.di.modules.main;

import com.hamza.dagger2sample.fragments.PostsFragment;
import com.hamza.dagger2sample.fragments.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBuilderModile {
    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
