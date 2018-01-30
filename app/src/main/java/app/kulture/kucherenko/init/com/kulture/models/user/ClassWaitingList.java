package app.kulture.kucherenko.init.com.kulture.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 10.11.17.
 */

public class ClassWaitingList implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    private ClassWaitingList(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<ClassWaitingList> CREATOR = new Creator<ClassWaitingList>() {
        @Override
        public ClassWaitingList createFromParcel(Parcel in) {
            return new ClassWaitingList(in);
        }

        @Override
        public ClassWaitingList[] newArray(int size) {
            return new ClassWaitingList[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
    }
}