package app.kulture.kucherenko.init.com.kulture.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhoneModel {
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
