package app.kulture.kucherenko.init.com.kulture.models.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by misha on 20.11.17.
 */

public class PastClasses {

    private Classes classes;

    private String id;

    private String end_time;

    private String start_time;

    @SerializedName("Class_reservations")
    @Expose
    private ClassReservation[] class_reservations;

    private String date;

    @SerializedName("Waiting_list")
    @Expose
    private WaitingList[] waiting_list;

    private String reserved_positions_count;

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public ClassReservation[] getClass_reservations() {
        return class_reservations;
    }

    public void setClass_reservations(ClassReservation[] class_reservations) {
        this.class_reservations = class_reservations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public WaitingList[] getWaiting_list() {
        return waiting_list;
    }

    public void setWaiting_list(WaitingList[] waiting_list) {
        this.waiting_list = waiting_list;
    }

    public String getReserved_positions_count() {
        return reserved_positions_count;
    }

    public void setReserved_positions_count(String reserved_positions_count) {
        this.reserved_positions_count = reserved_positions_count;
    }

    @Override
    public String toString() {
        return "ClassPojo [classes = " + classes + ", id = " + id + ", end_time = " + end_time + ", start_time = " + start_time + ", class_reservations = " + class_reservations + ", date = " + date + ", waiting_list = " + waiting_list + ", reserved_positions_count = " + reserved_positions_count + "]";
    }
}


