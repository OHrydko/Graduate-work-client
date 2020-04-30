package com.example.graduate_work_android.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.ItemHistoryBinding;
import com.example.graduate_work_android.models.History;
import com.example.graduate_work_android.models.Supplement;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<History> data;

    public HistoryAdapter(List<History> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_history, parent, false);
        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = data.get(position);
        holder.binding.name.setText(history.getName());
        byte[] decodedString = Base64.decode(history.getPhoto(), Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.binding.image.setImageBitmap(bm);
        List<String> content = new ArrayList<>();
        for (Supplement row : history.getList_of_e()) {
            content.add(row.getName());
        }
        content.addAll(history.getAllergic());
        holder.binding.content.setText(android.text.TextUtils.join(",", content));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<History> histories) {
        data = histories;
        notifyDataSetChanged();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryBinding binding;

        HistoryViewHolder(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
