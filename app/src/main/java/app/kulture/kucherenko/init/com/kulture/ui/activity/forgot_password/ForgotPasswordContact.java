package app.kulture.kucherenko.init.com.kulture.ui.activity.forgot_password;

import android.content.Context;

/**
 * Created by user on 23.11.17.
 */

public interface ForgotPasswordContact {

    interface View {
        Context getContext();

        boolean getEmail();

        boolean getSecretKey();

        String getKey();

    }

    interface EventListener{
        void onContinue(int count);
    }

}
