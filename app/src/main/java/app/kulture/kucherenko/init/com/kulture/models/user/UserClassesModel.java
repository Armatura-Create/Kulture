package app.kulture.kucherenko.init.com.kulture.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.kulture.kucherenko.init.com.kulture.models.classes.Teacher;

/**
 * Created by slava on 10.11.17.
 */

public class UserClassesModel implements Parcelable {

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

    private UserClassesModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        teacher = in.readParcelable(Teacher.class.getClassLoader());
        maxUsersCount = in.readInt();
    }

    public static final Creator<UserClassesModel> CREATOR = new Creator<UserClassesModel>() {
        @Override
        public UserClassesModel createFromParcel(Parcel in) {
            return new UserClassesModel(in);
        }

        @Override
        public UserClassesModel[] newArray(int size) {
            return new UserClassesModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeParcelable(teacher, i);
        parcel.writeInt(maxUsersCount);
    }
}