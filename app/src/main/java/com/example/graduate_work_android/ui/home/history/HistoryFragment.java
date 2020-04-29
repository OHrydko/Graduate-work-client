package com.example.graduate_work_android.ui.home.history;

import android.os.Bundle;
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
import com.example.graduate_work_android.adapter.HistoryAdapter;
import com.example.graduate_work_android.databinding.FragmentHistoryBinding;
import com.example.graduate_work_android.models.History;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {
    private FragmentActivity activity;
    private HistoryViewModel viewModel;
    private HistoryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHistoryBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_history, container, false);
        viewModel = new ViewModelProvider(activity).get(HistoryViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.init(activity);
        binding.setLifecycleOwner(this);


        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        adapter = new HistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        getHistory();
        return binding.getRoot();
    }

    private void getHistory() {
        viewModel.getHistories().observe(activity, histories -> adapter.setData(histories));
    }


}
