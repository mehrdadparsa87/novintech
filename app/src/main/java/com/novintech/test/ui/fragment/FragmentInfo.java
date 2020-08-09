package com.novintech.test.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.novintech.test.R;
import com.novintech.test.databinding.FragmentInfoBinding;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class FragmentInfo extends Fragment {

    private FragmentInfoBinding binding;

    public FragmentInfo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //    View root = inflater.inflate(R.layout.fragment_info, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        PersianCalendar persianCalendar = new PersianCalendar();
        String persianLongDate = persianCalendar.getPersianLongDate();
        binding.txtCreateDate.setText(persianLongDate);
    }
}