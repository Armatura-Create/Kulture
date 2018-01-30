
package app.kulture.kucherenko.init.com.kulture.models.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassPhoto implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private String image;

    private ClassPhoto(Parcel in) {
        id = in.readInt();
        image = in.readString();
    }

    public static final Creator<ClassPhoto> CREATOR = new Creator<ClassPhoto>() {
        @Override
        public ClassPhoto createFromParcel(Parcel in) {
            return new ClassPhoto(in);
        }

        @Override
        public ClassPhoto[] newArray(int size) {
            return new ClassPhoto[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(image);
    }
}
