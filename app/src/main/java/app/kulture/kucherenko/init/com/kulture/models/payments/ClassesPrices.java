package app.kulture.kucherenko.init.com.kulture.models.payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.kulture.kucherenko.init.com.kulture.models.classes.Teacher;

public class ClassesPrices {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("teacher")
    @Expose
    private Teacher teacher;

    @SerializedName("max_users_count")
    @Expose
    private Integer maxUsersCount;

    @SerializedName("type")
    @Expose
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getMaxUsersCount() {
        return maxUsersCount;
    }

    public void setMaxUsersCount(Integer maxUsersCount) {
        this.maxUsersCount = maxUsersCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
