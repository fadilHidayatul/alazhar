package com.example.aplikasitu.Absen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Absen.AbsenDetailActivity;
import com.example.aplikasitu.Pegawai.Pegawai;
import com.example.aplikasitu.databinding.RowAbsenBinding;

import java.util.ArrayList;
import java.util.List;

public class AbsenAdapter extends RecyclerView.Adapter<AbsenAdapter.viewHolder> implements Filterable {
    private Context context;
    private List<Pegawai.DATABean> listPegawai;
    private List<Pegawai.DATABean> filterAbsen;

    public AbsenAdapter(Context context, List<Pegawai.DATABean> listPegawai) {
        this.context = context;
        this.listPegawai = listPegawai;
        this.filterAbsen = listPegawai;
    }

    @NonNull
    @Override
    public AbsenAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowAbsenBinding view = RowAbsenBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenAdapter.viewHolder holder, int position) {
        holder.binding.namaPegawai.setText(filterAbsen.get(position).getNama_pegawai());
        holder.binding.cardPegawai.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AbsenDetailActivity.class);
            intent.putExtra("id",filterAbsen.get(position).getId());
            intent.putExtra("nama",filterAbsen.get(position).getNama_pegawai());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filterAbsen.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String seq = constraint.toString();
                if (seq.isEmpty()){
                    filterAbsen = listPegawai;
                }else {
                    List<Pegawai.DATABean> filtered = new ArrayList<>();
                    for (Pegawai.DATABean name : listPegawai){
                        if (name.getNama_pegawai().toLowerCase().contains(seq.toLowerCase())){
                            filtered.add(name);
                        }
                        filterAbsen = filtered;
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filterAbsen;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterAbsen = (List<Pegawai.DATABean>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowAbsenBinding binding;
        public viewHolder(RowAbsenBinding rowAbsenBinding) {
            super(rowAbsenBinding.getRoot());
            this.binding = rowAbsenBinding;
        }
    }
}
