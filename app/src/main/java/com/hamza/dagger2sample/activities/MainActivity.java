package com.hamza.dagger2sample.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.hamza.dagger2sample.R;
import com.hamza.dagger2sample.app.BaseApplication;
import com.hamza.dagger2sample.databinding.ActivityMainBinding;
import com.hamza.dagger2sample.di.components.DaggerAppComponent;
import com.hamza.dagger2sample.fragments.PostsFragment;
import com.hamza.dagger2sample.fragments.ProfileFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //  showToast(TAG);
       // testFragment();
        init();
    }

    private void testFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new PostsFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                sessionManager.logOut();
                navAuthScreen();
                return true;
            }
            case android.R.id.home: {
                if (binding.drawerLayout.isDrawerOpen(Gravity.START)) {
                    binding.drawerLayout.closeDrawer(Gravity.START);
                    return true;
                } else
                    return false;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile: {
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.main, true)
                        .build();
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.profileFragment,
                        null,
                        navOptions);
                break;
            }
            case R.id.nav_posts: {
                if(isValidDestination(R.id.nav_posts))
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsFragment);
                break;
            }
        }
        item.setCheckable(true);
        binding.drawerLayout.closeDrawer(Gravity.START);
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), binding.drawerLayout);
    }
    private boolean isValidDestination(int destination){
        return destination!=Navigation.findNavController(this,R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    private void init() {
        NavController navHostController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navHostController, binding.drawerLayout);
        NavigationUI.setupWithNavController(binding.navView, navHostController);
        binding.navView.setNavigationItemSelectedListener(this);
    }
}
