
package app.kulture.kucherenko.init.com.kulture.models.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassReservation implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;

    private ClassReservation(Parcel in) {
        id = in.readInt();
        user = in.readParcelable(User.class.getClassLoader());
        position = in.readInt();
        creationDate = in.readString();
    }

    public static final Creator<ClassReservation> CREATOR = new Creator<ClassReservation>() {
        @Override
        public ClassReservation createFromParcel(Parcel in) {
            return new ClassReservation(in);
        }

        @Override
        public ClassReservation[] newArray(int size) {
            return new ClassReservation[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(user, i);
        parcel.writeInt(position);
        parcel.writeString(creationDate);
    }
}
