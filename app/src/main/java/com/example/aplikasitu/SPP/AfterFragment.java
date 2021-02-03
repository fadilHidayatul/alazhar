package com.example.aplikasitu.SPP;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aplikasitu.R;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.FragmentAfterBinding;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AfterFragment extends Fragment {

    private FragmentAfterBinding binding;
    Context context;
    PrefManager manager;
    ApiInterface apiInterface;
    List<Spp.DATABean> dataBeans;

    String idSiswa = "";

    AlertDialog dialog;

    public AfterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAfterBinding.inflate(inflater,container,false);
        context = getContext();
        manager = new PrefManager(context);
        apiInterface = UtilsApi.getApiService();
        dialog = new SpotsDialog.Builder().setMessage("Please Wait").setContext(context).setCancelable(false).build();


        idSiswa = manager.getSppSiswa();
        binding.siswa.setText(manager.getSppNama());

        getSpp(idSiswa);

        binding.relative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpp(idSiswa);
            }
        });

        return binding.getRoot();
    }

    private void getSpp(String idSiswa) {
        dialog.show();
        apiInterface.getSpp(idSiswa,"1").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    binding.relative1.setVisibility(View.VISIBLE);
                    binding.relative2.setVisibility(View.GONE);

                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();

                            for (int i = 0; i < array.length(); i++) {
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
                dialog.dismiss();
                Toast.makeText(context, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        dialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }
}
