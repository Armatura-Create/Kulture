package app.kulture.kucherenko.init.com.kulture.interfaces;

public interface TeachersLoadingStatus {

    void onTeachersSuccess(String message);

    void onTeachersFailure(String message);
}
