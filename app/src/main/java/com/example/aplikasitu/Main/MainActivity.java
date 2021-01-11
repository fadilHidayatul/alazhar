package com.example.aplikasitu.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplikasitu.R;
import com.example.aplikasitu.Siswa.SiswaActivity;
import com.example.aplikasitu.databinding.ActivityMainBinding;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
}
