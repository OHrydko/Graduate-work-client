package com.example.graduate_work_android.ui.home.product;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.models.ResponseModel;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.ui.home.response_image.ResponseImageFragment;
import com.example.graduate_work_android.utils.ImageBottomSheet;
import com.example.graduate_work_android.utils.callback.CallBackResponse;
import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

import java.io.File;

public class ProductViewModel extends ViewModel implements CallBackResponse {

    public MutableLiveData<String> type = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> ingredient = new MutableLiveData<>();
    public MutableLiveData<String> photoName = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>();
    private FragmentActivity activity;
    private Repository repository;
    private SharedPreferences sharedPreferences;
    private ImageBottomSheet imageBottomSheet;
    private File file;

    void init(FragmentActivity activity, StartActivityForResultCallback start) {
        imageBottomSheet = new ImageBottomSheet(activity, start);
        sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        repository = new Repository();
        photoName.setValue(activity.getResources().getString(R.string.photo));
        this.activity = activity;
    }

    public void add() {
        if (file != null && name.getValue() != null && !name.getValue().isEmpty() && type != null
                && type.getValue() != null && ingredient != null && ingredient.getValue() != null) {
            isLoader.postValue(true);
            repository.addProduct(name.getValue(), sharedPreferences
                            .getString("mobileNumber", "0000000000"),
                    file, type.getValue(), ingredient.getValue(), this);
        }
    }

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        file = imageBottomSheet.getParam(requestCode, resultCode,
                data, activity);
        photoName.postValue(file.getName());

    }

    public void file() {
        imageBottomSheet.startBottomSheet();
    }

    @Override
    public void response(ResponseModel responseModel) {
        isLoader.postValue(false);
        if (responseModel.isSuccess()) {
            Toast.makeText(activity, "Продукт додано", Toast.LENGTH_SHORT).show();
            activity.getSupportFragmentManager().popBackStack();
        } else {
            Toast.makeText(activity, "server error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorResponse(Throwable throwable) {
        isLoader.postValue(false);
        Toast.makeText(activity, "server error", Toast.LENGTH_SHORT).show();
    }
}
