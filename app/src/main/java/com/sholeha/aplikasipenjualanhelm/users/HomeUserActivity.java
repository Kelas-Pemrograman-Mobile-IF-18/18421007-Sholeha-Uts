package com.sholeha.aplikasipenjualanhelm.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.RegistActivity;
import com.sholeha.aplikasipenjualanhelm.adapter.AdapterHelm;
import com.sholeha.aplikasipenjualanhelm.model.ModelHelm;
import com.sholeha.aplikasipenjualanhelm.server.BaseURL;
import com.sholeha.aplikasipenjualanhelm.session.PrefSetting;
import com.sholeha.aplikasipenjualanhelm.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeUserActivity extends AppCompatActivity {
    Button btnpesan;
    TextView txtNama;

    ProgressDialog pDialog;

    AdapterHelm adapter;
    ListView list;

    ArrayList<ModelHelm> newsList = new ArrayList<ModelHelm>();
    private RequestQueue mRequestQueue;


    FloatingActionButton floatingExit;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);



        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferances();

        session = new SessionManager(HomeUserActivity.this);

        prefSetting.isLogin(session, prefs);

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);

        btnpesan = (Button) findViewById(R.id.btnpesan);
        floatingExit = (FloatingActionButton) findViewById(R.id.exit);

        newsList.clear();
        adapter = new AdapterHelm(HomeUserActivity.this, newsList);
        list.setAdapter(adapter);
        getAllHelm();

//        txtNama = (TextView) findViewById(R.id.txtNama);
//        txtNama.setText(PrefSetting.namaLengkap);

        floatingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeUserActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeUserActivity.this, PesananUser.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void getAllHelm() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataHelm, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data helm = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelHelm helm = new ModelHelm();
                                    final String _id = jsonObject.getString("_id");
                                    final String kodeHelm = jsonObject.getString("kodeHelm");
                                    final String namaHelm = jsonObject.getString("namaHelm");
                                    final String warnaHelm = jsonObject.getString("warnaHelm");
                                    final String ukuranHelm = jsonObject.getString("ukuranHelm");
                                    final String tahunProduksi = jsonObject.getString("tahunProduksi");
                                    final String hargaHelm = jsonObject.getString("hargaHelm");
                                    final String gambar = jsonObject.getString("gambar");
                                    helm.setKodeHelm(kodeHelm);
                                    helm.setNamaHelm(namaHelm);
                                    helm.setWarnaHelm(warnaHelm);
                                    helm.setUkuranHelm(ukuranHelm);
                                    helm.setTahunProduksi(tahunProduksi);
                                    helm.setHargaHelm(hargaHelm);
                                    helm.setGambar(gambar);
                                    helm.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(HomeUserActivity.this, DetailUser.class);
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("kodeHelm", newsList.get(position).getKodeHelm());
                                            a.putExtra("namaHelm", newsList.get(position).getNamaHelm());
                                            a.putExtra("warnaHelm", newsList.get(position).getWarnaHelm());
                                            a.putExtra("ukuranHelm", newsList.get(position).getUkuranHelm());
                                            a.putExtra("tahunProduksi", newsList.get(position).getTahunProduksi());
                                            a.putExtra("hargaHelm", newsList.get(position).getHargaHelm());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(helm);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}