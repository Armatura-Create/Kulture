package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by slava on 27.11.17.
 */

public interface DayClassInfoStatus<T> {

    void onClassInfoSuccess(T info);

    void onClassInfoFailure(String message);
}
