
package app.kulture.kucherenko.init.com.kulture.models.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Classes implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("prices")
    @Expose
    private List<Price> prices = null;
    @SerializedName("teacher")
    @Expose
    private Teacher teacher;
    @SerializedName("level_of_difficulty")
    @Expose
    private int levelOfDifficulty;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("max_users_count")
    @Expose
    private int maxUsersCount;
    @SerializedName("class_photos")
    @Expose
    private List<ClassPhoto> classPhotos = null;
    @SerializedName("studio")
    @Expose
    private Studio studio;

    protected Classes(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        prices = in.createTypedArrayList(Price.CREATOR);
        teacher = in.readParcelable(Teacher.class.getClassLoader());
        levelOfDifficulty = in.readInt();
        info = in.readString();
        maxUsersCount = in.readInt();
        classPhotos = in.createTypedArrayList(ClassPhoto.CREATOR);
        studio = in.readParcelable(Studio.class.getClassLoader());
    }

    public static final Creator<Classes> CREATOR = new Creator<Classes>() {
        @Override
        public Classes createFromParcel(Parcel in) {
            return new Classes(in);
        }

        @Override
        public Classes[] newArray(int size) {
            return new Classes[size];
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getLevelOfDifficulty() {
        return levelOfDifficulty;
    }

    public void setLevelOfDifficulty(int levelOfDifficulty) {
        this.levelOfDifficulty = levelOfDifficulty;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<ClassPhoto> getClassPhotos() {
        return classPhotos;
    }

    public void setClassPhotos(List<ClassPhoto> classPhotos) {
        this.classPhotos = classPhotos;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
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
        parcel.writeString(type);
        parcel.writeTypedList(prices);
        parcel.writeParcelable(teacher, i);
        parcel.writeInt(levelOfDifficulty);
        parcel.writeString(info);
        parcel.writeInt(maxUsersCount);
        parcel.writeTypedList(classPhotos);
        parcel.writeParcelable(studio, i);
    }
}
