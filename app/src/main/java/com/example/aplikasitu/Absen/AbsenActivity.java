package com.example.aplikasitu.Absen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.aplikasitu.databinding.ActivityAbsenBinding;

public class AbsenActivity extends AppCompatActivity {
    private ActivityAbsenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
