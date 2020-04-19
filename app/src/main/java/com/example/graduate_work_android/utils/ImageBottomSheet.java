package com.example.graduate_work_android.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.graduate_work_android.models.ResponseUploadImage;
import com.example.graduate_work_android.repository.Repository;
import com.example.graduate_work_android.utils.callback.CallBackUpload;
import com.example.graduate_work_android.utils.callback.StartActivityForResultCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class ImageBottomSheet implements BottomFragment.ItemClickListener, CallBackUpload {

    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;
    private FragmentActivity activity;
    private Uri imageUri;
    private StartActivityForResultCallback startActivity;
    private static final int STORAGE_PERMISSION_CODE = 100;
    private SharedPreferences sharedPreferences;

    public ImageBottomSheet(FragmentActivity activity, StartActivityForResultCallback startActivity) {
        this.activity = activity;
        this.startActivity = startActivity;
        sharedPreferences = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
    }


    @SuppressLint("CheckResult")
    public HashMap<String, String> getParam(int requestCode, int resultCode, @Nullable Intent data,
                                            FragmentActivity activity) {
        File file = null;
        HashMap<String, String> sendData = new HashMap<>();
        if (resultCode == RESULT_OK) {


            if (requestCode == REQUEST_CODE_GALLERY) {
                Uri selectedImage = null;
                if (data != null) {
                    selectedImage = data.getData();
                }
                if (selectedImage != null) {
                    file = new File(Objects.requireNonNull(FileUtil.getPath(selectedImage, activity)));

                }
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                if (imageUri != null)
                    file = new File(Objects.requireNonNull(FileUtil.getPath(imageUri, activity)));
            }

            if (requestCode == REQUEST_CODE_CAMERA || requestCode == REQUEST_CODE_GALLERY) {
                if (file != null) {
                    Repository repository = new Repository();
                    repository.uploadFile(sharedPreferences
                                    .getString("mobileNumber", "0000000000"),
                                    file, this);
                }

            }
        }
        return sendData;
    }


    public void startBottomSheet() {
        BottomFragment bottomFragment = new BottomFragment(this);
        bottomFragment.show(activity.getSupportFragmentManager(), bottomFragment.getTag());
    }

    @Override
    public void onItemClick(String item) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {

            switch (item) {
                case "camera":
                    sendCamera();
                    break;
                case "picture":
                    sendIntent();
                    break;

            }
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            }

        }

    }

    private void sendIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivity.start(intent, REQUEST_CODE_GALLERY);
    }

    private void sendCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = activity.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivity.start(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    public void responseUploadImage(ResponseUploadImage responseUploadImage) {
        if (responseUploadImage.isSuccess())
            Log.d("res", responseUploadImage.getResult());
        else
            Log.d("res", responseUploadImage.getText());

    }

//    private boolean checkSize(List<String> path) {
//        long size = 0;
//        for (String row : path) {
//            size += new File(row).length();
//        }
//        return size < 5242880;
//    }

}


