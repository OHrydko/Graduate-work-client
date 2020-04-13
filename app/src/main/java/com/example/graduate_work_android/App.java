package com.example.graduate_work_android;

import android.app.Application;

import com.example.graduate_work_android.di.Component;
import com.example.graduate_work_android.di.DaggerComponent;
import com.example.graduate_work_android.di.RetrofitModule;


public class App extends Application {

    private static Component component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerComponent.builder()
                .application(this)
                .retrofitModule(new RetrofitModule())
                .build();

    }

    public static Component getComponent() {
        return component;
    }
}

