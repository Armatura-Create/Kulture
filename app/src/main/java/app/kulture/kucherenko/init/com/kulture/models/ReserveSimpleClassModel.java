package app.kulture.kucherenko.init.com.kulture.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 23.11.17.
 */

public class ReserveSimpleClassModel implements Parcelable{
    @SerializedName("user_id")
    @Expose
    protected int userId;
    @SerializedName("day_class_id")
    @Expose
    protected int dayClassId;

    protected ReserveSimpleClassModel(Parcel in) {
        userId = in.readInt();
        dayClassId = in.readInt();
    }

    public ReserveSimpleClassModel() {
    }

    public static final Creator<ReserveSimpleClassModel> CREATOR = new Creator<ReserveSimpleClassModel>() {
        @Override
        public ReserveSimpleClassModel createFromParcel(Parcel in) {
            return new ReserveSimpleClassModel(in);
        }

        @Override
        public ReserveSimpleClassModel[] newArray(int size) {
            return new ReserveSimpleClassModel[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        parcel.writeInt(userId);
        parcel.writeInt(dayClassId);
    }
}