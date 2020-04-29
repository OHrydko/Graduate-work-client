package com.example.graduate_work_android.ui.home.allergic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.adapter.AdapterAllergic;
import com.example.graduate_work_android.databinding.FragmentAllergicBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class AllergicFragment extends Fragment {
    private FragmentActivity activity;
    private AllergicViewModel viewModel;
    private AdapterAllergic adapterAllergic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        Log.d("life", "create");

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentAllergicBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_allergic, container, false);
        viewModel = new ViewModelProvider(activity).get(AllergicViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.init(activity);
        binding.setLifecycleOwner(this);
        Log.d("life", "onCreateView");


        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);

        adapterAllergic = new AdapterAllergic(new ArrayList<>());
        recyclerView.setAdapter(adapterAllergic);
        getAllergic();
        return binding.getRoot();
    }

    private void getAllergic() {
        viewModel.getData().observe(activity, strings -> adapterAllergic.setData(strings));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("life", "onView");
    }
}
