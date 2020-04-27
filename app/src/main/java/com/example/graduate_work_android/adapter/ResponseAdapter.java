package com.example.graduate_work_android.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.ItemBinding;
import com.example.graduate_work_android.models.ResponseUploadImage;
import com.example.graduate_work_android.models.Supplement;

import java.util.HashMap;
import java.util.Map;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.AdapterViewHolder> {
    private ResponseUploadImage response;
    private Map<String, Integer> colors = new HashMap<>();

    public ResponseAdapter(ResponseUploadImage response, FragmentActivity activity) {
        this.response = response;
        initHashMap(activity);
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding employeeListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item, parent, false);
        return new AdapterViewHolder(employeeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Supplement supplement = response.getSupplement().get(position);

        switch (supplement.getDanger()) {
            case "Висока":
                holder.itemBinding.name.setTextColor(colors.get("red"));
                break;
            case "Середня":
                holder.itemBinding.name.setTextColor(colors.get("dark_orange"));
                break;
            case "Низька":
                holder.itemBinding.name.setTextColor(colors.get("orange"));
                break;
            case "Дуже низька":
                holder.itemBinding.name.setTextColor(colors.get("yellow"));
                break;
            case "Немає":
                holder.itemBinding.name.setTextColor(colors.get("green"));
                break;
        }
        holder.itemBinding.idE.setText(String.format("E %s", supplement.getNumber_supplement()));
        holder.itemBinding.name.setText(supplement.getName());
        holder.itemBinding.category.setText(supplement.getCategories());
        holder.itemBinding.danger.setText(supplement.getDanger());

    }

    @Override
    public int getItemCount() {
        return response.getSupplement().size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding itemBinding;

        AdapterViewHolder(@NonNull ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }


    private void initHashMap(FragmentActivity activity) {
        colors.put("red", activity.getResources().getColor(R.color.red));
        colors.put("dark_orange", activity.getResources().getColor(R.color.dark_orange));
        colors.put("orange", activity.getResources().getColor(R.color.orange));
        colors.put("yellow", activity.getResources().getColor(R.color.yellow));
        colors.put("green", activity.getResources().getColor(R.color.green));
    }
}
