package com.example.graduate_work_android.models;

public class ResponseModel {
    private int status;
    private boolean success;
    private String mobile_number;
    private String text;

    public ResponseModel(int status, boolean success, String mobile_number, String text) {
        this.status = status;
        this.success = success;
        this.mobile_number = mobile_number;
        this.text = text;
    }

    public int getStatus() {
        return status;
    }


    public boolean isSuccess() {
        return success;
    }


    public String getMobile_number() {
        return mobile_number;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
