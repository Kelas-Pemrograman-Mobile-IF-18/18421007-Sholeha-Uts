package com.sholeha.aplikasipenjualanhelm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ornach.nobobutton.NoboButton;
import com.sholeha.aplikasipenjualanhelm.server.BaseURL;
import com.sholeha.aplikasipenjualanhelm.users.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistActivity extends AppCompatActivity {

    NoboButton btnTerbang;
    Button btnbacklogin;
    ProgressDialog pDialog;
    EditText edtUsername, edtPassword, edtNoTelpon, edtAlamat;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mRequestQueue = Volley.newRequestQueue(this);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtNoTelpon = (EditText) findViewById(R.id.edtNoTelp);
        edtAlamat = (EditText) findViewById(R.id.edtAlamat);


        btnbacklogin = (Button) findViewById(R.id.btnbacklogin);
        btnTerbang = (NoboButton) findViewById(R.id.btnTerbang);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( RegistActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnTerbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = edtUsername.getText().toString();
                String strPassword = edtPassword.getText().toString();
                String strNoTelpon = edtNoTelpon.getText().toString();
                String strAlamat = edtAlamat.getText().toString();

                if(strUsername.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if (strPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if (strNoTelpon.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nomor Telepon Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if (strAlamat.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Alamat Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else{
                    registrasi(strUsername, strPassword, strNoTelpon, strAlamat);
                }
            }
        });

    }

    public void registrasi(String Username, String Password, String NoTelpon, String Alamat){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Username", Username);
        params.put("Password", Password);
        params.put("NoTelpon", NoTelpon);
        params.put("role", "2");
        params.put("Alamat", Alamat);


        pDialog.setMessage("Mohon Tungu :) ");
        showDialog();


        JsonObjectRequest req = new JsonObjectRequest(BaseURL.register, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString(("msg"));
                            boolean status= response.getBoolean("error");

                            if(status== false) {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegistActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

// add the request object to the queue to be executed
        mRequestQueue.add(req);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(RegistActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

}