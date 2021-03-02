package com.example.aplikasitu.Absen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplikasitu.Absen.Adapter.AbsenDetailAdapter;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityAbsenDetailBinding;
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

public class AbsenDetailActivity extends AppCompatActivity {
    private ActivityAbsenDetailBinding binding;
    String id = "";
    List<Absen.DATABean> dataBeans;
    AbsenDetailAdapter adapter;
    Context context;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        apiInterface = UtilsApi.getApiService();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        binding.namaPegawai.setText(intent.getStringExtra("nama"));

        getAbsenPegawai();



    }

    private void getAbsenPegawai() {
        apiInterface.getAbsen(id).enqueue(new Callback<ResponseBody>() {
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
                                Absen.DATABean absen = gson.fromJson(array.getJSONObject(i).toString(),Absen.DATABean.class);
                                dataBeans.add(absen);
                            }

                            adapter = new AbsenDetailAdapter(context,dataBeans);
                            binding.recyclerAbsen2.setAdapter(adapter);
                            binding.recyclerAbsen2.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerAbsen2.setHasFixedSize(true);

                        }else{
                            binding.cardNoAbsen.setVisibility(View.VISIBLE);
                            binding.recyclerAbsen2.setVisibility(View.GONE);
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

            }
        });

    }

    public void back(View view) {
        finish();
    }
}
