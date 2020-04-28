package com.example.graduate_work_android.models;

public interface RowType {
    int SUPPLEMENT = 0;
    int ALLERGIC = 1;

    String getName();
    String getDanger();
    String getCategory();
    String getId();
    String getAllergic();
}
