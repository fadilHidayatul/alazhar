package com.example.aplikasitu.SPP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplikasitu.R;
import com.example.aplikasitu.SPP.Adapter.SppFragmentAdapter;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.databinding.ActivitySppDetailBinding;

import com.google.android.material.tabs.TabLayout;

public class SppDetailActivity extends AppCompatActivity {
    private ActivitySppDetailBinding binding;
    PrefManager manager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySppDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        manager = new PrefManager(context);

        binding.tabLayout.setupWithViewPager(binding.viewPager);

        SppFragmentAdapter adapter = new SppFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new BeforeFragment(),"Belum Bayar");
        adapter.addFragment(new AfterFragment(), "Sudah Bayar");
        binding.viewPager.setAdapter(adapter);



    }

    public void backToSiswaSpp(View view) {
        finish();
    }
}
