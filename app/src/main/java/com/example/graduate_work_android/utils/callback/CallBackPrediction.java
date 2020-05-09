package com.example.graduate_work_android.utils.callback;

import com.example.graduate_work_android.models.ResponsePrediction;

public interface CallBackPrediction {
    void response(ResponsePrediction prediction);
    void error(Throwable throwable);
}
