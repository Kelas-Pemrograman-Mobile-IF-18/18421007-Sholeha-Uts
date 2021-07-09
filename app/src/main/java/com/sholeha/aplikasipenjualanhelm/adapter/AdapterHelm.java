package com.sholeha.aplikasipenjualanhelm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sholeha.aplikasipenjualanhelm.R;
import com.sholeha.aplikasipenjualanhelm.model.ModelHelm;
import com.sholeha.aplikasipenjualanhelm.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHelm extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelHelm> item;

    public AdapterHelm(Activity activity, List<ModelHelm> item) {
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
            convertView = inflater.inflate(R.layout.content_helm, null);


        TextView namaHelm = (TextView) convertView.findViewById(R.id.txtNamaHelm);
        TextView warnaHelm     = (TextView) convertView.findViewById(R.id.txtWarnaHelm);
        TextView ukuranHelm          = (TextView) convertView.findViewById(R.id.txtUkuranHelm);
        TextView tahunProduksi         = (TextView) convertView.findViewById(R.id.txtTahunProduksi);
        TextView hargaHelm         = (TextView) convertView.findViewById(R.id.txtHargaHelm);
        ImageView gambarHelm         = (ImageView) convertView.findViewById(R.id.gambarHelm);

        namaHelm.setText(item.get(position).getNamaHelm());
        warnaHelm.setText(item.get(position).getWarnaHelm());
        ukuranHelm.setText(item.get(position).getUkuranHelm());
        tahunProduksi.setText("Rp." + item.get(position).getTahunProduksi());
        hargaHelm.setText(item.get(position).getHargaHelm());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarHelm);
        return convertView;
    }
}
