package com.example.graduate_work_android.models;

import java.util.List;

public class ResponseUploadImage {
    private int status;
    private boolean success;
    private String result;
    private String text;
    private List<String> allergic;
    private List<Supplement> supplement;

    public ResponseUploadImage(int status, boolean success, String result, String text,
                               List<String> allergic, List<Supplement> supplement) {
        this.status = status;
        this.success = success;
        this.result = result;
        this.text = text;
        this.allergic = allergic;
        this.supplement = supplement;
    }


    public List<String> getAllergic() {
        return allergic;
    }


    public List<Supplement> getSupplement() {
        return supplement;
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
