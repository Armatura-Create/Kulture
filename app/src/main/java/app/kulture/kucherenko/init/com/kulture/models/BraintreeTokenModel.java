package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 02.11.17.
 */

public class BraintreeTokenModel {

    @SerializedName("braintree_token")
    @Expose
    private String braintreeToken;

    public String getBraintreeToken() {
        return braintreeToken;
    }

    public void setBraintreeToken(String braintreeToken) {
        this.braintreeToken = braintreeToken;
    }
}
