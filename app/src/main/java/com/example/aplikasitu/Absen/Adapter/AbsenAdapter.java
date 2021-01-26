package com.example.aplikasitu.Absen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Absen.AbsenDetailActivity;
import com.example.aplikasitu.Pegawai.Pegawai;
import com.example.aplikasitu.databinding.RowAbsenBinding;

import java.util.List;

public class AbsenAdapter extends RecyclerView.Adapter<AbsenAdapter.viewHolder> {
    private Context context;
    private List<Pegawai.DATABean> listPegawai;

    public AbsenAdapter(Context context, List<Pegawai.DATABean> listPegawai) {
        this.context = context;
        this.listPegawai = listPegawai;
    }

    @NonNull
    @Override
    public AbsenAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowAbsenBinding view = RowAbsenBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenAdapter.viewHolder holder, int position) {
        holder.binding.namaPegawai.setText(listPegawai.get(position).getNama_pegawai());
        holder.binding.cardPegawai.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AbsenDetailActivity.class);
            intent.putExtra("id",listPegawai.get(position).getId());
            intent.putExtra("nama",listPegawai.get(position).getNama_pegawai());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listPegawai.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowAbsenBinding binding;
        public viewHolder(RowAbsenBinding rowAbsenBinding) {
            super(rowAbsenBinding.getRoot());
            this.binding = rowAbsenBinding;
        }
    }
}
