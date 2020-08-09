package com.novintech.test.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.novintech.test.R;
import com.novintech.test.databinding.FragmentFormBinding;
import com.novintech.test.databinding.FragmentInfoBinding;

public class FragmentForm extends Fragment {

    public FragmentForm() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentFormBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form, container, false);
        return binding.getRoot();

    }
}