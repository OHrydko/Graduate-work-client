package com.example.graduate_work_android.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.ActivityHomeBinding;
import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

public class HomeActivity extends AppCompatActivity implements StartActivityForResultCallback {
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_home);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.init(this, this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.getRoot();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        viewModel.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void start(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }
}
