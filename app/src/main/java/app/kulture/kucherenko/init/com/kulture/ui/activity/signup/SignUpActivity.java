package app.kulture.kucherenko.init.com.kulture.ui.activity.signup;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivitySignUpBinding;
import app.kulture.kucherenko.init.com.kulture.ui.activity.main.MainActivity;
import app.kulture.kucherenko.init.com.kulture.utils.CheckerInputData;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

/**
 * @author Alex Kucherenko(Godsmack)
 */
public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    //TODO: It might be useful to use DataBinding
    //TODO: Будет хорошо, если вы будете использовать DataBinding
    private ActivitySignUpBinding binding;
    private SignUpPresenter presenter;

    private static final String REGISTRATION_ERROR = "Registration error";
    public static final String TRY_AGAIN = "Try again";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        presenter = new SignUpPresenter(this);
        setSupportActionBar(binding.toolbar);
        initActions();
    }

    private void initActions() {
        setListenerSuccessEditText();

        binding.content.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Connection.isNetworkAvailable(getContext())) {

                    String dialogText = "";

                    if (TextUtils.isEmpty(binding.content.editTextSignUpName.getText())) {
                        dialogText += "* Name is required!\n";
                    }
                    if (binding.content.editTextSignUpPhone.getText().toString().trim().equals("+65")) {
                        dialogText += "* Phone number is required!\n";
                    }
                    if (TextUtils.isEmpty(binding.content.editTextSignUpEmail.getText())) {
                        dialogText += "* Email is required!\n";

                    }
                    if (TextUtils.isEmpty(binding.content.editTextSignUpPassword.getText())) {
                        dialogText += "* Password is required!\n";
                    }
                    if (TextUtils.isEmpty(binding.content.editTextSignUpPasswordAgain.getText())) {
                        dialogText += "* Password again field is required!\n";
                    }

                    if (!dialogText.equals("")) {
                        SimpleAlert.show(getContext(), REGISTRATION_ERROR, dialogText, TRY_AGAIN);
                        return;
                    }

                    if (!CheckerInputData.isName(binding.content.editTextSignUpName.getText().toString().trim())) {
                        dialogText += "* Incorrect name, please use letters and numbers for enter name\n";
                    }

                    if (!CheckerInputData.isEmail(binding.content.editTextSignUpEmail.getText().toString().trim())) {
                        dialogText += "* Incorrect email, please use example@example.com format\n";
                    }

                    if (!CheckerInputData.isCheckPhone(binding.content.editTextSignUpPhone.getText().toString().trim())) {
                        dialogText += "* Incorrect phone number, please enter your real phone number\n";
                    }

                    if (!CheckerInputData.isName(binding.content.editTextSignUpPassword.getText().toString().trim())) {
                        dialogText += "* Password must be at least 8 characters long!\n";
                    }
                    if (!TextUtils.isEmpty(binding.content.editTextSignUpPassword.getText()) && !binding.content.editTextSignUpPassword.getText().toString().equals(
                            binding.content.editTextSignUpPasswordAgain.getText().toString())) {
                        dialogText += "* Passwords don't match!";
                    }

                    if (!dialogText.equals("")) {
                        SimpleAlert.show(getContext(), REGISTRATION_ERROR, dialogText, TRY_AGAIN);
                        return;
                    }

                    presenter.register(binding.content.editTextSignUpName.getText().toString().trim(),
                            binding.content.editTextSignUpEmail.getText().toString().trim(),
                            binding.content.editTextSignUpPassword.getText().toString().trim(),
                            binding.content.editTextSignUpPhone.getText().toString().trim());

                } else SimpleAlert.showNoConnection(getContext());
            }
        });

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setListenerSuccessEditText() {

        binding.content.editTextSignUpPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.content.editTextSignUpPassword.getText().toString()
                        .equals(binding.content.editTextSignUpPasswordAgain.getText().toString())) {
                    binding.content.imageViewSignUpPasswordAgain.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.content.imageViewSignUpPasswordAgain.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

        binding.content.editTextSignUpPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (CheckerInputData.isPassword(binding.content.editTextSignUpPassword.getText().toString().trim())) {
                    binding.content.imageViewSignUpPassword.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.content.imageViewSignUpPassword.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

        binding.content.editTextSignUpPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (CheckerInputData.isCheckPhone(binding.content.editTextSignUpPhone.getText().toString().trim())) {
                    binding.content.imageViewSignUpPhone.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.content.imageViewSignUpPhone.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

        binding.content.editTextSignUpEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (CheckerInputData.isEmail(binding.content.editTextSignUpEmail.getText().toString().trim())) {
                    binding.content.imageViewSignUpEmail.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.content.imageViewSignUpEmail.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });

        binding.content.editTextSignUpName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (CheckerInputData.isName(binding.content.editTextSignUpName.getText().toString().trim())) {
                    binding.content.imageViewSignUpName.setBackground(getResources().getDrawable(R.drawable.success));
                } else {
                    binding.content.imageViewSignUpName.setBackground(getResources().getDrawable(R.drawable.failure));
                }
            }
        });
    }

    @Override
    public void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void goToSignIn() {
        onBackPressed();
    }

    @Override
    public Context getContext() {
        return this;
    }
}