package com.sholeha.aplikasipenjualanhelm.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.RegistActivity;

public class HomeUserActivity extends AppCompatActivity {

CardView btnMenuKlasik, btnMenuModern, btnMenuSepeda, btnMenuProyek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);


        btnMenuKlasik= (CardView) findViewById(R.id.btnMenuKlasik);
        btnMenuModern= (CardView) findViewById(R.id.btnMenuModern);
        btnMenuSepeda= (CardView) findViewById(R.id.btnMenuSepeda);
        btnMenuProyek= (CardView) findViewById(R.id.btnMenuProyek);

        btnMenuKlasik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeUserActivity.this, MenuHelmKlasik.class);
                startActivity(i);
                finish();
            }
        });

        btnMenuModern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeUserActivity.this, MenuHelmModernActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnMenuSepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeUserActivity.this, MenuHelmSepedaActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnMenuProyek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeUserActivity.this, MenuHelmProyekActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}