package com.dibarter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home :
                        loadFragmment(new HomeFragment());
                        break;
                    case R.id.navigation_plus :
                        startActivity(new Intent(MainActivity.this, PasangBarangActivity.class));
                        break;
                    case R.id.navigation_notification :
                        loadFragmment(new NotificationFragment());
                        break;
                    case R.id.navigation_account :
                        loadFragmment(new AccountFragment());
                        break;
                }

                return true;
            }
        });

        loadFragmment(new HomeFragment());
    }

    public void loadFragmment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.navigation_home) {
            super.onBackPressed();
        } else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}