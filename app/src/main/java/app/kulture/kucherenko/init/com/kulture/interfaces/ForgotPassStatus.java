package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by slava on 21.11.17.
 */

public interface ForgotPassStatus {
    void onSendEmailSuccess(String info);

    void onSendEmailFailure(String message);

    void onSecretKeySuccess(String info);

    void onSecretKeyFailure(String message);

    void onChangePassSuccess(String info);

    void onChangePassFailure(String message);
}
