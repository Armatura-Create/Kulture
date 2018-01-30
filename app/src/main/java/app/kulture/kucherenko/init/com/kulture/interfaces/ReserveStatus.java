package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by dev on 11/15/17.
 */

public interface ReserveStatus<T> {

    void onReserveSuccess(T info);

    void onReserveFailure(String message);
}
