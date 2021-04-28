package com.sholeha.aplikasipenjualanhelm.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ornach.nobobutton.NoboButton;
import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.RegistActivity;
import com.sholeha.aplikasipenjualanhelm.admin.HomeAdminActivity;

public class LoginActivity extends AppCompatActivity {

    NoboButton loginBtn;
    Button btnPindah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnPindah= (Button) findViewById(R.id.btnPindah);
        loginBtn= (NoboButton) findViewById(R.id.loginBtn);

        btnPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(i);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomeUserActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}