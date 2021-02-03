package com.example.aplikasitu.SPP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.SPP.Adapter.SppAdapter;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivitySppBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SppActivity extends AppCompatActivity {
    private ActivitySppBinding binding;
    Context context;
    SppAdapter adapter;
    List<SppSiswa.DATABean> dataBeans;
    ApiInterface apiInterface;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        apiInterface = UtilsApi.getApiService();
        dialog = new SpotsDialog.Builder().setMessage("Please Wait").setContext(context).setCancelable(false).build();

        getSiswaSPP();
    }

    private void getSiswaSPP() {
        dialog.show();
        apiInterface.getSppSiswa().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();

                            for (int i = 0; i <array.length() ; i++) {
                                SppSiswa.DATABean sppsiswa = gson.fromJson(array.getJSONObject(i).toString(),SppSiswa.DATABean.class);
                                dataBeans.add(sppsiswa);
                            }

                            adapter = new SppAdapter(context,dataBeans);
                            binding.recyclerSpp.setAdapter(adapter);
                            binding.recyclerSpp.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerSpp.setHasFixedSize(true);
                        }else{
                            Toast.makeText(context, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        dialog.dismiss();
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
                dialog.dismiss();
                Toast.makeText(context, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }

    public void back(View view) {
        finish();
    }
}
