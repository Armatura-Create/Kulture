package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by slava on 02.11.17.
 */

public interface PaymentClassWithMemberStatus<T> {

    void onSuccessWithMembership(String code);

    void onFailureWithMembership(String message);
}
