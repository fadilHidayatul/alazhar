package com.example.aplikasitu.Siswa.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Siswa.Siswa;
import com.example.aplikasitu.databinding.RowSiswaBinding;

import java.util.ArrayList;
import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.viewHolder> implements Filterable {
    private Context context;
    private List<Siswa.DATABean> list;
    private List<Siswa.DATABean> filterList;

    public SiswaAdapter(Context context, List<Siswa.DATABean> list) {
        this.context = context;
        this.filterList = list;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowSiswaBinding view = RowSiswaBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final Siswa.DATABean model = filterList.get(position);

        holder.siswaBinding.namaSIswa.setText(model.getNama_siswa());
        holder.siswaBinding.jekelSiswa.setText(model.getGender());
        holder.siswaBinding.kls.setText(model.getKelas());
        holder.siswaBinding.grup.setText(model.getGrup());
        holder.siswaBinding.tmpt.setText(model.getTempat_lahir());
        holder.siswaBinding.tgl.setText(" / "+model.getTgl_lahir());
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSeq = constraint.toString();
                if (charSeq.isEmpty()){
                    filterList = list;
                }else{
                    List<Siswa.DATABean> filtered = new ArrayList<>();
                    for (Siswa.DATABean name : list){
                        if (name.getNama_siswa().toLowerCase().contains(charSeq.toLowerCase())){
                            filtered.add(name);
                        }
                        filterList = filtered;
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (List<Siswa.DATABean>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowSiswaBinding siswaBinding;

        public viewHolder(RowSiswaBinding rowSiswaBinding) {
            super(rowSiswaBinding.getRoot());
            this.siswaBinding = rowSiswaBinding;
        }
    }
}
