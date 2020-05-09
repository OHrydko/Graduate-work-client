package com.example.graduate_work_android.ui.home.photo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.R;
import com.example.graduate_work_android.models.Prediction;
import com.example.graduate_work_android.models.ResponsePrediction;
import com.example.graduate_work_android.models.ResponseUploadImage;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.ui.home.product.ProductFragment;
import com.example.graduate_work_android.ui.home.response_image.ResponseImageFragment;
import com.example.graduate_work_android.utils.ImageBottomSheet;
import com.example.graduate_work_android.utils.callback.CallBackPrediction;
import com.example.graduate_work_android.utils.callback.CallBackUpload;
import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

import java.io.File;

public class PhotoViewModel extends ViewModel implements CallBackUpload, CallBackPrediction {

    private ImageBottomSheet imageBottomSheet;
    private FragmentActivity activity;
    private SharedPreferences sharedPreferences;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> type = new MutableLiveData<>();
    public MutableLiveData<String> photoName = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoader = new MutableLiveData<>(false);
    private MutableLiveData<ResponseUploadImage> response = new MutableLiveData<>();
    public MutableLiveData<Prediction> prediction = new MutableLiveData<>();
    private File file;
    private Repository repository;

    void init(FragmentActivity activity, StartActivityForResultCallback start) {
        imageBottomSheet = new ImageBottomSheet(activity, start);
        sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        this.activity = activity;
        photoName.postValue(activity.getResources()
                .getString(R.string.upload_picture_with_ingredient));
    }

    public void clickButton() {
        imageBottomSheet.startBottomSheet();
    }

    public void analyze() {
        if (file != null && name.getValue() != null && !name.getValue().isEmpty()) {
            isLoader.postValue(true);
            repository = new Repository();
            repository.uploadFile(name.getValue(), sharedPreferences
                            .getString("mobileNumber", "0000000000"),
                    file, this);
        } else {
            Toast.makeText(activity, "Заповніть всі поля", Toast.LENGTH_SHORT).show();
        }
    }

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        file = imageBottomSheet.getParam(requestCode, resultCode,
                data, activity);
        photoName.postValue(file.getName());

    }

    @Override
    public void responseUploadImage(ResponseUploadImage responseUploadImage) {
        isLoader.postValue(false);
        if (responseUploadImage.isSuccess()) {
            response.postValue(responseUploadImage);
            repository.prediction(sharedPreferences
                            .getString("mobileNumber", "0000000000"), type.getValue(),
                            responseUploadImage.getDanger(),
                    this);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new ResponseImageFragment())
                    .addToBackStack(null)
                    .commit();
            Log.d("res", responseUploadImage.getResult());

        } else {
            throwable();
            Log.d("res", responseUploadImage.getText());
        }
    }

    @Override
    public void throwable() {
        isLoader.postValue(false);
        Toast.makeText(activity, "server error", Toast.LENGTH_SHORT).show();
    }

    public MutableLiveData<ResponseUploadImage> getResponse() {
        return response;
    }

    public void add() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new ProductFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void response(ResponsePrediction prediction) {
        if (prediction.isSuccess()) {
            this.prediction.postValue(prediction.getProduct());
        }
    }

    @Override
    public void error(Throwable throwable) {

    }
}
