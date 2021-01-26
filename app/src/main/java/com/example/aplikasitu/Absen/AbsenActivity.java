package com.example.aplikasitu.Absen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.Absen.Adapter.AbsenAdapter;
import com.example.aplikasitu.Pegawai.Pegawai;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityAbsenBinding;
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

public class AbsenActivity extends AppCompatActivity {
    private ActivityAbsenBinding binding;
    Context context;
    AbsenAdapter adapter;
    List<Pegawai.DATABean> dataBeans;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        apiInterface = UtilsApi.getApiService();

        getPegawai();

    }

    private void getPegawai() {
        apiInterface.allPegawai().enqueue(new Callback<ResponseBody>() {
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
                                Pegawai.DATABean pegawai = gson.fromJson(array.getJSONObject(i).toString(),Pegawai.DATABean.class);
                                dataBeans.add(pegawai);
                            }

                            adapter = new AbsenAdapter(context,dataBeans);
                            binding.recyclerAbsen.setAdapter(adapter);
                            binding.recyclerAbsen.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerAbsen.setHasFixedSize(true);
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
                Toast.makeText(context, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void back(View view) {
        finish();
    }

    public void searchAbsen(View view) {
        binding.searchAbsen.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (binding.searchAbsen.getVisibility() == View.VISIBLE){
            binding.searchAbsen.setVisibility(View.GONE);
        }else{
            finish();
        }

    }
}
