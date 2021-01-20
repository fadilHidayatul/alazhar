package com.example.aplikasitu.Kelas.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Kelas.Kelas;
import com.example.aplikasitu.R;
import com.example.aplikasitu.databinding.RowKelasBinding;

import java.util.List;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.viewHolder> {
    private Context context;
    private List<Kelas.DATABean> listKelas;

    public KelasAdapter(Context context, List<Kelas.DATABean> listKelas) {
        this.context = context;
        this.listKelas = listKelas;
    }

    @NonNull
    @Override
    public KelasAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowKelasBinding view = RowKelasBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull KelasAdapter.viewHolder holder, int position) {
        holder.binding.kelas.setText("Kelas : "+listKelas.get(position).getKelas()+" "+listKelas.get(position).getGrup());
        holder.binding.jmlMurid.setText(""+listKelas.get(position).getJumlah_murid());
    }

    @Override
    public int getItemCount() {
        return listKelas.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowKelasBinding binding;

        public viewHolder(RowKelasBinding rowKelasBinding) {
            super(rowKelasBinding.getRoot());
            this.binding = rowKelasBinding;
        }

    }




}
