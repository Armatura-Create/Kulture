package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.models.classes.Classes;
import app.kulture.kucherenko.init.com.kulture.models.classes.PastClasses;
import app.kulture.kucherenko.init.com.kulture.models.classes.Teacher;

public class AllStudios {

    @SerializedName("classes")
    @Expose
    private Classes[] classes;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("admins")
    @Expose
    private String[] admins;

    @SerializedName("past_classes")
    @Expose
    private PastClasses[] past_classes;

    @SerializedName("teachers")
    @Expose
    private List<Teacher> teachers;

    @SerializedName("banner")
    @Expose
    private String banner;

    @SerializedName("info")
    @Expose
    private String info;

    public Classes[] getClasses() {
        return classes;
    }

    public void setClasses(Classes[] classes) {
        this.classes = classes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAdmins() {
        return admins;
    }

    public void setAdmins(String[] admins) {
        this.admins = admins;
    }

    public PastClasses[] getPast_classes() {
        return past_classes;
    }

    public void setPast_classes(PastClasses[] past_classes) {
        this.past_classes = past_classes;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
