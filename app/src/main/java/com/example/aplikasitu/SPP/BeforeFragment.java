package com.example.aplikasitu.SPP;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aplikasitu.R;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.FragmentBeforeBinding;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class BeforeFragment extends Fragment {

    private FragmentBeforeBinding binding;
    PrefManager manager;
    Context context;
    ApiInterface apiInterface;
    List<Spp.DATABean> dataBeans;

    String idSiswa = "";
    String status = "0";
    String namaSiswa = "";
    int FRAGMENT_A = 1;

    public BeforeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBeforeBinding.inflate(inflater,container,false);
        context = getContext();
        apiInterface = UtilsApi.getApiService();
        manager = new PrefManager(context);

        idSiswa = manager.getSppSiswa();
        namaSiswa = manager.getSppNama();

        getSPP(idSiswa);

        binding.siswa.setText(namaSiswa);
        binding.btnVerify.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Yakin memverifikasi siswa ini?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(context, "Sudah Verifikasi", Toast.LENGTH_SHORT).show();
                            updateSpp(idSiswa);
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#D15858"));
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#CC3F2A92"));
        });

        return binding.getRoot();
    }

    private void getSPP(String idSiswa) {
        apiInterface.getSpp(idSiswa,status).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    binding.relative1.setVisibility(View.VISIBLE);
                    binding.relative2.setVisibility(View.GONE);
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();

                            for (int i = 0; i <array.length() ; i++) {
                                Spp.DATABean spp = gson.fromJson(array.getJSONObject(i).toString(),Spp.DATABean.class);
                                dataBeans.add(spp);
                            }

                           binding.thn.setText("-"+dataBeans.get(0).getTgl_bayar().substring(0,4));
                           binding.bln.setText("-"+dataBeans.get(0).getTgl_bayar().substring(5,7));
                           binding.tgl.setText(dataBeans.get(0).getTgl_bayar().substring(8,10));
                            Glide.with(context)
                                    .load(UtilsApi.img+dataBeans.get(0).getBukti())
                                    .fitCenter()
                                    .placeholder(R.drawable.ic_exclamation)
                                    .into(binding.imgBukti);

                            binding.imgBukti.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity().getApplicationContext(),SppGambarActivity.class);
                                    intent.putExtra("imgBukti",dataBeans.get(0).getBukti());
                                    startActivity(intent);
                                }
                            });
                        }else{
//                            Toast.makeText(context, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                            binding.relative1.setVisibility(View.GONE);
                            binding.relative2.setVisibility(View.VISIBLE);
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

    private void updateSpp(String idSiswa) {
        apiInterface.updateSpp(idSiswa).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            Toast.makeText(context, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                            getSPP(idSiswa);
                        }else{
                            Toast.makeText(context, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException | IOException e) {
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
