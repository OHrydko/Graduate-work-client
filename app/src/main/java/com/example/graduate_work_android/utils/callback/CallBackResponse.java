package com.example.graduate_work_android.utils.callback;

import com.example.graduate_work_android.models.ResponseModel;

public interface CallBackResponse {
    void response(ResponseModel responseModel);
    void errorResponse(Throwable throwable);
}
