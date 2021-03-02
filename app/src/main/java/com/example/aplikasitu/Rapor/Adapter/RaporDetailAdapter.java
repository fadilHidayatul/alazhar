package com.example.aplikasitu.Rapor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitu.Rapor.RaporDetailActivity;
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
        FragmentManager fragmentManager = ((RaporDetailActivity) context).getSupportFragmentManager();

        holder.binding.namaSiswa.setText(listSiswa.get(position).getSiswa()); //masih B
        holder.binding.no.setText(""+listSiswa.get(position).getNo());
        holder.binding.cardSiswa.setOnClickListener(v -> {
            manager.spKelas(PrefManager.ID_SISWA_RAPOR,listSiswa.get(position).getId_siswa());
            manager.spGrupKelasRapor(PrefManager.GRUP_RAPOR,grup);

            SemesterDialog dialog = new SemesterDialog();
            dialog.show(fragmentManager, "Semester Dialog");
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
