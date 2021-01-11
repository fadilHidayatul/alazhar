package com.example.aplikasitu.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.R;
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
    }

    private void navigateMenu() {
        binding.menuSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SiswaActivity.class);
                startActivity(intent);
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
