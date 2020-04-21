package com.example.graduate_work_android.ui.home;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.models.ResponseUploadImage;
import com.example.graduate_work_android.utils.ImageBottomSheet;
import com.example.graduate_work_android.utils.callback.CallBackUpload;
import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

import java.util.HashMap;

public class HomeViewModel extends ViewModel implements CallBackUpload {
    private ImageBottomSheet imageBottomSheet;
    private FragmentActivity activity;
    public MutableLiveData<String> text = new MutableLiveData<>("Click");

    void init(FragmentActivity activity, StartActivityForResultCallback start) {
        imageBottomSheet = new ImageBottomSheet(activity, start, this);
        this.activity = activity;

    }

    public void clickButton() {
        imageBottomSheet.startBottomSheet();
    }


    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        HashMap<String, String> sendData = imageBottomSheet.getParam(requestCode, resultCode,
                data, activity);

    }

    @Override
    public void responseUploadImage(ResponseUploadImage responseUploadImage) {
        if (responseUploadImage.isSuccess()) {
            text.postValue(responseUploadImage.getResult());
            Log.d("res", responseUploadImage.getResult());
        } else {
            Log.d("res", responseUploadImage.getText());
        }

    }

}
