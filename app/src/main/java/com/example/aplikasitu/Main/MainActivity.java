package com.example.aplikasitu.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.Absen.AbsenActivity;
import com.example.aplikasitu.Intro.LoginActivity;
import com.example.aplikasitu.Jadwal.JadwalActivity;
import com.example.aplikasitu.Kelas.KelasActivity;
import com.example.aplikasitu.Pegawai.PegawaiActivity;
import com.example.aplikasitu.R;
import com.example.aplikasitu.SPP.SppActivity;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.Siswa.SiswaActivity;
import com.example.aplikasitu.databinding.ActivityMainBinding;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Context context;
    PrefManager manager;
    private boolean doubleback;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = this;
        manager = new PrefManager(context);

        binding.TU.setText(manager.getUsername());

        navigateMenu();
        logout();

    }

    private void navigateMenu() {
        binding.menuSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SiswaActivity.class);
                startActivity(intent);
            }
        });
        binding.menuPegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PegawaiActivity.class);
                startActivity(intent);
            }
        });
        binding.menuKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KelasActivity.class);
                startActivity(intent);
            }
        });
        binding.menuJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JadwalActivity.class);
                startActivity(intent);
            }
        });
        binding.menuAbsen.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AbsenActivity.class);
            startActivity(intent);
        });
        binding.menuSPP.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SppActivity.class);
            startActivity(intent);
        });
    }

    private void logout() {
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(),R.style.AlertDialog);
                builder.setMessage("Keluar Aplikasi?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                manager.removeSession();
                                finish();
                                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
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
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleback){
            toast.cancel();
            super.onBackPressed();
            moveTaskToBack(true);
        }else{
            toast = Toast.makeText(this,"Tekan lagi untuk keluar",Toast.LENGTH_SHORT);
            toast.show();
            doubleback = true;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleback = false;
                }
            },2000);
        }
    }
}
