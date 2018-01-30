package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpToUnlimitedMemberModel {
    @SerializedName("payment_method_nonce")
    @Expose
    private String unlimitedMemberNonce;

    public String getUnlimitedMemberNonce() {
        return unlimitedMemberNonce;
    }

    public void setUnlimitedMemberNonce(String unlimitedMemberNonce) {
        this.unlimitedMemberNonce = unlimitedMemberNonce;
    }
}
