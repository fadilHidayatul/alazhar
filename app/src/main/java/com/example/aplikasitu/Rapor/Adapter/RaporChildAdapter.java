package com.example.aplikasitu.Rapor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Kelas.Grup;
import com.example.aplikasitu.Rapor.RaporDetailActivity;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.databinding.RowRaporChildBinding;

import java.util.List;

public class RaporChildAdapter extends RecyclerView.Adapter<RaporChildAdapter.viewHolder> {
    private Context context;
    private List<Grup.DATABean> listGrup;

    PrefManager manager;

    public RaporChildAdapter(Context context, List<Grup.DATABean> listGrup) {
        this.context = context;
        this.listGrup = listGrup;
    }

    @NonNull
    @Override
    public RaporChildAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRaporChildBinding view = RowRaporChildBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaporChildAdapter.viewHolder holder, int position) {
        manager = new PrefManager(context);
        holder.binding.grupKelas.setText(listGrup.get(position).getGrup());
        holder.binding.cardGrup.setOnClickListener(v -> {
            Intent intent = new Intent(context, RaporDetailActivity.class);
            intent.putExtra("grup",listGrup.get(position).getGrup());
            context.startActivity(intent);

//            Toast.makeText(context, manager.getKelas()+"-"+listGrup.get(position).getGrup(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return listGrup.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowRaporChildBinding binding;
        public viewHolder(RowRaporChildBinding rowRaporChildBinding) {
            super(rowRaporChildBinding.getRoot());
            this.binding = rowRaporChildBinding;
        }
    }
}
