package com.sholeha.aplikasipenjualanhelm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.session.PrefSetting;

public class Profile extends AppCompatActivity {

    TextView txtUserName, txttNoTelpon, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        txtUserName = (TextView) findViewById(R.id.userName);
       txttNoTelpon = (TextView) findViewById(R.id.noTelpon);
        txtAlamat = (TextView) findViewById(R.id.alamat);

        txtUserName.setText(PrefSetting.Username);
        txttNoTelpon.setText(PrefSetting.NoTelpon);
        txtAlamat.setText(PrefSetting.Alamat);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
    }
}