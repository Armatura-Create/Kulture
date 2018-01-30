package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin-user on 28.10.17.
 */

public class TokenAtRegistration {
    @SerializedName("token")
    @Expose
    private String regist_token;


    public void setRegist_token(String regist_token){
        this.regist_token = regist_token;
    }

    public String getRegist_token(){
        return regist_token;
    }
}
