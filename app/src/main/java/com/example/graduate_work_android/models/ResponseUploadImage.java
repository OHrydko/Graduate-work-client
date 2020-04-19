package com.example.graduate_work_android.models;

public class ResponseUploadImage {
    private int status;
    private boolean success;
    private String result;
    private String text;

    public ResponseUploadImage(int status, boolean success, String result, String text) {
        this.status = status;
        this.success = success;
        this.result = result;
        this.text = text;
    }


    public boolean isSuccess() {
        return success;
    }


    public String getResult() {
        return result;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatus() {
        return status;
    }
}
