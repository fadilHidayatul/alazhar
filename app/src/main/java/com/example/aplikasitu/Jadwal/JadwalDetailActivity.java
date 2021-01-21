package com.example.aplikasitu.Jadwal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aplikasitu.R;
import com.example.aplikasitu.databinding.ActivityJadwalDetailBinding;

public class JadwalDetailActivity extends AppCompatActivity {
    private ActivityJadwalDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJadwalDetailBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_jadwal_detail);
    }
}
