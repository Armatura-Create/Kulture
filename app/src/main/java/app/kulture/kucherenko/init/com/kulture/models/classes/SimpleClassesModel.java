package app.kulture.kucherenko.init.com.kulture.models.classes;

/**
 * Created by slava on 23.11.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleClassesModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("teacher")
    @Expose
    private Teacher teacher;
    @SerializedName("max_users_count")
    @Expose
    private int maxUsersCount;
    @SerializedName("type")
    @Expose
    private String type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getMaxUsersCount() {
        return maxUsersCount;
    }

    public void setMaxUsersCount(int maxUsersCount) {
        this.maxUsersCount = maxUsersCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
