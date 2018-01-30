package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 20.10.17.
 */

public class UpdateUserInfoModel {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("last_name")
    @Expose
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UpdateUserInfoModel{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}