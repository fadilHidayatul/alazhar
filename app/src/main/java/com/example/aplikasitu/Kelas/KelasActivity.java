package com.example.aplikasitu.Kelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.aplikasitu.Kelas.Adapter.KelasAdapter;
import com.example.aplikasitu.R;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityKelasBinding;
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

public class KelasActivity extends AppCompatActivity {

    private ActivityKelasBinding binding;
    Context context;
    KelasAdapter adapter;
    List<Kelas.DATABean> dataBeans;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKelasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        apiInterface = UtilsApi.getApiService();

        getAllKelas();


    }

    private void getAllKelas() {
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
                                Kelas.DATABean kelas = gson.fromJson(array.getJSONObject(i).toString(),Kelas.DATABean.class);
                                dataBeans.add(kelas);
                            }

                            adapter = new KelasAdapter(context,dataBeans);
                            binding.recyclerKelas.setAdapter(adapter);
                            binding.recyclerKelas.setLayoutManager(new GridLayoutManager(context,2));
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

            }
        });
    }
}
