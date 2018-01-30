package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 12.10.17.
 */

public class FacebookTokenModel {

    @SerializedName("facebook_access_token")
    @Expose
    private String facebookToken;

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    @Override
    public String toString() {
        return "FacebookTokenModel{" +
                "facebookToken='" + facebookToken + '\'' +
                '}';
    }
}