package app.kulture.kucherenko.init.com.kulture.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPastReservation implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("day_class_id")
    @Expose
    private int dayClassId;

    private UserPastReservation(Parcel in) {
        id = in.readInt();
        dayClassId = in.readInt();
    }

    public static final Creator<UserPastReservation> CREATOR = new Creator<UserPastReservation>() {
        @Override
        public UserPastReservation createFromParcel(Parcel in) {
            return new UserPastReservation(in);
        }

        @Override
        public UserPastReservation[] newArray(int size) {
            return new UserPastReservation[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayClassId() {
        return dayClassId;
    }

    public void setDayClassId(int dayClassId) {
        this.dayClassId = dayClassId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(dayClassId);
    }
}