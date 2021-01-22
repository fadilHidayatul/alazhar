package com.example.aplikasitu.Jadwal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Kelas.Grup;
import com.example.aplikasitu.Kelas.Kelas;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.databinding.RowChildJadwalBinding;

import java.util.List;

public class JadwalChildAdapter extends RecyclerView.Adapter<JadwalChildAdapter.viewHolder> {
    private Context context;
    private List<Grup.DATABean> listGrup;
    PrefManager manager;


    public JadwalChildAdapter(Context context, List<Grup.DATABean> listGrup) {
        this.context = context;
        this.listGrup = listGrup;
    }

    @NonNull
    @Override
    public JadwalChildAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowChildJadwalBinding view = RowChildJadwalBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalChildAdapter.viewHolder holder, int position) {
       manager = new PrefManager(context);

        holder.binding.txtGrup.setText(listGrup.get(position).getGrup());
        holder.binding.cardChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, manager.getKelas()+listGrup.get(position).getGrup(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listGrup.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowChildJadwalBinding binding;
        public viewHolder(RowChildJadwalBinding rowChildJadwalBinding) {
            super(rowChildJadwalBinding.getRoot());
            this.binding = rowChildJadwalBinding;
        }
    }
}
