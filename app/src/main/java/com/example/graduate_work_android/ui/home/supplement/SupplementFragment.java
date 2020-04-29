package com.example.graduate_work_android.ui.home.supplement;

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
import com.example.graduate_work_android.databinding.FragmentSupplementBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class SupplementFragment extends Fragment {
    private FragmentActivity activity;
    private SupplementViewModel viewModel;
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
        FragmentSupplementBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_supplement, container, false);
        viewModel = new ViewModelProvider(activity).get(SupplementViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.init(activity);
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        adapter = new ResponseAdapter(new ArrayList<>(), activity);
        recyclerView.setAdapter(adapter);

        getData();
        return binding.getRoot();
    }

    private void getData() {
        viewModel.getSupplement().observe(activity, supplements -> adapter.setData(supplements));
    }


}
