package com.example.lutfi.crudmahasiswa.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lutfi.crudmahasiswa.Model.Mahasiswa;
import com.example.lutfi.crudmahasiswa.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MahasiswaAdapter extends ArrayAdapter<Mahasiswa> {
    public MahasiswaAdapter(@NonNull Context context, int resource,
                            @NonNull List<Mahasiswa> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from((getContext()))
                    .inflate(R.layout.item_mahasiswa, parent, false);
        }

        // String user = getItem(position);
        Mahasiswa mahasiswa = getItem(position);
        TextView tvNama = convertView.findViewById(R.id.tv_nama);
        tvNama.setText(mahasiswa.getNama());

        TextView tvNim = convertView.findViewById((R.id.tv_nim));
        tvNim.setText(mahasiswa.getNim());

        TextView tvEmail = convertView.findViewById(R.id.tv_email);
        tvEmail.setText(mahasiswa.getEmail());

        ImageView img = convertView.findViewById(R.id.iv_foto);
        Log.e("CRUD", "Foto: http://")
        Picasso.get().load(data.get(position).getFoto())
                .resize(50,50).into(img);

        return convertView;
    }
}
