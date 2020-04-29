package com.example.graduate_work_android.models;

import java.util.List;

public class History {
    private String name;
    private List<String> allergic;
    private List<Supplement> list_of_e;
    private String photo;
    private String user_mobile;

    public History(String name, List<String> allergic, List<Supplement> list_of_e, String photo, String user_mobile) {
        this.name = name;
        this.allergic = allergic;
        this.list_of_e = list_of_e;
        this.photo = photo;
        this.user_mobile = user_mobile;
    }

    public String getName() {
        return name;
    }

    public List<String> getAllergic() {
        return allergic;
    }

    public List<Supplement> getList_of_e() {
        return list_of_e;
    }

    public String getPhoto() {
        return photo;
    }

    public String getUser_mobile() {
        return user_mobile;
    }
}
