package com.example.graduate_work_android.models;

import java.util.List;

public class ResponseAllergic {

    private int status;
    private boolean success;
    private String text;
    private List<String> allergic;

    public ResponseAllergic(int status, boolean success, String text, List<String> allergic) {
        this.status = status;
        this.success = success;
        this.text = text;
        this.allergic = allergic;
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

    public List<String> getAllergic() {
        return allergic;
    }
}
