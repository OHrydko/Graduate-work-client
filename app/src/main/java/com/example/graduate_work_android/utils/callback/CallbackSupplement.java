package com.example.graduate_work_android.utils.callback;

import com.example.graduate_work_android.models.ResponseSupplement;

public interface CallbackSupplement {
    void responseSupplement(ResponseSupplement response);
    void error(Throwable throwable);
}
