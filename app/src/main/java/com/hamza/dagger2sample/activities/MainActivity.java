package com.hamza.dagger2sample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hamza.dagger2sample.R;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast(TAG);
    }
}
