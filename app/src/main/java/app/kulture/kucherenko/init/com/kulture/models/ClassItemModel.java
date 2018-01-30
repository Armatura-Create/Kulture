package app.kulture.kucherenko.init.com.kulture.models;

/**
 * Created by slava on 23.11.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.kulture.kucherenko.init.com.kulture.models.classes.SimpleClassesModel;

public class ClassItemModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("classes")
    @Expose
    private SimpleClassesModel classes;
    @SerializedName("price")
    @Expose
    private int priceId;

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SimpleClassesModel getClasses() {
        return classes;
    }

    public void setClasses(SimpleClassesModel classes) {
        this.classes = classes;
    }

}
