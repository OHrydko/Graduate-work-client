package com.example.graduate_work_android.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.ItemAllergicUserBinding;

import java.util.List;

public class AdapterAllergic extends RecyclerView.Adapter<AdapterAllergic.AllergicViewHolder> {
    private List<String> allergic;

    public AdapterAllergic(List<String> allergic) {
        this.allergic = allergic;
    }

    @NonNull
    @Override
    public AllergicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAllergicUserBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_allergic_user, parent, false);
        return new AllergicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllergicViewHolder holder, int position) {
        String data = allergic.get(position).substring(0, 1).toUpperCase()
                + allergic.get(position).substring(1);
        holder.binding.name.setText(data);
    }

    @Override
    public int getItemCount() {
        return allergic.size();
    }

    public void setData(List<String> data) {
        this.allergic = data;
        notifyDataSetChanged();
    }

    class AllergicViewHolder extends RecyclerView.ViewHolder {
        private ItemAllergicUserBinding binding;

        AllergicViewHolder(@NonNull ItemAllergicUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
