package com.example.aplikasitu.SPP;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aplikasitu.R;
import com.example.aplikasitu.SharedPreferences.PrefManager;
import com.example.aplikasitu.databinding.FragmentBeforeBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeforeFragment extends Fragment {

    private FragmentBeforeBinding binding;
    PrefManager manager;
    Context context;
    String idSiswa = "";

    public BeforeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBeforeBinding.inflate(inflater,container,false);
        context = getContext();
        manager = new PrefManager(context);

        idSiswa = manager.getSppSiswa();

        binding.btnVerify.setOnClickListener(v -> {
            Toast.makeText(context, "Panggil data verifikasi", Toast.LENGTH_SHORT).show();
        });



        return binding.getRoot();
    }
}
