package com.example.aplikasitu.SPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aplikasitu.R;
import com.example.aplikasitu.UtilsApi.UtilsApi;
import com.example.aplikasitu.databinding.ActivitySppGambarBinding;

public class SppGambarActivity extends AppCompatActivity {

    private ActivitySppGambarBinding binding;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySppGambarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        Intent intent = getIntent();
        String imgBukti = intent.getStringExtra("imgBukti");

        Glide.with(context)
                .load(UtilsApi.img+imgBukti)
                .fitCenter()
                .placeholder(R.drawable.ic_exclamation)
                .into(binding.imgBuktiDetail);
    }

    public void backToSpp(View view) {
        finish();
    }
}
