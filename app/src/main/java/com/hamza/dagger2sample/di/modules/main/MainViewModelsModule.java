package com.hamza.dagger2sample.di.modules.main;

import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.di.annotations.ViewModelKey;
import com.hamza.dagger2sample.viewmodels.PostsViewModel;
import com.hamza.dagger2sample.viewmodels.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);

}
