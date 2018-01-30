package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 10/24/17.
 */

public class SignInModel {

    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}