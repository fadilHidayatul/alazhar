package com.example.aplikasitu.Jadwal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Jadwal.Jadwal;
import com.example.aplikasitu.databinding.RowJadwalShowBinding;

import java.util.List;

public class JadwalShowAdapter extends RecyclerView.Adapter<JadwalShowAdapter.viewHolder> {
    private Context context;
    private List<Jadwal.DATABean> listJadwal;

    public JadwalShowAdapter(Context context, List<Jadwal.DATABean> listJadwal) {
        this.context = context;
        this.listJadwal = listJadwal;
    }

    @NonNull
    @Override
    public JadwalShowAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowJadwalShowBinding view = RowJadwalShowBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalShowAdapter.viewHolder holder, int position) {
        holder.binding.matpel.setText(listJadwal.get(position).getMatpel());
        holder.binding.jamAwal.setText(listJadwal.get(position).getJam_awal().substring(0,5)+" - ");
        holder.binding.jamAkhir.setText(listJadwal.get(position).getJam_akhir().substring(03,5));

    }

    @Override
    public int getItemCount() {
        return listJadwal.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowJadwalShowBinding binding;
        public viewHolder(RowJadwalShowBinding rowJadwalShowBinding) {
            super(rowJadwalShowBinding.getRoot());
            this.binding = rowJadwalShowBinding;
        }
    }
}
