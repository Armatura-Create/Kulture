package app.kulture.kucherenko.init.com.kulture.models.payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alex on 09.11.17.
 */

public class PaymentsPrice {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("price")
    @Expose
    private Integer price;

    @SerializedName("classes_prices")
    @Expose
    private List<ClassesPrices> classesPrices = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<ClassesPrices> getClassesPrices() {
        return classesPrices;
    }

    public void setClassesPrices(List<ClassesPrices> classesPrices) {
        this.classesPrices = classesPrices;
    }
}
