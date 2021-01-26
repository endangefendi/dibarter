package com.dibarter.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dibarter.R;

public class PasangBarangActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasang_barang);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn_simpan).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.btn_simpan:
                Toast.makeText(this, "Validasi inputan dan proses simpan",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}