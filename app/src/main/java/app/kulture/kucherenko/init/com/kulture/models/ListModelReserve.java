package app.kulture.kucherenko.init.com.kulture.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by user on 23.11.17.
 */

public class ListModelReserve implements Parcelable {
    private List<ReserveClassModel> reserveClassModel;

    public ListModelReserve(Parcel in) {
        reserveClassModel = in.createTypedArrayList(ReserveClassModel.CREATOR);
    }

    public static final Creator<ListModelReserve> CREATOR = new Creator<ListModelReserve>() {
        @Override
        public ListModelReserve createFromParcel(Parcel in) {
            return new ListModelReserve(in);
        }

        @Override
        public ListModelReserve[] newArray(int size) {
            return new ListModelReserve[size];
        }
    };

    public ListModelReserve() {

    }

    public List<ReserveClassModel> getReserveClassModel() {
        return reserveClassModel;
    }

    public void setReserveClassModel(List<ReserveClassModel> reserveClassModel) {
        this.reserveClassModel = reserveClassModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(reserveClassModel);
    }
}
