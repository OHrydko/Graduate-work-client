package com.example.graduate_work_android.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.graduate_work_android.ui.home.HomeActivity;
import com.example.graduate_work_android.ui.login.LoginActivity;

public class MainViewModel extends ViewModel {


    @SuppressLint("CheckResult")
    void init(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("User",
                Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("login", false);
        context.startActivity(new Intent(context, (isLogin) ?
                HomeActivity.class : LoginActivity.class));
    }


}
