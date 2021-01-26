package com.dibarter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.dibarter.R;
import com.dibarter.fragment.AccountFragment;
import com.dibarter.fragment.HomeFragment;
import com.dibarter.fragment.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Created by Endang Efendi on Jan 2021.
 */

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_plus, R.id.navigation_account, R.id.navigation_notification)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    protected void onResume() {
        super.onResume();
        navView.getMenu().getItem(0).setChecked(true);
    }

}