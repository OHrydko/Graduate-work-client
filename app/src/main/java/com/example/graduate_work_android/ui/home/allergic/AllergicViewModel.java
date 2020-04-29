package com.example.graduate_work_android.ui.home.allergic;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.databinding.DialogBinding;
import com.example.graduate_work_android.models.ResponseAllergic;
import com.example.graduate_work_android.models.ResponseModel;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.utils.callback.CallBackResponse;
import com.example.graduate_work_android.utils.callback.CallbackAllergic;

import java.util.List;

public class AllergicViewModel extends ViewModel implements CallbackAllergic, CallBackResponse {
    private MutableLiveData<List<String>> data = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>(true);
    public MutableLiveData<String> name = new MutableLiveData<>();
    private Repository repository;
    private FragmentActivity activity;
    private String mobile;
    private Dialog dialog;
    void init(FragmentActivity activity) {
        this.activity = activity;
        repository = new Repository();
        SharedPreferences sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        mobile = sharedPreferences.getString("mobileNumber", "0000000000");
        repository.getAllergic(mobile, this);
    }

    @Override
    public void responseAllergic(ResponseAllergic responseAllergic) {
        isLoader.postValue(false);
        if (responseAllergic.isSuccess()) {
            data.postValue(responseAllergic.getAllergic());
        }
    }

    @Override
    public void response(ResponseModel responseModel) {
        isLoader.postValue(false);
        dialog.dismiss();

        if (responseModel.isSuccess()) {
            if (data.getValue() != null) {
                data.getValue().add(name.getValue());
                data.postValue(data.getValue());
            }
        }
        name.setValue("");
    }

    @Override
    public void errorResponse(Throwable throwable) {
        isLoader.postValue(false);
        Toast.makeText(activity, "server error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(Throwable throwable) {
        isLoader.postValue(false);
    }

    public MutableLiveData<List<String>> getData() {
        return data;
    }

    public void add() {
        dialog = new Dialog(activity);
        DialogBinding binding = DialogBinding.inflate(LayoutInflater.from(activity));
        dialog.setContentView(binding.getRoot());
        binding.setLifecycleOwner(activity);
        binding.setViewModel(AllergicViewModel.this);
        dialog.show();
    }

    public void addAllergic() {
        if (name.getValue() != null && !name.getValue().isEmpty())
            repository.addAllergic(mobile, name.getValue(), this);
        else
            dialog.dismiss();
    }


}
