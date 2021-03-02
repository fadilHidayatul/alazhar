package com.example.aplikasitu.Rapor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityShowRaporBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowRaporActivity extends AppCompatActivity {
    private ActivityShowRaporBinding binding;
    String idSIswa,kelas,grup,semester,file;

    Context context;
    ApiInterface apiInterface;
    PrefManager manager;
    AlertDialog dialog;

    RemotePDFViewPager remotePDFViewPager;
    PDFPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowRaporBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        manager = new PrefManager(context);
        apiInterface = UtilsApi.getApiService();
        dialog = new SpotsDialog.Builder().setMessage("Please Wait").setContext(context).setCancelable(false).build();


        Intent intent = getIntent();
        semester = intent.getStringExtra("id_smt");
        idSIswa = manager.getIdSiswaRapor();
        kelas = manager.getKelas();
        grup = manager.getGrupRapor();

        getRaporSiswa();

    }

    private void getRaporSiswa() {
        dialog.show();
        apiInterface.getRapor(idSIswa,kelas,grup,semester).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            binding.container.setVisibility(View.VISIBLE);
                            binding.oops.setVisibility(View.GONE);

                            JSONArray array = object.getJSONArray("DATA");

                            binding.namaRapor.setText(""+array.getJSONObject(0).getString("siswa"));
                            file = array.getJSONObject(0).getString("file");
                            remotePDFViewPager = new RemotePDFViewPager(context, UtilsApi.pdf + file, new DownloadFile.Listener() {
                                @Override
                                public void onSuccess(String url, String destinationPath) {
                                    adapter = new PDFPagerAdapter(context, FileUtil.extractFileNameFromURL(url));
                                    remotePDFViewPager.setAdapter(adapter);

                                    binding.container.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(context, "Rapor tidak bisa dibuka", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onProgressUpdate(int progress, int total) {

                                }
                            });

                        }else{
                            binding.container.setVisibility(View.GONE);
                            binding.oops.setVisibility(View.VISIBLE);
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
                dialog.dismiss();
                Toast.makeText(context, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
}
