package app.kulture.kucherenko.init.com.kulture.models.forgotpass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 21.11.17.
 */

public class ForgotPassModel {

    @SerializedName("email")
    @Expose
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
