package com.example.aplikasitu.Jadwal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.Jadwal.Adapter.JadwalShowAdapter;
import com.example.aplikasitu.R;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityShowJadwalBinding;
import com.google.android.material.snackbar.Snackbar;
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

public class ShowJadwalActivity extends AppCompatActivity {
    private ActivityShowJadwalBinding binding;
    Context context;
    JadwalShowAdapter adapter;
    ApiInterface apiInterface;
    List<Jadwal.DATABean> dataBeans;

    String kelas = "";
    String grup = "";
    String hari = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowJadwalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        apiInterface = UtilsApi.getApiService();

        Intent intent = getIntent();
        kelas = intent.getStringExtra("kelas");
        grup = intent.getStringExtra("grup");

        if (hari.isEmpty()){
            hari = "senin";
        }

        pilihHari();
        showSchedule(kelas,grup,hari);

    }

    private void showSchedule(String kelas, String grup, String hari ) {
        apiInterface.getJadwal(kelas, grup, hari).enqueue(new Callback<ResponseBody>() {
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
                                Jadwal.DATABean jadwal = gson.fromJson(array.getJSONObject(i).toString(),Jadwal.DATABean.class);
                                dataBeans.add(jadwal);
                            }

                            adapter = new JadwalShowAdapter(context,dataBeans);
                            binding.rcShow.setAdapter(adapter);
                            binding.rcShow.setLayoutManager(new LinearLayoutManager(context));
                            binding.rcShow.setHasFixedSize(true);
                            binding.rcShow.setVisibility(View.VISIBLE);
                            binding.warning.setVisibility(View.GONE);
                        }else{
                            binding.rcShow.setVisibility(View.GONE);
                            binding.warning.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
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

    @SuppressLint("ResourceAsColor")
    private void pilihHari() {
        binding.senin.setOnClickListener(v -> {
            hari = binding.senin.getText().toString();
            binding.senin.setTextColor(this.getResources().getColor(R.color.softred2));
            binding.selasa.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.rabu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.kamis.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.jumat.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.sabtu.setTextColor(this.getResources().getColor(R.color.colorPrimary));

            showSchedule(kelas,grup,hari);
        });
        binding.selasa.setOnClickListener(v -> {
            hari = binding.selasa.getText().toString();
            binding.senin.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.selasa.setTextColor(this.getResources().getColor(R.color.softred2));
            binding.rabu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.kamis.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.jumat.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.sabtu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            showSchedule(kelas,grup,hari);
        });
        binding.rabu.setOnClickListener(v -> {
            hari = binding.rabu.getText().toString();
            binding.senin.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.selasa.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.rabu.setTextColor(this.getResources().getColor(R.color.softred2));
            binding.kamis.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.jumat.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.sabtu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            showSchedule(kelas,grup,hari);
        });
        binding.kamis.setOnClickListener(v -> {
            hari = binding.kamis.getText().toString();
            binding.senin.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.selasa.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.rabu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.kamis.setTextColor(this.getResources().getColor(R.color.softred2));
            binding.jumat.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.sabtu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            showSchedule(kelas,grup,hari);
        });
        binding.jumat.setOnClickListener(v -> {
            hari = binding.jumat.getText().toString();
            binding.senin.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.selasa.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.rabu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.kamis.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.jumat.setTextColor(this.getResources().getColor(R.color.softred2));
            binding.sabtu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            showSchedule(kelas,grup,hari);
        });
        binding.sabtu.setOnClickListener(v -> {
            hari = binding.sabtu.getText().toString();
            binding.senin.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.selasa.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.rabu.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.kamis.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.jumat.setTextColor(this.getResources().getColor(R.color.colorPrimary));
            binding.sabtu.setTextColor(this.getResources().getColor(R.color.softred2));
            showSchedule(kelas,grup,hari);
        });

    }


    public void back(View view) {
        finish();
    }
}
