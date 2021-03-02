package com.example.aplikasitu.SPP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.aplikasitu.SPP.Adapter.SppAdapter;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivitySppBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    PrefManager manager;

    AlertDialog dialog;

    private DatePickerDialog datePickerDialog;
    int DATE_DIALOG_ID = 1;
    private int th,bln,hr;
    int xmonth, xyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        manager = new PrefManager(context);
        apiInterface = UtilsApi.getApiService();
        dialog = new SpotsDialog.Builder().setMessage("Please Wait").setContext(context).setCancelable(false).build();

        binding.getTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar calendar = Calendar.getInstance();
        th = calendar.get(Calendar.YEAR);
        bln = calendar.get(Calendar.MONTH);
        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       xmonth = month +1;
                       xyear = year;
                       manager.bulanSPP(PrefManager.BULAN_SPP,xmonth+"");
                       manager.tahunSPP(PrefManager.TAHUN_SPP,xyear+"");
                       binding.txtTanggal.setText("Bulan : "+ formatMonth((xmonth+"")) + " Tahun : "+xyear); //format bulan tambah
                        getSiswaSPP((xmonth+""),(xyear+""));
                    }
                },th,bln,30);

        datePickerDialog.getDatePicker().findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);

        binding.getTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


    }


    private void getSiswaSPP(String bulan, String tahun) {
        dialog.show();
        apiInterface.getSppSiswa(bulan,tahun).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            binding.recyclerSpp.setVisibility(View.VISIBLE);
                            binding.noSPP.setVisibility(View.GONE);

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
                            binding.recyclerSpp.setVisibility(View.GONE);
                            binding.noSPP.setVisibility(View.VISIBLE);
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

    public String formatMonth(String s) {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");

        try {
            return monthDisplay.format(monthParse.parse(s));
        }catch (ParseException e){
            e.printStackTrace();
        }
        return s;
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
