package app.kulture.kucherenko.init.com.kulture.interfaces;

import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;

/**
 * Created by slava on 13.11.17.
 */

public interface UserInfoLoadingStatus{

    void onUserInfoSuccess(UserInfoModel user);

    void onUserInfoFailure(String message);
}
