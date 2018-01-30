package app.kulture.kucherenko.init.com.kulture.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 10.11.17.
 */

public class ReserveClassModel extends ReserveSimpleClassModel implements Parcelable{

    @SerializedName("position")
    @Expose
    private int position;

    private ReserveClassModel(Parcel in) {
        super(in);
        position = in.readInt();
    }

    public ReserveClassModel() {
    }

    public static final Creator<ReserveClassModel> CREATOR = new Creator<ReserveClassModel>() {
        @Override
        public ReserveClassModel createFromParcel(Parcel in) {
            return new ReserveClassModel(in);
        }

        @Override
        public ReserveClassModel[] newArray(int size) {
            return new ReserveClassModel[size];
        }
    };

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeInt(dayClassId);
        parcel.writeInt(position);
    }
}
