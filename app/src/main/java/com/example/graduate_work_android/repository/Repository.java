package com.example.graduate_work_android.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.graduate_work_android.App;
import com.example.graduate_work_android.utils.callback.CallBackLogin;
import com.example.graduate_work_android.utils.callback.CallBackRegistration;
import com.example.graduate_work_android.utils.callback.CallBackUpload;
import com.example.graduate_work_android.utils.callback.CallbackHistory;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Repository {

    @SuppressLint("CheckResult")
    public void login(String mobileNumber, String passwords, CallBackLogin callBackLogin) {
        RequestBody mobile = RequestBody.create(mobileNumber, MediaType.parse("text/plain"));

        RequestBody password = RequestBody.create(passwords, MediaType.parse("text/plain"));
        App.getComponent().getApi().login(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBackLogin::response,
                        throwable ->
                                Log.d("throwable", throwable.getMessage() + ""));
    }

    @SuppressLint("CheckResult")
    public void registration(String mobileNumber, String passwordField, String nameField,
                             String lastNameField, String birthdayField, String userNameField,
                             CallBackRegistration callBackRegistration) {

        RequestBody mobile = RequestBody.create(mobileNumber, MediaType.parse("text/plain"));

        RequestBody password = RequestBody.create(passwordField, MediaType.parse("text/plain"));
        RequestBody name = RequestBody.create(nameField, MediaType.parse("text/plain"));
        RequestBody lastName = RequestBody.create(lastNameField, MediaType.parse("text/plain"));
        RequestBody birthday = RequestBody.create(birthdayField.replace(" ", ""),
                MediaType.parse("text/plain"));
        RequestBody userName = RequestBody.create(userNameField, MediaType.parse("text/plain"));

        App.getComponent().getApi()
                .registration(mobile, password, name, lastName, birthday, userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBackRegistration::response,
                        throwable ->
                                Log.d("throwable", throwable.getMessage() + ""));
    }

    @SuppressLint("CheckResult")
    public void uploadFile(String names, String mobileNumber, File file, CallBackUpload callBackUpload) {
        RequestBody mobile = RequestBody.create(mobileNumber,
                MediaType.parse("text/plain"));

        RequestBody name = RequestBody.create(names,
                MediaType.parse("text/plain"));

        RequestBody requestFile =
                RequestBody.create(file, MediaType.parse("image/jpeg"));

        MultipartBody.Part fileToUpload =
                MultipartBody.Part.createFormData("file", file.getName(),
                        requestFile);
        App.getComponent().getApi().uploadImage(name, mobile, fileToUpload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBackUpload::responseUploadImage,
                        throwable ->
                                Log.d("throwable", throwable.getMessage() + ""));
    }

    @SuppressLint("CheckResult")
    public void history(String mobileNumber, CallbackHistory callbackHistory) {

        App.getComponent().getApi().history(mobileNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callbackHistory::responseHistory,
                        throwable ->
                                Log.d("throwable", throwable.getMessage() + ""));
    }
}
