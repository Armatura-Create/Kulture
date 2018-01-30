package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by slava on 02.11.17.
 */

public interface PaymentLoadingStatus<T> {
    void onBTokenSuccess(T token);

    void onBTokenFailure(String message);

    void onPaymentSuccess(String code);

    void onPaymentFailure(String message);
}
