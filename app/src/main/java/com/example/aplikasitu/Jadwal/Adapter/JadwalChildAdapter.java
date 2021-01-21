package com.example.aplikasitu.Jadwal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.databinding.RowChildJadwalBinding;

public class JadwalChildAdapter extends RecyclerView.Adapter<JadwalChildAdapter.viewHolder> {
    private Context context;

    public JadwalChildAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public JadwalChildAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowChildJadwalBinding view = RowChildJadwalBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalChildAdapter.viewHolder holder, int position) {
        holder.binding.txtGrup.setText("Al-Farabi");
        holder.binding.cardChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "-"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowChildJadwalBinding binding;
        public viewHolder(RowChildJadwalBinding rowChildJadwalBinding) {
            super(rowChildJadwalBinding.getRoot());
            this.binding = rowChildJadwalBinding;
        }
    }
}
