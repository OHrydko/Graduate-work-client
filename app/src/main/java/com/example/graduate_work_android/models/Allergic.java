package com.example.graduate_work_android.models;

public class Allergic implements RowType{
    private String allergic;

    public Allergic(String allergic) {
        this.allergic = allergic;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDanger() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getAllergic() {
        return allergic;
    }
}
