package app.kulture.kucherenko.init.com.kulture.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by 0x008800 on 14.12.17.
 */

public class UploadAvatar {

    @SerializedName("file")
    @Expose
    private File avatar;

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public File getAvatar() {
        return avatar;
    }
}
