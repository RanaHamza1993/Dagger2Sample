package com.hamza.dagger2sample.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.hamza.dagger2sample.models.Post;
import com.hamza.dagger2sample.network.MainApi;
import com.hamza.dagger2sample.utils.ApiResource;
import com.hamza.dagger2sample.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";
    SessionManager sessionManager;
    MainApi mainApi;
    private MediatorLiveData<ApiResource<List<Post>>> posts;

    @Inject
    public PostsViewModel(MainApi mainApi, SessionManager sessionManager) {
        this.mainApi = mainApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "PostsViewModel: viewmodel is working.....");
    }

    public LiveData<ApiResource<List<Post>>> observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(ApiResource.loading(null));
            final LiveData<ApiResource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher
                    (mainApi.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId())
                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @Override
                                public List<Post> apply(Throwable throwable) throws Exception {
                                    Log.d(TAG, "apply: " + throwable);
                                    Post post = new Post();
                                    post.setId(-1);
                                    List<Post> posts = new ArrayList<>();
                                    posts.add(post);
                                    return posts;
                                }
                            }).map(new Function<List<Post>, ApiResource<List<Post>>>() {
                                @Override
                                public ApiResource<List<Post>> apply(List<Post> posts) throws Exception {
                                    if (posts.size() > 0) {
                                        if (posts.get(0).getId() == -1) {
                                            ApiResource.error("Did not get posts", null);
                                        }
                                    }
                                    return ApiResource.success(posts);
                                }
                            }).subscribeOn(Schedulers.io())
                    );
            posts.addSource(source, new Observer<ApiResource<List<Post>>>() {
                @Override
                public void onChanged(ApiResource<List<Post>> listApiResource) {
                    posts.setValue(listApiResource);
                    posts.removeSource(source);
                }
            });
        }
        return posts;


    }
}
