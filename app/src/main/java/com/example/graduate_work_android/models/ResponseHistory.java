package com.example.graduate_work_android.models;

import java.util.List;

public class ResponseHistory {
    private int status;
    private boolean success;
    private String text;
    private List<History> histories;

    public ResponseHistory(int status, boolean success, String text, List<History> histories) {
        this.status = status;
        this.success = success;
        this.text = text;
        this.histories = histories;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
