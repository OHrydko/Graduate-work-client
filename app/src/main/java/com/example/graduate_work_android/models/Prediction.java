package com.example.graduate_work_android.models;

public class Prediction implements RowType{
    private String ingredient;
    private String photo;
    private String name;
    private String type;

    public Prediction(String ingredient, String photo, String name, String type) {
        this.ingredient = ingredient;
        this.photo = photo;
        this.name = name;
        this.type = type;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDanger() {
        return null;
    }

    @Override
    public String getCategory() {
        return type;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getAllergic() {
        return null;
    }

    public String getType() {
        return type;
    }
}
