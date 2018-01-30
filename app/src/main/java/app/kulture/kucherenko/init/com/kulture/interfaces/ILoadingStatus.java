package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * @author Alex Kucherenko(Godsmack)
 */
public interface ILoadingStatus<T> {

    void onSuccess(T info);

    void onFailure(String message);
}
