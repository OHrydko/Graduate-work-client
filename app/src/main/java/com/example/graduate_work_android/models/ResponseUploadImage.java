package com.example.graduate_work_android.models;

import java.util.List;

public class ResponseUploadImage {
    private int status;
    private boolean success;
    private String result;
    private String text;
    private int danger;
    private List<String> allergic;
    private List<Supplement> supplement;

    public ResponseUploadImage(int status, boolean success, String result, String text,
                               int danger, List<String> allergic, List<Supplement> supplement) {
        this.status = status;
        this.success = success;
        this.result = result;
        this.text = text;
        this.danger = danger;
        this.allergic = allergic;
        this.supplement = supplement;
    }

    public int getStatus() {
        return status;
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

    public int getDanger() {
        return danger;
    }

    public List<String> getAllergic() {
        return allergic;
    }

    public List<Supplement> getSupplement() {
        return supplement;
    }
}
