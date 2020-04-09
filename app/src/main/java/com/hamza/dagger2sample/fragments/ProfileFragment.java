package com.hamza.dagger2sample.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hamza.dagger2sample.R;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";
    protected void showToast(String message){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        showToast(TAG);
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
