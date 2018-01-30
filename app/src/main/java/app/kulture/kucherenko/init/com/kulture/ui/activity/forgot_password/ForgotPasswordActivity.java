package app.kulture.kucherenko.init.com.kulture.ui.activity.forgot_password;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivityForgotPasswordBinding;
import app.kulture.kucherenko.init.com.kulture.interfaces.ForgotPassStatus;
import app.kulture.kucherenko.init.com.kulture.utils.CheckerInputData;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordContact.View, ForgotPassStatus {

    private static final String RECOVERING_ERROR = "Recovering error";
    private final static String CONNECTION_ERROR = "Connection error";

    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordPresenter presenter;

    private boolean email;
    private boolean secretKey;

    private String key;

    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);

        presenter = new ForgotPasswordPresenter(this, binding);

        binding.toolbarForgotPassword.setNavigationOnClickListener(view -> onBackPressed());

        binding.forgotPasswordLayout.etSecretKey.setVisibility(View.INVISIBLE);
        binding.forgotPasswordLayout.etNewPassword.setVisibility(View.INVISIBLE);
        binding.forgotPasswordLayout.etNewPasswordAgain.setVisibility(View.INVISIBLE);

        binding.forgotPasswordLayout.secretKeyImageView.setVisibility(View.INVISIBLE);
        binding.forgotPasswordLayout.checkPassImageView.setVisibility(View.INVISIBLE);
        binding.forgotPasswordLayout.mIvForCheckPassAgainField.setVisibility(View.INVISIBLE);

        binding.forgotPasswordLayout.btContinueForgotPassword.setOnClickListener(view -> presenter.onContinue(count));

        setListenerSuccessEditText();

//        if (!Connection.isNetworkAvailable(getContext()))
//                SimpleAlert.showNoConnection(getContext());

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean getEmail() {
        return email;
    }

    @Override
    public boolean getSecretKey() {
        return secretKey;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void onSendEmailSuccess(String info) {
        email = true;
        //Toast.makeText(this, "Please enter secret key in your email", Toast.LENGTH_SHORT).show();
        SimpleAlert.show(getContext(), "Hint:", "Please enter secret key in your email", "Ok");
        binding.forgotPasswordLayout.etSecretKey.setVisibility(View.VISIBLE);

        binding.forgotPasswordLayout.secretKeyImageView.setVisibility(View.VISIBLE);

        binding.forgotPasswordLayout.etForgotPasswordEmail.setCursorVisible(false);
        binding.forgotPasswordLayout.etForgotPasswordEmail.setFocusable(false);
        binding.forgotPasswordLayout.etForgotPasswordEmail.setLongClickable(false);

        count = 1;
    }

    @Override
    public void onSendEmailFailure(String message) {
        //TODO error massege

        SimpleAlert.show(getContext(), RECOVERING_ERROR, "send email is failed, try again!", "Try");
        //oToast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSecretKeySuccess(String info) {
        secretKey = true;

        key = binding.forgotPasswordLayout.etSecretKey.getText().toString().trim();

        SimpleAlert.show(getContext(), "Success!", "Enter new password and confirm him", "Ok");
        //Toast.makeText(this, "Enter new password", Toast.LENGTH_SHORT).show();
        binding.forgotPasswordLayout.etNewPassword.setVisibility(View.VISIBLE);
        binding.forgotPasswordLayout.etNewPasswordAgain.setVisibility(View.VISIBLE);

        binding.forgotPasswordLayout.checkPassImageView.setVisibility(View.VISIBLE);
        binding.forgotPasswordLayout.mIvForCheckPassAgainField.setVisibility(View.VISIBLE);

        binding.forgotPasswordLayout.etSecretKey.setCursorVisible(false);
        binding.forgotPasswordLayout.etSecretKey.setFocusable(false);
        binding.forgotPasswordLayout.etSecretKey.setLongClickable(false);

        count = 2;
    }

    @Override
    public void onSecretKeyFailure(String message) {
        SimpleAlert.show(getContext(), RECOVERING_ERROR, "wrong secret key, try again!", "Try");
    }

    @Override
    public void onChangePassSuccess(String info) {
        SimpleAlert.show(this, "Succes!", "You changed password", "Next");
        onBackPressed();
    }

    @Override
    public void onChangePassFailure(String message) {
        if (message.equals(CONNECTION_ERROR)) {
            SimpleAlert.show(getContext(), RECOVERING_ERROR, "Please check your connection", "Ok");
        } else
            SimpleAlert.show(getContext(), RECOVERING_ERROR, "Something went wrong", "Try again");
    }

    private void setListenerSuccessEditText() {

        binding.forgotPasswordLayout.etNewPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.forgotPasswordLayout.etNewPasswordAgain.getText().toString()
                        .equals(binding.forgotPasswordLayout.etNewPassword.getText().toString())) {
                    binding.forgotPasswordLayout.mIvForCheckPassAgainField.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.forgotPasswordLayout.mIvForCheckPassAgainField.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

        binding.forgotPasswordLayout.etNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (CheckerInputData.isPassword(binding.forgotPasswordLayout.etNewPassword.getText().toString())) {
                    binding.forgotPasswordLayout.checkPassImageView.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.forgotPasswordLayout.checkPassImageView.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

        binding.forgotPasswordLayout.etForgotPasswordEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (CheckerInputData.isEmail(binding.forgotPasswordLayout.etForgotPasswordEmail.getText().toString())) {
                    binding.forgotPasswordLayout.checkEmeilImageView.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.forgotPasswordLayout.checkEmeilImageView.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

        binding.forgotPasswordLayout.etSecretKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (CheckerInputData.isKeyForgotPass(binding.forgotPasswordLayout.etSecretKey.getText().toString())) {
                    binding.forgotPasswordLayout.secretKeyImageView.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.forgotPasswordLayout.secretKeyImageView.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

    }

}
