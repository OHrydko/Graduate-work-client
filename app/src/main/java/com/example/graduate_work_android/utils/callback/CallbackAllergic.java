package com.example.graduate_work_android.utils.callback;

import com.example.graduate_work_android.models.ResponseAllergic;

public interface CallbackAllergic {
    void responseAllergic(ResponseAllergic responseAllergic);

    void error(Throwable throwable);
}
