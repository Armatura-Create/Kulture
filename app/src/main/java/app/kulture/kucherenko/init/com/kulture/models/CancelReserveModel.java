package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 10.11.17.
 */

public class CancelReserveModel {

    @SerializedName("reservation_id")
    @Expose
    private int reserveId;

    public int getReserveId() {
        return reserveId;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }
}
