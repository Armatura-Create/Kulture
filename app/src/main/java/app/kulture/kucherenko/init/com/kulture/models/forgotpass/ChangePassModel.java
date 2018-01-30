package app.kulture.kucherenko.init.com.kulture.models.forgotpass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 21.11.17.
 */

public class ChangePassModel {

    @SerializedName("forgot_password_key")
    @Expose
    private String forgotPassKey;

    @SerializedName("new_password")
    @Expose
    private String newPass;

    public String getForgotPassKey() {
        return forgotPassKey;
    }

    public void setForgotPassKey(String forgotPassKey) {
        this.forgotPassKey = forgotPassKey;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
