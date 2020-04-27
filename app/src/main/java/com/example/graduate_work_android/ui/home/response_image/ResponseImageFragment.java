package com.example.graduate_work_android.ui.home.response_image;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.adapter.ResponseAdapter;
import com.example.graduate_work_android.databinding.FragmentResponseImageBinding;
import com.example.graduate_work_android.models.ResponseUploadImage;
import com.example.graduate_work_android.ui.home.photo.PhotoViewModel;

import org.jetbrains.annotations.NotNull;


public class ResponseImageFragment extends Fragment {
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
        FragmentResponseImageBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_response_image, container, false);
        PhotoViewModel viewModel = new ViewModelProvider(activity).get(PhotoViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        ResponseUploadImage response = viewModel.getResponse().getValue();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);

        ResponseAdapter adapter = new ResponseAdapter(response, activity);
        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }


}
