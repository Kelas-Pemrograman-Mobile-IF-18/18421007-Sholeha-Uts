package com.sholeha.aplikasipenjualanhelm.users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.RegistActivity;
import com.sholeha.aplikasipenjualanhelm.server.BaseURL;
import com.sholeha.aplikasipenjualanhelm.session.PrefSetting;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.sholeha.aplikasipenjualanhelm.server.BaseURL.order;
import static com.sholeha.aplikasipenjualanhelm.session.PrefSetting.Username;

public class DetailUser extends AppCompatActivity {
    TextView txtKodeHelm,txtNamaHelm, txtUkuranHelm, txtWarnaHelm, txtTahunProduksi, txtharga;
    EditText edtjumlah;
    ImageView gambar;
    Button btnpesan;

    String userName;

    private RequestQueue mRequestQueue;

    ProgressDialog pDialog;

    String strKodeHelm, strNamaHelm, strWarnaHelm, strUkuranHelm, strTahunProduksi, strHargaHelm, strGamabr, _id, strjumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_helm);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        userName = Username;
        mRequestQueue = Volley.newRequestQueue(this);

        txtKodeHelm = (TextView) findViewById(R.id.txtKodeHelm);
        txtNamaHelm = (TextView) findViewById(R.id.txtNamaHelm);
        txtUkuranHelm = (TextView) findViewById(R.id.txtUkuranHelm);
        txtWarnaHelm = (TextView) findViewById(R.id.txtWarnaHelm);
        txtTahunProduksi = (TextView) findViewById(R.id.txtTahunProduksi);
        edtjumlah = (EditText) findViewById(R.id.edtjumlah);
        txtharga = (TextView) findViewById(R.id.txtharga);
        btnpesan = (Button) findViewById(R.id.btnpesan);
        gambar = (ImageView) findViewById(R.id.gambar);

        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strKodeHelm = txtKodeHelm.getText().toString();
                strNamaHelm = txtNamaHelm.getText().toString();
                strWarnaHelm = txtWarnaHelm.getText().toString();
                strUkuranHelm = txtUkuranHelm.getText().toString();
                strTahunProduksi = txtTahunProduksi.getText().toString();
                strHargaHelm = txtharga.getText().toString();
                strjumlah = edtjumlah.getText().toString();
                int Total = Integer.parseInt(strHargaHelm) * Integer.parseInt(strjumlah);


                order(strNamaHelm, strHargaHelm, strjumlah, Total);
            }
        });

        Intent i = getIntent();
        strKodeHelm = i.getStringExtra("kodeHelm");
        strNamaHelm = i.getStringExtra("namaHelm");
        strWarnaHelm = i.getStringExtra("warnaHelm");
        strUkuranHelm = i.getStringExtra("ukuranHelm");
        strTahunProduksi = i.getStringExtra("tahunProduksi");
        strHargaHelm = i.getStringExtra("hargaHelm");
        strGamabr = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        txtKodeHelm.setText(strKodeHelm);
        txtNamaHelm.setText(strNamaHelm);
        txtWarnaHelm.setText(strWarnaHelm);
        txtUkuranHelm.setText(strUkuranHelm);
        txtTahunProduksi.setText(strTahunProduksi);
        txtharga.setText(strHargaHelm);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGamabr)
                .into(gambar);

    }
            private void order(String namaHelm, String harga, String jumlah, int total) {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Username", Username);
                params.put("namaHelm", namaHelm);
                params.put("harga", harga);
                params.put("jumlah", jumlah);
                params.put("status", "sedang diproses");
                params.put("total", String.valueOf(total));

                pDialog.setMessage("Mohon Tunggu....");
                showDialog();
                Log.e("params", String.valueOf(params));

                JsonObjectRequest req = new JsonObjectRequest(BaseURL.order, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                hideDialog();
                                try {
                                    String strMsg = response.getString("msg");
                                    boolean status = response.getBoolean("error");
                                    if (status == false) {
                                        Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(DetailUser.this, PesananUser.class);
                                        startActivity(i);
                                        finish();
                                    } else {
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

            private void showDialog() {
                if (!pDialog.isShowing()) {
                    pDialog.show();
                }
            }

            private void hideDialog() {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();

                }
            }
        }