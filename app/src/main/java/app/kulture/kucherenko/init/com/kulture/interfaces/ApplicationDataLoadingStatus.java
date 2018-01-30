package app.kulture.kucherenko.init.com.kulture.interfaces;

public interface ApplicationDataLoadingStatus {

    void onAppDataSuccess();

    void onAppDataFailure(String message);
}
