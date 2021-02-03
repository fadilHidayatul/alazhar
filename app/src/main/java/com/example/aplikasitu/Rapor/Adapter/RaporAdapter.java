package com.example.aplikasitu.Rapor.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.aplikasitu.databinding.RowRaporBinding;
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


public class RaporAdapter extends RecyclerView.Adapter<RaporAdapter.viewHolder> {

    private Context context;
    private List<Kelas.DATABean> listKelas;

    ApiInterface apiInterface;
    PrefManager manager;
    List<Grup.DATABean> dataBeans;
    RaporChildAdapter adapter;

    public RaporAdapter(Context context, List<Kelas.DATABean> listKelas) {
        this.context = context;
        this.listKelas = listKelas;
    }

    @NonNull
    @Override
    public RaporAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRaporBinding view = RowRaporBinding.inflate(LayoutInflater.from(context),parent,false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RaporAdapter.viewHolder holder, int position) {
        apiInterface = UtilsApi.getApiService();
        manager = new PrefManager(context);

        holder.binding.txtKelas.setText("Kelas "+listKelas.get(position).getKelas());
        holder.binding.mainCard.setOnClickListener(v -> {
            if (holder.binding.expandableKelas.getVisibility() == View.GONE){
                manager.spKelas(PrefManager.KELAS,listKelas.get(position).getKelas());
                String kelas = listKelas.get(position).getKelas();

                TransitionManager.beginDelayedTransition(holder.binding.mainCard, new AutoTransition());
                holder.binding.expandableKelas.setVisibility(View.VISIBLE);
                holder.binding.arrow.setImageResource(R.drawable.ic_up_arrow);
                showGrup(holder,kelas);
            }else{
                TransitionManager.beginDelayedTransition(holder.binding.mainCard, new AutoTransition());
                holder.binding.expandableKelas.setVisibility(View.GONE);
                holder.binding.arrow.setImageResource(R.drawable.ic_arrow_down_sign_to_navigate);
            }
        });
    }

    private void showGrup(viewHolder holder, String kelas) {
        apiInterface.getGrupKelas(kelas).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();

                            for (int i = 0; i <array.length() ; i++) {
                                Grup.DATABean grup = gson.fromJson(array.getJSONObject(i).toString(), Grup.DATABean.class);
                                dataBeans.add(grup);
                            }

                            adapter = new RaporChildAdapter(context,dataBeans);
                            holder.binding.recyclerRowKelas.setAdapter(adapter);
                            holder.binding.recyclerRowKelas.setLayoutManager(new LinearLayoutManager(context));
                            holder.binding.recyclerRowKelas.setHasFixedSize(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listKelas.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RowRaporBinding binding;
        public viewHolder(RowRaporBinding rowRaporBinding) {
            super(rowRaporBinding.getRoot());
            this.binding = rowRaporBinding;
        }
    }
}
