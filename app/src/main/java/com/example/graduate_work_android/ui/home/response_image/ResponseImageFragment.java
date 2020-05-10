package com.example.graduate_work_android.ui.home.response_image;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.adapter.ResponseAdapter;
import com.example.graduate_work_android.databinding.FragmentResponseImageBinding;
import com.example.graduate_work_android.models.Allergic;
import com.example.graduate_work_android.models.Prediction;
import com.example.graduate_work_android.models.ResponseUploadImage;
import com.example.graduate_work_android.models.RowType;
import com.example.graduate_work_android.ui.home.photo.PhotoViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ResponseImageFragment extends Fragment {
    private FragmentActivity activity;
    private ResponseAdapter adapter;
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

        List<RowType> list = new ArrayList<>();
        if (response != null) {
            if (response.getAllergic() != null && response.getAllergic().size() > 0) {
                List<Allergic> allergic = new ArrayList<>();
                for (String row : response.getAllergic()) {
                    allergic.add(new Allergic(row));
                }
                list.addAll(allergic);
            }

            if (response.getSupplement() != null) {
                list.addAll(response.getSupplement());
            }
        }

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        if (list.size() > 0) {
            binding.nothing.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            adapter = new ResponseAdapter(list, activity);
            recyclerView.setAdapter(adapter);
        } else {
            binding.nothing.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
        }
        viewModel.prediction.observe(activity, prediction -> {
            Log.d("prediction", prediction.getName());
            adapter.addPrediction(prediction);
        });
        return binding.getRoot();
    }

}
