package com.sholeha.aplikasipenjualanhelm.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.RegistActivity;

public class MenuHelmKlasik extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_helm_klasik);


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(MenuHelmKlasik.this, HomeUserActivity.class);
        startActivity(i);
        finish();
    }
}