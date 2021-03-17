package com.example.aplikasitu.Rapor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasitu.R;
import com.example.aplikasitu.Rapor.ShowRaporActivity;
import com.example.aplikasitu.databinding.LayoutSemesterDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SemesterDialog extends BottomSheetDialogFragment {

    private LayoutSemesterDialogBinding binding;
    Context context;

    public SemesterDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = LayoutSemesterDialogBinding.inflate(inflater,container,false);
        context = getContext();

        binding.sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowRaporActivity.class);
                intent.putExtra("id_smt", "1");
                startActivity(intent);
            }
        });
        binding.sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowRaporActivity.class);
                intent.putExtra("id_smt", "2");
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}
