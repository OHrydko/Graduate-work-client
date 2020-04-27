package com.example.graduate_work_android.models;

public class History {
    private String name;
    private String allergic;
    private String list_of_e;
    private String photo;
    private String user_mobile;

    public History(String name, String allergic, String list_of_e, String photo, String user_mobile) {
        this.name = name;
        this.allergic = allergic;
        this.list_of_e = list_of_e;
        this.photo = photo;
        this.user_mobile = user_mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllergic() {
        return allergic;
    }

    public void setAllergic(String allergic) {
        this.allergic = allergic;
    }

    public String getList_of_e() {
        return list_of_e;
    }

    public void setList_of_e(String list_of_e) {
        this.list_of_e = list_of_e;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }
}
