package com.sholeha.aplikasipenjualanhelm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.model.ModelOrder;

import java.util.List;

public class AdapterOrder extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelOrder> item;

    public AdapterOrder(Activity activity, List<ModelOrder> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_order, null);


        TextView namaPembeli = (TextView) convertView.findViewById(R.id.txtnamaUser);
        TextView namaBarang     = (TextView) convertView.findViewById(R.id.txtnamaBarang);
        TextView jumlah         = (TextView) convertView.findViewById(R.id.txtjumlahBarang);
        TextView harga         = (TextView) convertView.findViewById(R.id.txtharga);
        TextView total    = (TextView) convertView.findViewById(R.id.txttotalBelanja);
        TextView status        = (TextView) convertView.findViewById(R.id.status);
        LinearLayout statusLinear = (LinearLayout) convertView.findViewById(R.id.statusLinear);

        namaPembeli.setText(item.get(position).getNamaUser());
        namaBarang.setText(item.get(position).getNamaBarang());
        harga.setText("Rp." + item.get(position).getHarga());
        jumlah.setText(item.get(position).getJumlah());
        total.setText(item.get(position).getTotal());
        status.setText(item.get(position).getStatus());
        if (item.get(position).getStatus().equals("sedang diproses")) {
            statusLinear.setBackgroundResource(R.drawable.buttonmerah);
        }else if (item.get(position).getStatus().equals("Pesanan Diterima")) {
            statusLinear.setBackgroundResource(R.drawable.buttonhijau);
        } else {
            statusLinear.setBackgroundResource(R.drawable.buttonorange);
        }
        return convertView;
    }
}

