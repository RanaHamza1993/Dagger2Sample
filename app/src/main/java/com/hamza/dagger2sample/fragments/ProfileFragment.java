package com.hamza.dagger2sample.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hamza.dagger2sample.databinding.FragmentProfileBinding;
import com.hamza.dagger2sample.models.User;
import com.hamza.dagger2sample.utils.AuthResource;
import com.hamza.dagger2sample.viewmodels.ProfileViewModel;
import com.hamza.dagger2sample.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";
    private ProfileViewModel viewModel;
    FragmentProfileBinding binding;

    @Inject
    Context context;
    @Inject
    ViewModelProviderFactory factory;

    protected void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        showToast(TAG);
        binding=FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ProfileFragment");
        viewModel = new ViewModelProvider(this, factory).get(ProfileViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case AUTHENTICATED: {
                            setUserDetails(userAuthResource.data);
                            break;
                        }
                        case ERROR: {
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }
    private void setErrorDetails(String message){
        binding.email.setText(message);
        binding.username.setText("error");
        binding.website.setText("error");
    }

    private void setUserDetails(User user){
        binding.email.setText(user.getEmail());
        binding. username.setText(user.getUsername());
        binding.website.setText(user.getWebsite());
    }

    @Override
    public void onDestroy() {
        binding=null;
        super.onDestroy();
    }
}
