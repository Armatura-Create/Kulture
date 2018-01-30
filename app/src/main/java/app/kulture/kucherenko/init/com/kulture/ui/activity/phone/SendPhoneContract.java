package app.kulture.kucherenko.init.com.kulture.ui.activity.phone;

import android.content.Context;

import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;

public class SendPhoneContract {

    interface View {
        Context getContext();

        void goToMain();
    }

    interface EventListener extends ILoadingStatus<String> {

        void updateUserInfo(String phone_number);
    }
}
