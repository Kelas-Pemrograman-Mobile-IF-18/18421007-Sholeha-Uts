package com.sholeha.aplikasipenjualanhelm.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.session.PrefSetting;
import com.sholeha.aplikasipenjualanhelm.session.SessionManager;
import com.sholeha.aplikasipenjualanhelm.users.LoginActivity;

public class HomeAdminActivity extends AppCompatActivity {

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    CardView cardExit, cardDataHelm, cardInput, cardProfile, cardpesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferances();

        session = new SessionManager(HomeAdminActivity.this);

        prefSetting.isLogin(session, prefs);

        cardExit = (CardView) findViewById(R.id.cardExit);
        cardDataHelm = (CardView) findViewById(R.id.cardDataHelm);
        cardInput = (CardView) findViewById(R.id.InputData);
        cardProfile = (CardView) findViewById(R.id.cardProfile);
        cardpesanan = (CardView) findViewById(R.id.cardpesanan);

        cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputDataHelm.class);
                startActivity(i);
                finish();
            }
        });

        cardDataHelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ActivityDataHelm.class);
                startActivity(i);
                finish();
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        cardpesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, PesananUser.class);
                startActivity(i);
                finish();
            }
        });

    }
}