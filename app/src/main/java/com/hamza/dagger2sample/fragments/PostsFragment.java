package com.hamza.dagger2sample.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hamza.dagger2sample.R;
import com.hamza.dagger2sample.adapters.PostsRecyclerAdapter;
import com.hamza.dagger2sample.databinding.FragmentPostsBinding;
import com.hamza.dagger2sample.databinding.FragmentProfileBinding;
import com.hamza.dagger2sample.models.Post;
import com.hamza.dagger2sample.utils.ApiResource;
import com.hamza.dagger2sample.utils.VerticalSpacingItemDecoration;
import com.hamza.dagger2sample.viewmodels.PostsViewModel;
import com.hamza.dagger2sample.viewmodels.ViewModelProviderFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {
    private FragmentPostsBinding binding;
    private static final String TAG = "PostsFragment";
   private PostsViewModel viewModel;
    @Inject
    ViewModelProviderFactory factory;
    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;
    @Inject
    VerticalSpacingItemDecoration verticalSpacingItemDecoration;
    @Inject
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding= FragmentPostsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: PostsFragment");
        viewModel = new ViewModelProvider(this, factory).get(PostsViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }
    private void subscribeObservers() {
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<ApiResource<List<Post>>>() {
            @Override
            public void onChanged(ApiResource<List<Post>> listApiResource) {
                if(listApiResource!=null) {
                    switch (listApiResource.status) {
                        case LOADING:{
                            Log.d(TAG, "onChanged: Loading....");
                            break;
                        }
                        case SUCCESS:{
                            Log.d(TAG, "onChanged: Success");
                            postsRecyclerAdapter.setPosts(listApiResource.data);
                            break;

                        }
                        case ERROR:{
                            Log.d(TAG, "onChanged: Error");
                            break;

                        }
                 //       Log.d(TAG, "onChanged: " + listApiResource.data);

                    }
                }
            }
        });
    }
    @Override
    public void onDestroy() {
        binding=null;
        super.onDestroy();
    }
    private void initRecyclerView(){
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addItemDecoration(verticalSpacingItemDecoration);
        binding.recyclerView.setAdapter(postsRecyclerAdapter);
    }
}
