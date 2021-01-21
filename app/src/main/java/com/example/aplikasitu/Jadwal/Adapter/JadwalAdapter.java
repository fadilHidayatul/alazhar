package com.example.aplikasitu.Jadwal.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Jadwal.JadwalDetailActivity;
import com.example.aplikasitu.Kelas.Kelas;
import com.example.aplikasitu.R;
import com.example.aplikasitu.databinding.RowJadwalBinding;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.viewHolder> {

    private Context context;
    private List<Kelas.DATABean> listKelas;


    public JadwalAdapter(Context context, List<Kelas.DATABean> listKelas) {
        this.context = context;
        this.listKelas = listKelas;
    }

    @NonNull
    @Override
    public JadwalAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowJadwalBinding view = RowJadwalBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull JadwalAdapter.viewHolder holder, int position) {
        holder.binding.txtKelas.setText("Kelas"+listKelas.get(position).getKelas());

        holder.binding.cardBesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.expandableView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(holder.binding.cardBesar,new AutoTransition());
                    holder.binding.expandableView.setVisibility(View.VISIBLE);
                    holder.binding.arrow.setImageResource(R.drawable.ic_up_arrow);
                }else{
                    TransitionManager.beginDelayedTransition(holder.binding.cardBesar,new AutoTransition());
                    holder.binding.expandableView.setVisibility(View.GONE);
                    holder.binding.arrow.setImageResource(R.drawable.ic_arrow_down_sign_to_navigate);
                }
            }
        });

        JadwalChildAdapter childAdapter = new JadwalChildAdapter(context);
        holder.binding.recyclerJadwalChild.setAdapter(childAdapter);
        holder.binding.recyclerJadwalChild.setLayoutManager(new LinearLayoutManager(context));
        holder.binding.recyclerJadwalChild.setHasFixedSize(true);

    }

    @Override
    public int getItemCount() {
        return listKelas.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowJadwalBinding binding;
        public viewHolder(RowJadwalBinding rowJadwalBinding) {
            super(rowJadwalBinding.getRoot());
            this.binding = rowJadwalBinding;
        }
    }
}
