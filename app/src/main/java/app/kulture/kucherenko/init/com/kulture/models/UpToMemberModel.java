package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpToMemberModel {

    @SerializedName("payment_method_nonce")
    @Expose
    private String memberNonce;

    public String getMemberNonce() {
        return memberNonce;
    }

    public void setMemberNonce(String memberNonce) {
        this.memberNonce = memberNonce;
    }
}
