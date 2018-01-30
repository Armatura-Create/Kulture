package app.kulture.kucherenko.init.com.kulture.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("username")
    @Expose
    private String username;
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
    @SerializedName("is_vip")
    @Expose
    private boolean isVIP;
    @SerializedName("vip_finish_date")
    @Expose
    private String vipFinishDate;
    @SerializedName("friends")
    @Expose
    private List<FriendInfoModel> friends = null;
    @SerializedName("class_waiting_list")
    @Expose
    private int[] classWaitingList;
    @SerializedName("user_reservations")
    @Expose
    private List<UserReservation> userReservations = null;
    @SerializedName("user_past_reservations")
    @Expose
    private List<UserPastReservation> userPastReservations = null;

    public UserInfoModel() {
    }

    public UserInfoModel(Parcel in) {
        id = in.readInt();
        email = in.readString();
        phoneNumber = in.readString();
        username = in.readString();
        image = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        isVerifiedEmail = in.readByte() != 0;
        isMember = in.readByte() != 0;
        friends = in.createTypedArrayList(FriendInfoModel.CREATOR);
        classWaitingList = in.createIntArray();
        userReservations = in.createTypedArrayList(UserReservation.CREATOR);
        userPastReservations = in.createTypedArrayList(UserPastReservation.CREATOR);
    }

    public static final Creator<UserInfoModel> CREATOR = new Creator<UserInfoModel>() {
        @Override
        public UserInfoModel createFromParcel(Parcel in) {
            return new UserInfoModel(in);
        }

        @Override
        public UserInfoModel[] newArray(int size) {
            return new UserInfoModel[size];
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<FriendInfoModel> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendInfoModel> friends) {
        this.friends = friends;
    }

    public boolean isVerifiedEmail() {
        return isVerifiedEmail;
    }

    public void setVerifiedEmail(boolean verifiedEmail) {
        isVerifiedEmail = verifiedEmail;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public String getVipFinishDate() {
        return vipFinishDate;
    }

    public void setVipFinishDate(String vipFinishDate) {
        this.vipFinishDate = vipFinishDate;
    }

    public int[] getClassWaitingList() {
        return classWaitingList;
    }

    public void setClassWaitingList(int[] classWaitingList) {
        this.classWaitingList = classWaitingList;
    }

    public List<UserReservation> getUserReservations() {
        return userReservations;
    }

    public void setUserReservations(List<UserReservation> userReservations) {
        this.userReservations = userReservations;
    }

    public List<UserPastReservation> getUserPastReservations() {
        return userPastReservations;
    }

    public void setUserPastReservations(List<UserPastReservation> userPastReservations) {
        this.userPastReservations = userPastReservations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(phoneNumber);
        parcel.writeString(username);
        parcel.writeString(image);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeByte((byte) (isVerifiedEmail ? 1 : 0));
        parcel.writeByte((byte) (isMember ? 1 : 0));
        parcel.writeTypedList(friends);
        parcel.writeIntArray(classWaitingList);
        parcel.writeTypedList(userReservations);
        parcel.writeTypedList(userPastReservations);
    }
}

