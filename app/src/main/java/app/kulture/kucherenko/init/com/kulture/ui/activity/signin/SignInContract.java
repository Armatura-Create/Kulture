package app.kulture.kucherenko.init.com.kulture.ui.activity.signin;

import android.content.Context;

import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;

/**
 * Created by st18rai on 08.10.17.
 */

interface SignInContract {

    interface View {
        Context getContext();

        void goToSignUp();

        void goToMain();

        void forgotPassword();

        void goToLogin();
    }

    interface EventListener extends UserInfoLoadingStatus {

        void getUserInfo();

        void isPhoneExist();

        void signIn(String password, String phone_number);
    }

}
