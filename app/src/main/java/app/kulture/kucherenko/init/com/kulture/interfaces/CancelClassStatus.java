package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by alex on 01.12.17.
 */

public interface CancelClassStatus
{
    void onSuccessCanceled(String info);

    void onFailureCanceled(String message);
}
