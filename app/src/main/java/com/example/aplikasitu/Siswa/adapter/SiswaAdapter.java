package com.example.aplikasitu.Siswa.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Siswa.Siswa;
import com.example.aplikasitu.databinding.RowSiswaBinding;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.viewHolder> {
    private Context context;
    private List<Siswa.DATABean> list;

    public SiswaAdapter(Context context, List<Siswa.DATABean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowSiswaBinding view = RowSiswaBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.siswaBinding.namaSIswa.setText(list.get(position).getNama_siswa());
        holder.siswaBinding.jekelSiswa.setText(list.get(position).getGender());
        holder.siswaBinding.kls.setText(list.get(position).getKelas());
        holder.siswaBinding.grup.setText(list.get(position).getGrup());
        holder.siswaBinding.tmpt.setText(list.get(position).getTempat_lahir());
        holder.siswaBinding.tgl.setText(" / "+list.get(position).getTgl_lahir());
    }

    @Override
    public int getItemCount() {
        return (list!=null ? list.size() : 0);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowSiswaBinding siswaBinding;

        public viewHolder(RowSiswaBinding rowSiswaBinding) {
            super(rowSiswaBinding.getRoot());
            this.siswaBinding = rowSiswaBinding;
        }
    }
}
