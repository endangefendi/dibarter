package com.dibarter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.dibarter.R;
import com.dibarter.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Created by Endang Efendi on Jan 2021.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}