package com.example.graduate_work_android.models;

public class ResponsePrediction {
    private int status;
    private boolean success;
    private Prediction product;

    public ResponsePrediction(int status, boolean success, Prediction product) {
        this.status = status;
        this.success = success;
        this.product = product;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public Prediction getProduct() {
        return product;
    }
}
