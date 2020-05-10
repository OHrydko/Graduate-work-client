package com.example.graduate_work_android.models;

public class Supplement implements RowType {
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


    public String getDanger() {
        return danger;
    }

    @Override
    public String getCategory() {
        return categories;
    }

    @Override
    public String getId() {
        return number_supplement;
    }

    @Override
    public String getAllergic() {
        return null;
    }

    @Override
    public String getPhoto() {
        return null;
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

}
