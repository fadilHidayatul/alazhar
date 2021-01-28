package com.example.aplikasitu.SPP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.aplikasitu.SPP.Adapter.SppFragmentAdapter;
import com.example.aplikasitu.databinding.ActivitySppDetailBinding;

public class SppDetailActivity extends AppCompatActivity {
    private ActivitySppDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySppDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tabLayout.setupWithViewPager(binding.viewPager);

        SppFragmentAdapter adapter = new SppFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new BeforeFragment(), "Belum Bayar");
        adapter.addFragment(new AfterFragment(), "Sudah Bayar");
        binding.viewPager.setAdapter(adapter);
    }


}
