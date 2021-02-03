package com.example.aplikasitu.Rapor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplikasitu.Kelas.Kelas;
import com.example.aplikasitu.Rapor.Adapter.RaporAdapter;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityRaporBinding;
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

public class RaporActivity extends AppCompatActivity {
    private ActivityRaporBinding binding;
    Context context;
    RaporAdapter adapter;
    ApiInterface apiInterface;
    List<Kelas.DATABean> dataBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRaporBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        apiInterface = UtilsApi.getApiService();

        getKelasForRapor();

    }

    public boolean isExist(String strKelas){
        for (int i = 0; i <dataBeans.size() ; i++) {
            if (dataBeans.get(i).getKelas().equals(strKelas)){
                return true;
            }
        }
        return false;
    }

    private void getKelasForRapor() {
        apiInterface.getAllKelas().enqueue(new Callback<ResponseBody>() {
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

                                JSONObject kls = array.getJSONObject(i);
                                boolean isExist = isExist(kls.getString("kelas"));

                                if (!isExist){
                                    Kelas.DATABean kelas = gson.fromJson(array.getJSONObject(i).toString(), Kelas.DATABean.class);
                                    dataBeans.add(kelas);
                                }

                            }

                            adapter = new RaporAdapter(context,dataBeans);
                            binding.recyclerKelas.setAdapter(adapter);
                            binding.recyclerKelas.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerKelas.setHasFixedSize(true);


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
