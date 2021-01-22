package com.example.aplikasitu.Jadwal.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.aplikasitu.Kelas.Grup;
import com.example.aplikasitu.Kelas.Kelas;
import com.example.aplikasitu.R;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.RowJadwalBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.viewHolder> {

    private Context context;
    private List<Kelas.DATABean> listKelas;

    PrefManager manager;
    ApiInterface apiInterface;
    List<Grup.DATABean> dataBeans;


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
        manager = new PrefManager(context);
        apiInterface = UtilsApi.getApiService();

        holder.binding.txtKelas.setText("Kelas "+listKelas.get(position).getKelas());
        holder.binding.cardBesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.spKelas(PrefManager.KELAS,listKelas.get(position).getKelas());
                String kelas = listKelas.get(position).getKelas();
                if (holder.binding.expandableView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(holder.binding.cardBesar,new AutoTransition());
                    holder.binding.expandableView.setVisibility(View.VISIBLE);
                    holder.binding.arrow.setImageResource(R.drawable.ic_up_arrow);
                    openGrup(holder,kelas); //pilih grup
                }else{
                    TransitionManager.beginDelayedTransition(holder.binding.cardBesar,new AutoTransition());
                    holder.binding.expandableView.setVisibility(View.GONE);
                    holder.binding.arrow.setImageResource(R.drawable.ic_arrow_down_sign_to_navigate);
                }
            }
        });

       

    }

    private void openGrup(viewHolder holder, String kelas) {
        apiInterface.getGrupKelas(kelas).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equals("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();

                            for (int i = 0; i <array.length() ; i++) {
                                Grup.DATABean grup = gson.fromJson(array.getJSONObject(i).toString(),Grup.DATABean.class);
                                dataBeans.add(grup);
                            }

                            JadwalChildAdapter childAdapter = new JadwalChildAdapter(context,dataBeans);
                            holder.binding.recyclerJadwalChild.setAdapter(childAdapter);
                            holder.binding.recyclerJadwalChild.setLayoutManager(new LinearLayoutManager(context));
                            holder.binding.recyclerJadwalChild.setHasFixedSize(true);

                        }else{
                            Toast.makeText(context, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Toast.makeText(context, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });

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
