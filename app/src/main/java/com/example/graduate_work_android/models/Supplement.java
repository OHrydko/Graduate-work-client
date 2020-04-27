package com.example.graduate_work_android.models;

public class Supplement {
    private String categories;
    private String danger;
    private String name;
    private String number_supplement;

    public Supplement(String categories, String danger, String name, String number_supplement) {
        this.categories = categories;
        this.danger = danger;
        this.name = name;
        this.number_supplement = number_supplement;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDanger() {
        return danger;
    }

    public void setDanger(String danger) {
        this.danger = danger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber_supplement() {
        return number_supplement;
    }

    public void setNumber_supplement(String number_supplement) {
        this.number_supplement = number_supplement;
    }
}
