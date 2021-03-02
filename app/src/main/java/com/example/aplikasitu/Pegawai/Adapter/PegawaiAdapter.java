package com.example.aplikasitu.Pegawai.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplikasitu.Pegawai.Pegawai;
import com.example.aplikasitu.R;
import com.example.aplikasitu.Siswa.Siswa;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityPegawaiBinding;
import com.example.aplikasitu.databinding.RowPegawaiBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.viewHolder> implements Filterable {
    private Context context;
    private List<Pegawai.DATABean> list;
    private List<Pegawai.DATABean> filterList;

    public PegawaiAdapter(Context context, List<Pegawai.DATABean> list) {
        this.context = context;
        this.list = list;
        this.filterList = list;
    }

    @NonNull
    @Override
    public PegawaiAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPegawaiBinding view = RowPegawaiBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PegawaiAdapter.viewHolder holder, int position) {

        holder.pegawaiBinding.namaPegawai.setText(filterList.get(position).getNama_pegawai());
        holder.pegawaiBinding.nipPegawai.setText(filterList.get(position).getNip());
        holder.pegawaiBinding.genderPegawai.setText(filterList.get(position).getGender());
        holder.pegawaiBinding.email.setText(filterList.get(position).getEmail());
        holder.pegawaiBinding.nohp.setText("No Hp : "+filterList.get(position).getNohp());
        Glide.with(context)
                .load(UtilsApi.imgPegawai+filterList.get(position).getFoto())
                .centerCrop()
                .placeholder(R.drawable.ic_officer)
                .override(300,300)
                .into(holder.pegawaiBinding.menu1);
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
                }else {
                    List<Pegawai.DATABean> filtered = new ArrayList<>();
                    for (Pegawai.DATABean name : list){
                        if (name.getNama_pegawai().toLowerCase().contains(charSeq.toLowerCase())){
                            filtered.add(name);
                        }
                        filterList = filtered;
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filterList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (List<Pegawai.DATABean>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowPegawaiBinding pegawaiBinding;
        public viewHolder(RowPegawaiBinding rowPegawaiBinding) {
            super(rowPegawaiBinding.getRoot());
            this.pegawaiBinding = rowPegawaiBinding;

        }
    }
}
