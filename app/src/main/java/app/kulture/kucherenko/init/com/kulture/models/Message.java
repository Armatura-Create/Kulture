package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by slava on 12.10.17.
 */

public class Message {

    @SerializedName("message")
    @Expose
    private String message;

}