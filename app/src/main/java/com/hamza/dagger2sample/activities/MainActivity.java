package com.hamza.dagger2sample.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.hamza.dagger2sample.R;
import com.hamza.dagger2sample.fragments.PostsFragment;
import com.hamza.dagger2sample.fragments.ProfileFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  showToast(TAG);
        testFragment();
    }

    private void testFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ProfileFragment()).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout: {
                sessionManager.logOut();
                navAuthScreen();
                return true;
            } default:
                return super.onOptionsItemSelected(item);
        }
    }
}
