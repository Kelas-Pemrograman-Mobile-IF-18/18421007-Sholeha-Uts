package com.sholeha.aplikasipenjualanhelm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ornach.nobobutton.NoboButton;
import com.sholeha.aplikasipenjualanhelm.users.LoginActivity;

public class RegistActivity extends AppCompatActivity {

    NoboButton btnTerbang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);


        btnTerbang = (NoboButton) findViewById(R.id.btnTerbang);

        btnTerbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( RegistActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegistActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}