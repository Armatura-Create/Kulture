package app.kulture.kucherenko.init.com.kulture.ui.activity.signup;

import android.content.Context;

import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;

/**
 * @author Alex Kucherenko(Godsmack)
 */
public class SignUpContract {

    interface View {

        Context getContext();

        void goToMain();

        void goToSignIn();
    }

    interface EventListener extends ILoadingStatus<String> {

        void register(String name, String email, String password, String phone);

        void goToMain();

        void goToSignIn();
    }
}
