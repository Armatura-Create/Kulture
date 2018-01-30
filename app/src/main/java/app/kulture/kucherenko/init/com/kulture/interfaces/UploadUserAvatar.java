package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by 0x008800 on 09.12.17.
 */

public interface UploadUserAvatar<T>{

    void onSuccessUploadUserAvatar(T info);

    void onFailureUploadUserAvatar(String message);
}
