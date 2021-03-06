package com.novintech.test.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.novintech.test.R;
import com.novintech.test.databinding.FragmentConfirmationBinding;
import com.novintech.test.databinding.FragmentFormBinding;


public class FragmentConfirmation extends Fragment {

    public FragmentConfirmation() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentConfirmationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirmation, container, false);
        return binding.getRoot();

    }
}