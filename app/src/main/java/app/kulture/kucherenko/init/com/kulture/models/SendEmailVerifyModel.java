package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 06.12.17.
 */

public class SendEmailVerifyModel {
    @SerializedName("username")
    @Expose
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String token) {
        this.username = username;
    }
}
