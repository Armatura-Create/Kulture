
package app.kulture.kucherenko.init.com.kulture.models.classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.List;

public class AllDayClassModel implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("waiting_list")
    @Expose
    private List<WaitingList> waitingList = null;
    @SerializedName("class_reservations")
    @Expose
    private List<ClassReservation> classReservations = null;
    @SerializedName("classes")
    @Expose
    private Classes classes;
    @SerializedName("reserved_positions_count")
    @Expose
    private int reservedPositionsCount;

    protected AllDayClassModel(Parcel in) {
        id = in.readInt();
        date = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        waitingList = in.createTypedArrayList(WaitingList.CREATOR);
        classReservations = in.createTypedArrayList(ClassReservation.CREATOR);
        classes = in.readParcelable(Classes.class.getClassLoader());
        reservedPositionsCount = in.readInt();
    }

    public static final Creator<AllDayClassModel> CREATOR = new Creator<AllDayClassModel>() {
        @Override
        public AllDayClassModel createFromParcel(Parcel in) {
            return new AllDayClassModel(in);
        }

        @Override
        public AllDayClassModel[] newArray(int size) {
            return new AllDayClassModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<WaitingList> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<WaitingList> waitingList) {
        this.waitingList = waitingList;
    }

    public List<ClassReservation> getClassReservations() {
        return classReservations;
    }

    public void setClassReservations(List<ClassReservation> classReservations) {
        this.classReservations = classReservations;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public static final Comparator<AllDayClassModel> COMPARE_BY_PRICE = (allDayClassModel, t1) ->
            allDayClassModel.getClasses().getPrices().get(0).getPrice() - t1.getClasses().getPrices().get(0).getPrice();

    public static final Comparator<AllDayClassModel> COMPARE_BY_DATE = (allDayClassModel, t1) -> {
        if (allDayClassModel.getDate() == null || t1.getDate() == null)
            return 0;
        return allDayClassModel.getDate().compareTo(t1.getDate());        };

    public int getReservedPositionsCount() {
        return reservedPositionsCount;
    }

    public void setReservedPositionsCount(int reservedPositionsCount) {
        this.reservedPositionsCount = reservedPositionsCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeTypedList(waitingList);
        parcel.writeTypedList(classReservations);
        parcel.writeParcelable(classes, i);
        parcel.writeInt(reservedPositionsCount);
    }
}
