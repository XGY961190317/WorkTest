package com.example.onetob.diancandemo.viewpagerdemo.module2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onetob.diancandemo.viewpagerdemo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SPFragment extends androidx.fragment.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spfragment_layout,null);
        return view;
    }
}
