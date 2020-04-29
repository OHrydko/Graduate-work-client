package com.example.graduate_work_android.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.databinding.ItemAllergicBinding;
import com.example.graduate_work_android.databinding.ItemBinding;
import com.example.graduate_work_android.models.RowType;
import com.example.graduate_work_android.models.Supplement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseAdapter extends RecyclerView.Adapter {
    private List<RowType> response;
    private Map<String, Integer> colors = new HashMap<>();

    public ResponseAdapter(List<RowType> response, FragmentActivity activity) {
        this.response = response;
        initHashMap(activity);
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if (response.get(position) instanceof Supplement)
            return RowType.SUPPLEMENT;
        return RowType.ALLERGIC;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == RowType.SUPPLEMENT) {
            ItemBinding employeeListItemBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item, parent, false);
            return new AdapterViewHolder(employeeListItemBinding);
        } else {
            ItemAllergicBinding employeeListItemBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_allergic, parent, false);
            return new AllergicViewHolder(employeeListItemBinding);
        }
    }

    public void setData(List<RowType> data) {
        this.response = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AdapterViewHolder) {
            RowType supplement = response.get(position);
            switch (supplement.getDanger()) {
                case "Висока":
                    ((AdapterViewHolder) holder).itemBinding.name.setTextColor(colors.get("red"));
                    break;
                case "Середня":
                    ((AdapterViewHolder) holder).itemBinding.name.setTextColor(colors.get("dark_orange"));
                    break;
                case "Низька":
                    ((AdapterViewHolder) holder).itemBinding.name.setTextColor(colors.get("orange"));
                    break;
                case "Дуже низька":
                    ((AdapterViewHolder) holder).itemBinding.name.setTextColor(colors.get("yellow"));
                    break;
                case "Немає":

                default:
                    ((AdapterViewHolder) holder).itemBinding.name.setTextColor(colors.get("green"));
                    break;

            }
            ((AdapterViewHolder) holder).itemBinding.idE.setText(String.format("E %s",
                    supplement.getId()));
            String names = supplement.getName().substring(0, 1).toUpperCase()
                    + supplement.getName().substring(1);

            ((AdapterViewHolder) holder).itemBinding.name.setText(names);
            ((AdapterViewHolder) holder).itemBinding.category.setText(supplement.getCategory());
            ((AdapterViewHolder) holder).itemBinding.danger.setText(supplement.getDanger());
        } else {
            RowType allergic = response.get(position);
            String allergics = allergic.getAllergic().substring(0, 1).toUpperCase()
                    + allergic.getAllergic().substring(1);
            ((AllergicViewHolder) holder).allergicBinding.name.setText(allergics);
        }

    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding itemBinding;

        AdapterViewHolder(@NonNull ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    class AllergicViewHolder extends RecyclerView.ViewHolder {
        private ItemAllergicBinding allergicBinding;

        AllergicViewHolder(@NonNull ItemAllergicBinding itemBinding) {
            super(itemBinding.getRoot());
            this.allergicBinding = itemBinding;
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
