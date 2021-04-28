package com.sholeha.aplikasipenjualanhelm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sholeha.aplikasipenjualanhelm.R;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        getSupportActionBar().hide();
    }
}