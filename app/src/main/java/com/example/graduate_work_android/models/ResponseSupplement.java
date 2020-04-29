package com.example.graduate_work_android.models;

import java.util.List;

public class ResponseSupplement {
    private int status;
    private boolean success;
    private String text;
    private List<Supplement> supplement;

    public ResponseSupplement(int status, boolean success, String text, List<Supplement> supplement) {
        this.status = status;
        this.success = success;
        this.text = text;
        this.supplement = supplement;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getText() {
        return text;
    }

    public List<Supplement> getSupplement() {
        return supplement;
    }
}
