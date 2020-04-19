package com.example.graduate_work_android.ui.home;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.utils.ImageBottomSheet;
import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

import java.util.HashMap;

public class HomeViewModel extends ViewModel {
    private ImageBottomSheet imageBottomSheet;
    private FragmentActivity activity;

    void init(FragmentActivity activity, StartActivityForResultCallback start) {
        imageBottomSheet = new ImageBottomSheet(activity, start);
        this.activity = activity;

    }

    public void clickButton() {
        imageBottomSheet.startBottomSheet();
    }


    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        HashMap<String, String> sendData = imageBottomSheet.getParam(requestCode, resultCode,
                data, activity);

    }

}
