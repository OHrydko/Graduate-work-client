package com.example.graduate_work_android.utils.callback;

import android.content.Intent;

public interface StartActivityForResultCallback {
    void start(Intent intent, int requestCode);
}
