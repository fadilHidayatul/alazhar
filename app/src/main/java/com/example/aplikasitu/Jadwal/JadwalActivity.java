package com.example.aplikasitu.Jadwal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;

import com.example.aplikasitu.Jadwal.Adapter.JadwalAdapter;
import com.example.aplikasitu.Kelas.Kelas;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityJadwalBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {
    private ActivityJadwalBinding binding;
    Context context;
    JadwalAdapter adapter;
    List<Kelas.DATABean> dataBeans;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJadwalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        apiInterface = UtilsApi.getApiService();

        getJadwal();


    }

    //cek data ganda
    public boolean isExist(String strKelas){
        for (int i = 0; i <dataBeans.size() ; i++) {
            if (dataBeans.get(i).getKelas().equals(strKelas)){
                return true;
            }
        }
        return false;
    }

    private void getJadwal() {
        apiInterface.getAllKelas().enqueue(new Callback<ResponseBody>() {
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

                                //cek dari respon data ganda
                                JSONObject kls = array.getJSONObject(i);
                                boolean isExist = isExist(kls.getString("kelas"));

                                if (!isExist){
                                    Kelas.DATABean kelas = gson.fromJson(array.getJSONObject(i).toString(),Kelas.DATABean.class);
                                    dataBeans.add(kelas);
                                }

                            }


                            adapter = new JadwalAdapter(context,dataBeans);
                            binding.recyclerJadwal.setAdapter(adapter);
                            binding.recyclerJadwal.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerJadwal.setHasFixedSize(true);

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
}
