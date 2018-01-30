package app.kulture.kucherenko.init.com.kulture.ui.activity.phone;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import com.facebook.login.LoginManager;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivitySendPhoneNumberBinding;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.activity.main.MainActivity;
import app.kulture.kucherenko.init.com.kulture.utils.CheckerInputData;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import app.kulture.kucherenko.init.com.kulture.utils.StaticValues;

public class SendPhoneActivity extends AppCompatActivity implements SendPhoneContract.View {

    private SendPhonePresenter mPhonePresenter;
    private ActivitySendPhoneNumberBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_phone_number);


        mPhonePresenter = new SendPhonePresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_send_phone_number);

        setTextChangeListeners();
        binding.toolbar.setNavigationOnClickListener(v -> {
            MSharedPreferences.getInstance().setKey(null);
            MSharedPreferences.getInstance().setBraintreeToken(null);
            LoginManager.getInstance().logOut();
            Request.getInstance().logOut();
            onBackPressed();
        });
        binding.sendPhoneNumber.btSendPhone.setOnClickListener(v -> {

            if (!CheckerInputData.isCheckPhone(binding.sendPhoneNumber.etSendPhone.getText().toString())) {
                SimpleAlert.show(getContext(), "Error", "Wrong phone number!", StaticValues.TRY_AGAIN);
                return;
            }
            mPhonePresenter.updateUserInfo(
                    binding.sendPhoneNumber.etSendPhone.getText().toString().trim());
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goToMain() {
        startActivity(new Intent(getContext(), MainActivity.class));
        finish();
    }

    private void setTextChangeListeners() {
        binding.sendPhoneNumber.etSendPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (CheckerInputData.isCheckPhone(binding.sendPhoneNumber.etSendPhone.getText().toString())) {
                    binding.sendPhoneNumber.ivIsSuccess.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.sendPhoneNumber.ivIsSuccess.setBackground(getResources().getDrawable(R.drawable.failure));
                }

            }
        });
    }

    @Override
    protected void onStop() {
        MSharedPreferences.getInstance().setKey(null);
        MSharedPreferences.getInstance().setBraintreeToken(null);
        LoginManager.getInstance().logOut();
        Request.getInstance().logOut();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        MSharedPreferences.getInstance().setKey(null);
        MSharedPreferences.getInstance().setBraintreeToken(null);
        LoginManager.getInstance().logOut();
        Request.getInstance().logOut();
        super.onDestroy();
    }
}
