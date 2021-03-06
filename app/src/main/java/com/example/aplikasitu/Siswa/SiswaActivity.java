package com.example.aplikasitu.Siswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aplikasitu.R;
import com.example.aplikasitu.Siswa.adapter.SiswaAdapter;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivitySiswaBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class SiswaActivity extends AppCompatActivity {
    private ActivitySiswaBinding binding;
    SiswaAdapter adapter;
    Context context;
    ApiInterface apiInterface;
    List<Siswa.DATABean> dataBeanList;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySiswaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        context = this;
        apiInterface = UtilsApi.getApiService();

        dialog = new SpotsDialog.Builder().setContext(context).setCancelable(false).setMessage("Please Wait").build();

        getData();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void getData() {
        dialog.show();
        apiInterface.allSiswa().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    dialog.hide();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equals("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeanList = new ArrayList<>();
                            Gson gson = new Gson();
                            dataBeanList.clear();
                            for (int i = 0; i < array.length(); i++) {
                                Siswa.DATABean siswa = gson.fromJson(array.getJSONObject(i).toString(),Siswa.DATABean.class);
                                dataBeanList.add(siswa);
                            }

                            binding.jumlah.setText(array.length()+"");

                            binding.recyclerSiswa.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerSiswa.setHasFixedSize(true);
                            adapter = new SiswaAdapter(context,dataBeanList);
                            binding.recyclerSiswa.setAdapter(adapter);

                        }else{
                            dialog.dismiss();
                            Toast.makeText(context, ""+object.getString("status"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    dialog.dismiss();
                    Toast.makeText(context, "Respon tidak berhasil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "koneksi internet", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void back(View view) {
        finish();
    }

    public void search(View view) {
        binding.searchView.setVisibility(VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (binding.searchView.getVisibility() == VISIBLE){
            binding.searchView.setVisibility(View.GONE);
        }else {
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }
}
