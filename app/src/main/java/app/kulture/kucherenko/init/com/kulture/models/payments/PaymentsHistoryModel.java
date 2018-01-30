package app.kulture.kucherenko.init.com.kulture.models.payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentsHistoryModel {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("create_date")
    @Expose
    private String createDate;

    @SerializedName("price")
    @Expose
    private PaymentsPrice price;

    @SerializedName("payment_type")
    @Expose
    private String paymentType;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public PaymentsPrice getPrice() {
        return price;
    }

    public void setPrice(PaymentsPrice price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
