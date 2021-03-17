package com.example.aplikasitu.Absen.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Absen.Absen;
import com.example.aplikasitu.databinding.RowAbsenDetailBinding;

import java.util.List;

public class AbsenDetailAdapter extends RecyclerView.Adapter<AbsenDetailAdapter.viewHolder> {
    private Context context;
    private List<Absen.DATABean> listAbsen;

    public AbsenDetailAdapter(Context context, List<Absen.DATABean> listAbsen) {
        this.context = context;
        this.listAbsen = listAbsen;
    }

    @NonNull
    @Override
    public AbsenDetailAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowAbsenDetailBinding view = RowAbsenDetailBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AbsenDetailAdapter.viewHolder holder, int position) {
        holder.binding.tgl.setText(listAbsen.get(position).getTanggal().substring(8,10));
        holder.binding.bln.setText(" "+listAbsen.get(position).getBulan());
        holder.binding.thn.setText(" "+listAbsen.get(position).getTanggal().substring(0,4));

        holder.binding.JAawal.setText(listAbsen.get(position).getJam_ajar_awal().substring(0,5));
        holder.binding.JAakhir.setText(" - "+listAbsen.get(position).getJam_ajar_akhir().substring(0,5));
        holder.binding.JMasuk.setText(listAbsen.get(position).getJam_masuk().substring(0,5));
        holder.binding.JKeluar.setText(listAbsen.get(position).getJam_keluar().substring(0,5));
    }

    @Override
    public int getItemCount() {
        return listAbsen.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RowAbsenDetailBinding binding;
        public viewHolder(RowAbsenDetailBinding rowAbsenDetailBinding) {
            super(rowAbsenDetailBinding.getRoot());
            this.binding = rowAbsenDetailBinding;
        }
    }
}
