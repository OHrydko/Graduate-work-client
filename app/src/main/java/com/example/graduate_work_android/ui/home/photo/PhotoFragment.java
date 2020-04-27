package com.example.graduate_work_android.ui.home.photo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.FragmentPhotoBinding;
import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

import org.jetbrains.annotations.NotNull;


public class PhotoFragment extends Fragment implements StartActivityForResultCallback {
    private FragmentActivity activity;
    private PhotoViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPhotoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo,
                container, false);
        viewModel = new ViewModelProvider(activity).get(PhotoViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.init(activity, this);
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        viewModel.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void start(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }


}
