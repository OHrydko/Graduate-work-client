package com.example.graduate_work_android.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.App;
import com.example.graduate_work_android.models.Login;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainViewModel extends ViewModel {

    @SuppressLint("CheckResult")
    void init(Context context) {

        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"),
                "0633168638");

        RequestBody password = RequestBody.create(MediaType.parse("text/plain"),
                "123");
        App.getComponent().getApi().login(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::login,
                        throwable ->
                                Log.d("throwable", throwable.getMessage() + ""));
    }

    private void login(Login login) {
        Log.d("result", login.getMobile_number() + "");
    }
}
