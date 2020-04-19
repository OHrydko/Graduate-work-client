package com.example.graduate_work_android.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.FragmentRegistrationBinding;

import org.jetbrains.annotations.NotNull;

public class RegistrationFragment extends Fragment {

    private FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentRegistrationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration,
                container, false);
        RegistrationViewModel viewModel = new ViewModelProvider(activity).get(RegistrationViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.init(activity);
        return binding.getRoot();
    }
}