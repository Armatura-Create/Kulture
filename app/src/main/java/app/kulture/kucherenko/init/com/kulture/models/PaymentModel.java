package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 02.11.17.
 */

public class PaymentModel {

    @SerializedName("payment_method_nonce")
    @Expose
    private String paymentMethodNonce;

    @SerializedName("price_id")
    @Expose
    private int priceId;

    public String getPaymentMethodNonce() {
        return paymentMethodNonce;
    }

    public void setPaymentMethodNonce(String paymentMethodNonce) {
        this.paymentMethodNonce = paymentMethodNonce;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "paymentMethodNonce='" + paymentMethodNonce + '\'' +
                ", priceId=" + priceId +
                '}';
    }
}
