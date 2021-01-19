package com.example.aplikasitu.Pegawai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.Pegawai.Adapter.PegawaiAdapter;
import com.example.aplikasitu.R;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityPegawaiBinding;
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

public class PegawaiActivity extends AppCompatActivity {
    private ActivityPegawaiBinding binding;
    Context context;
    PegawaiAdapter adapter;
    List<Pegawai.DATABean> dataBeans;
    ApiInterface apiInterface;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPegawaiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = this;
        apiInterface = UtilsApi.getApiService();
        dialog = new SpotsDialog.Builder().setContext(context).setMessage("Please Wait").setCancelable(false).build();

        getDataPegawai();

        binding.searchPegawai.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    private void getDataPegawai() {
        dialog.show();
        apiInterface.allPegawai().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    dialog.hide();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equals("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            Gson gson = new Gson();
                            dataBeans = new ArrayList<>();

                            for (int i = 0; i <array.length() ; i++) {
                                Pegawai.DATABean pegawai = gson.fromJson(array.getJSONObject(i).toString(),Pegawai.DATABean.class);
                                dataBeans.add(pegawai);
                            }

                            binding.jumlah.setText(""+array.length());

                            adapter = new PegawaiAdapter(context,dataBeans);
                            binding.recyclerPegawai.setAdapter(adapter);
                            binding.recyclerPegawai.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerPegawai.setHasFixedSize(true);

                        }else{
                            dialog.dismiss();
                            Toast.makeText(context, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
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

    public void back(View view) {
        finish();
    }

    public void search(View view) {
        binding.searchPegawai.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (binding.searchPegawai.getVisibility() == View.VISIBLE){
            binding.searchPegawai.setVisibility(View.GONE);
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }
}
