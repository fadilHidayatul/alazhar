package com.example.aplikasitu.Rapor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Rapor.ShowRaporActivity;
import com.example.aplikasitu.Rapor.SiswaByKelas;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.databinding.RowRaporSiswaBinding;

import java.util.List;

public class RaporDetailAdapter extends RecyclerView.Adapter<RaporDetailAdapter.viewHolder> {
    private Context context;
    private List<SiswaByKelas.DATABean> listSiswa;
    private String grup;

    PrefManager manager;


    public RaporDetailAdapter(Context context, List<SiswaByKelas.DATABean> listSiswa, String grup) {
        this.context = context;
        this.listSiswa = listSiswa;
        this.grup = grup;
    }

    @NonNull
    @Override
    public RaporDetailAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRaporSiswaBinding view = RowRaporSiswaBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaporDetailAdapter.viewHolder holder, int position) {
        manager = new PrefManager(context);

        holder.binding.namaSiswa.setText(listSiswa.get(position).getSiswa());
        holder.binding.no.setText(""+listSiswa.get(position).getNo());
        holder.binding.cardSiswa.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowRaporActivity.class);
            intent.putExtra("idSiswa",listSiswa.get(position).getId_siswa());
            intent.putExtra("kelas",manager.getKelas());
            intent.putExtra("grup",grup);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listSiswa.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowRaporSiswaBinding binding;
        public viewHolder(RowRaporSiswaBinding rowRaporSiswaBinding) {
            super(rowRaporSiswaBinding.getRoot());
            this.binding = rowRaporSiswaBinding;
        }
    }
}
