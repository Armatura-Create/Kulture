package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationData {

    @SerializedName("membership_price")
    @Expose
    private int memberPrice;

    @SerializedName("vip_price")
    @Expose
    private int vipPrice;

    @SerializedName("vip_time_interval")
    @Expose
    private int vipTimeInterval;

    @SerializedName("membership_reservation_time_interval")
    @Expose
    private int memberTimeReservation;

    @SerializedName("vip_reservation_time_interval")
    @Expose
    private int vipTimeReservation;


    public int getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(int memberPrice) {
        this.memberPrice = memberPrice;
    }

    public int getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(int vipPrice) {
        this.vipPrice = vipPrice;
    }

    public int getVipTimeInterval() {
        return vipTimeInterval;
    }

    public void setVipTimeInterval(int vipTimeInterval) {
        this.vipTimeInterval = vipTimeInterval;
    }

    public int getMemberTimeReservation() {
        return memberTimeReservation;
    }

    public void setMemberTimeReservation(int memberTimeReservation) {
        this.memberTimeReservation = memberTimeReservation;
    }

    public int getVipTimeReservation() {
        return vipTimeReservation;
    }

    public void setVipTimeReservation(int vipTimeReservation) {
        this.vipTimeReservation = vipTimeReservation;
    }
}
