package com.example.graduate_work_android.models;

public class Login {
    private int status;
    private boolean success;
    private String mobile_number;
    private String text;

    public Login(int status, boolean success, String mobile_number, String text) {
        this.status = status;
        this.success = success;
        this.mobile_number = mobile_number;
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
