package com.example.graduate_work_android.models;

public class Prediction {
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

    public String getType() {
        return type;
    }
}
