package com.example.aplikasitu.Pegawai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.aplikasitu.R;
import com.example.aplikasitu.databinding.ActivityPegawaiBinding;

public class PegawaiActivity extends AppCompatActivity {
    private ActivityPegawaiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPegawaiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    public void back(View view) {
        finish();
    }
}
