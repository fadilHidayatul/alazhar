package com.example.aplikasitu.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.UtilsApi.ApiInterface;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivityProfilBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    private ActivityProfilBinding binding;

    Context context;
    PrefManager manager;
    ApiInterface apiInterface;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        manager = new PrefManager(context);
        apiInterface = UtilsApi.getApiService();

        dialog = new SpotsDialog.Builder().setMessage("Please Wait").setContext(context).setCancelable(false).build();

//        manager.getIdPegawai();

        getProfil();

    }

    private void getProfil() {
        dialog.show();
        apiInterface.getProfilPegawai(manager.getIdPegawai()).enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equalsIgnoreCase("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            String tglx = array.getJSONObject(0).getString("masuk").substring(8,10);
                            String blnx = array.getJSONObject(0).getString("masuk").substring(5,7);
                            String thnx = array.getJSONObject(0).getString("masuk").substring(0,4);

                            binding.namaProfil.setText(array.getJSONObject(0).getString("nama"));
                            binding.nipPegawai.setText(array.getJSONObject(0).getString("nip"));
                            binding.emailPegawai.setText(array.getJSONObject(0).getString("email"));
                            binding.telpPegawai.setText(array.getJSONObject(0).getString("tlp"));
                            binding.alamatPegawai.setText(array.getJSONObject(0).getString("alamat"));
                            binding.pendidikanPegawai.setText(array.getJSONObject(0).getString("pendidikan"));
                            binding.daftarPegawai.setText(tglx+"-"+blnx+"-"+thnx);

                        }else{
                            Toast.makeText(context, object.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    dialog.dismiss();
                    try {
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
}
