package app.kulture.kucherenko.init.com.kulture.ui.activity.phone;

import android.widget.Toast;

import com.google.gson.Gson;

import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.models.UpdateUserInfoModel;
import app.kulture.kucherenko.init.com.kulture.models.user.PhoneModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class SendPhonePresenter implements SendPhoneContract.EventListener {

    private SendPhoneContract.View mView;

    public SendPhonePresenter(SendPhoneContract.View view) {
        mView = view;
    }

    @Override
    public void onSuccess(String info) {
//        Toast.makeText(mView.getContext(), info, Toast.LENGTH_SHORT).show();
        mView.goToMain();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUserInfo(String phone_number) {
        if (Connection.isNetworkAvailable(mView.getContext())) {
            PhoneModel model = new PhoneModel();
            model.setPhoneNumber(phone_number);
            Request.getInstance().updatePhoneInfo(model, this);
        } else SimpleAlert.showNoConnection(mView.getContext());
    }
}
