
package app.kulture.kucherenko.init.com.kulture.models.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WaitingList implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("username")
    @Expose
    private String username;

    private WaitingList(Parcel in) {
        id = in.readInt();
        username = in.readString();
    }

    public static final Creator<WaitingList> CREATOR = new Creator<WaitingList>() {
        @Override
        public WaitingList createFromParcel(Parcel in) {
            return new WaitingList(in);
        }

        @Override
        public WaitingList[] newArray(int size) {
            return new WaitingList[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
    }
}
