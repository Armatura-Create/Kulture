package app.kulture.kucherenko.init.com.kulture.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 10.11.17.
 */

public class UserReservation implements Parcelable {

    @SerializedName("id")
    @Expose
    private int reservationId;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("day_class_id")
    @Expose
    private int dayClassId;
    @SerializedName("status")
    @Expose
    private String status;

    private UserReservation(Parcel in) {
        reservationId = in.readInt();
        position = in.readInt();
        creationDate = in.readString();
        dayClassId = in.readInt();
        status = in.readString();
    }

    public static final Creator<UserReservation> CREATOR = new Creator<UserReservation>() {
        @Override
        public UserReservation createFromParcel(Parcel in) {
            return new UserReservation(in);
        }

        @Override
        public UserReservation[] newArray(int size) {
            return new UserReservation[size];
        }
    };

    public int getId() {
        return reservationId;
    }

    public void setId(int id) {
        this.reservationId = id;
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

    public int getDayClassId() {
        return dayClassId;
    }

    public void setDayClassId(int dayClassId) {
        this.dayClassId = dayClassId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(reservationId);
        parcel.writeInt(position);
        parcel.writeString(creationDate);
        parcel.writeInt(dayClassId);
        parcel.writeString(status);
    }
}