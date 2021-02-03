package com.example.aplikasitu.Rapor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplikasitu.Rapor.Adapter.RaporDetailAdapter;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityRaporDetailBinding;
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

public class RaporDetailActivity extends AppCompatActivity {
    private ActivityRaporDetailBinding binding;
    Context context;
    ApiInterface apiInterface;
    PrefManager manager;
    List<SiswaByKelas.DATABean> dataBeans;
    RaporDetailAdapter adapter;

    String grup = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRaporDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        apiInterface = UtilsApi.getApiService();
        manager = new PrefManager(context);

        Intent intent = getIntent();
        grup = intent.getStringExtra("grup");


        binding.kls.setText(manager.getKelas());
        binding.grp.setText(" "+grup);

        showSiswaKelas();
    }

    private void showSiswaKelas() {
        apiInterface.getSiswaByKelas(manager.getKelas(),grup).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();

                            for (int i = 0; i < array.length(); i++) {
                                SiswaByKelas.DATABean kelas = gson.fromJson(array.getJSONObject(i).toString(),SiswaByKelas.DATABean.class);
                                dataBeans.add(kelas);
                            }

                            adapter = new RaporDetailAdapter(context,dataBeans,grup);
                            binding.recyclerRaporSiswa.setAdapter(adapter);
                            binding.recyclerRaporSiswa.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerRaporSiswa.setHasFixedSize(true);
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
}
