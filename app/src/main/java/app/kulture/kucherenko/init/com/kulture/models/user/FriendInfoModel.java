package app.kulture.kucherenko.init.com.kulture.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 10.11.17.
 */

public class FriendInfoModel implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("is_verified_email")
    @Expose
    private boolean isVerifiedEmail;
    @SerializedName("is_member")
    @Expose
    private boolean isMember;


    protected FriendInfoModel(Parcel in) {
        id = in.readInt();
        email = in.readString();
        username = in.readString();
        phoneNumber = in.readString();
        image = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        isVerifiedEmail = in.readByte() != 0;
        isMember = in.readByte() != 0;
    }

    public static final Creator<FriendInfoModel> CREATOR = new Creator<FriendInfoModel>() {
        @Override
        public FriendInfoModel createFromParcel(Parcel in) {
            return new FriendInfoModel(in);
        }

        @Override
        public FriendInfoModel[] newArray(int size) {
            return new FriendInfoModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    public void setIsVerifiedEmail(boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    public boolean isIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(phoneNumber);
        parcel.writeString(image);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeByte((byte) (isVerifiedEmail ? 1 : 0));
        parcel.writeByte((byte) (isMember ? 1 : 0));
    }
}