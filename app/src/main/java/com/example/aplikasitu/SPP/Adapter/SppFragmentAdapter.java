package com.example.aplikasitu.SPP.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SppFragmentAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment>fragmentList = new ArrayList<>();
    private final List<String>stringListFragment = new ArrayList<>();

    public SppFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Nullable
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringListFragment.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        stringListFragment.add(title);
    }
}
