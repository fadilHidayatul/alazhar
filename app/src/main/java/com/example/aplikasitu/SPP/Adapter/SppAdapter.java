package com.example.aplikasitu.SPP.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.SPP.BeforeFragment;
import com.example.aplikasitu.SPP.SppDetailActivity;
import com.example.aplikasitu.SPP.SppSiswa;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.databinding.RowSppBinding;

import java.util.List;

public class SppAdapter extends RecyclerView.Adapter<SppAdapter.viewHolder> {
    private Context context;
    private List<SppSiswa.DATABean> listSppSiswa;
    PrefManager manager;

    public SppAdapter(Context context, List<SppSiswa.DATABean> listSppSiswa) {
        this.context = context;
        this.listSppSiswa = listSppSiswa;
    }

    @NonNull
    @Override
    public SppAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowSppBinding view = RowSppBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SppAdapter.viewHolder holder, int position) {
        manager = new PrefManager(context);
        holder.binding.no.setText(String.valueOf(listSppSiswa.get(position).getNo()));
        holder.binding.namaSiswa.setText(listSppSiswa.get(position).getNama_siswa());
        holder.binding.cardSppSiswa.setOnClickListener(v -> {
            manager.spSppSiswa(PrefManager.SISWA_SPP,listSppSiswa.get(position).getId_siswa());
            Intent intent = new Intent(v.getContext(), SppDetailActivity.class);
            context.startActivity(intent);
        });



    }

    @Override
    public int getItemCount() {
        return listSppSiswa.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowSppBinding binding;
        public viewHolder(RowSppBinding rowSppBinding) {
            super(rowSppBinding.getRoot());
            this.binding = rowSppBinding;
        }
    }
}
