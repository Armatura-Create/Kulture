package app.kulture.kucherenko.init.com.kulture.models.forgotpass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 21.11.17.
 */

public class PassKeyModel {

    @SerializedName("forgot_password_key")
    @Expose
    private String forgotPassKey;

    public String getForgotPassKey() {
        return forgotPassKey;
    }

    public void setForgotPassKey(String forgotPassKey) {
        this.forgotPassKey = forgotPassKey;
    }
}
