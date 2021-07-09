package com.sholeha.aplikasipenjualanhelm.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.adapter.AdapterHelm;
import com.sholeha.aplikasipenjualanhelm.model.ModelHelm;
import com.sholeha.aplikasipenjualanhelm.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityDataHelm extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterHelm adapter;
    ListView list;

    ArrayList<ModelHelm> newsList = new ArrayList<ModelHelm>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_helm);

//        getSupportActionBar().setTitle("Data Helm");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterHelm(ActivityDataHelm.this, newsList);
        list.setAdapter(adapter);
        getAllHelm();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ActivityDataHelm.this, HomeAdminActivity.class);
        startActivity(i);
        finish();
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
                                    final String namaHelm = jsonObject.getString("namaHelm");
                                    final String warnaHelm = jsonObject.getString("warnaHelm");
                                    final String ukuranHelm = jsonObject.getString("ukuranHelm");
                                    final String tahunProduksi = jsonObject.getString("tahunProduksi");
                                    final String hargaHelm = jsonObject.getString("hargaHelm");
                                    final String gambar = jsonObject.getString("gambar");
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
                                            Intent a = new Intent(ActivityDataHelm.this, EditHelmDanHapusActivity.class);
                                            a.putExtra("namaHelm", newsList.get(position).getNamaHelm());
                                            a.putExtra("_id", newsList.get(position).get_id());
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