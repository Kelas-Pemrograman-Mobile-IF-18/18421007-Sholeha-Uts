package com.sholeha.aplikasipenjualanhelm.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sholeha.aplikasipenjualanhelm.R;

public class MenuHelmProyekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_helm_proyek);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(MenuHelmProyekActivity.this, HomeUserActivity.class);
        startActivity(i);
        finish();
    }

}