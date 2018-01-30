package app.kulture.kucherenko.init.com.kulture.ui.activity.signin;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivitySignInBinding;
import app.kulture.kucherenko.init.com.kulture.ui.activity.forgot_password.ForgotPasswordActivity;
import app.kulture.kucherenko.init.com.kulture.ui.activity.main.MainActivity;
import app.kulture.kucherenko.init.com.kulture.ui.activity.signup.SignUpActivity;
import app.kulture.kucherenko.init.com.kulture.utils.CheckerInputData;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

/**
 * @author Alex Kucherenko(Godsmack)
 */
//TODO All general Activities HAVE TO BE DONE! with MVC/MVP pattern (ex. signup package)
//TODO Все основные классы активити обязательно делать в стиле MVC/MVP паттерна (пример signup package)
public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private ActivitySignInBinding binding;
    private SignInPresenter presenter;

    private static final String ENTERING_ERROR = "Entering error!";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        presenter = new SignInPresenter(this, binding);
        initActions();
        presenter.loginWithFB();

    }

    private void initActions() {

        binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.INVISIBLE);
        binding.signinLayout.pbLogin.setVisibility(View.INVISIBLE);

        binding.signinLayout.textViewSignInNotAMember.setOnClickListener(view -> goToSignUp());
        binding.signinLayout.buttonSignInLogin.setOnClickListener(v -> {

            if (Connection.isNetworkAvailable(getContext())) {
                binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.VISIBLE);
                binding.signinLayout.pbLogin.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(binding.signinLayout.editTextSignInPhone.getText().toString())
                        || TextUtils.isEmpty(binding.signinLayout.editTextSignInPassword.getText().toString())) {
                    SimpleAlert.show(getContext(), "Entering error", "Fill all fields!", "Try again");
                    binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.INVISIBLE);
                    binding.signinLayout.pbLogin.setVisibility(View.INVISIBLE);
                    return;
                }

                String errors = "";
                //check phone for regex
                if (!CheckerInputData.isCheckPhone(binding.signinLayout.editTextSignInPhone.getText().toString().trim())) {
                    errors += "* Wrong phone number!\n\n";
                }
                //check pass for regex
                if (!CheckerInputData.isPassword(binding.signinLayout.editTextSignInPassword.getText().toString().trim())) {
                    errors += "* Password must be at least 8 characters long\n";
                }//if errors isnot empty we see alert
                if (!errors.equals("")) {
                    SimpleAlert.show(getContext(), ENTERING_ERROR, errors, "Try again");
                    binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.INVISIBLE);
                    binding.signinLayout.pbLogin.setVisibility(View.INVISIBLE);
                    return;
                }

                //if all had been gone successfully
//                if (!TextUtils.isEmpty(binding.signinLayout.editTextSignInPhone.getText()) || !TextUtils.isEmpty(binding.signinLayout.editTextSignInPassword.getText().toString()))
                presenter.signIn(binding.signinLayout.editTextSignInPassword.getText().toString().trim(),
                        binding.signinLayout.editTextSignInPhone.getText().toString().trim());
//                else
//                    //Toast.makeText(getContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
//                    SimpleAlert.show(getContext(), "Entering error", "Fill all fields!", "Try again");
            } else SimpleAlert.showNoConnection(getContext());
        });

        binding.signinLayout.textViewSignInForgotPassword.setOnClickListener(v -> forgotPassword());

        //выход с приложения
        binding.toolbar.setNavigationOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Exit")
                    .setMessage("Are you sure?")
                    .setCancelable(false)
                    .setNegativeButton("Cancel",
                            (dialog, id) -> {
                                dialog.cancel();
                            })
                    .setPositiveButton("OK", (dialog, i) -> onBackPressed());
            builder.show();
        });
    }

    @Override
    public void goToMain() {
        binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.INVISIBLE);
        binding.signinLayout.pbLogin.setVisibility(View.INVISIBLE);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        presenter.getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goToSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @Override
    public void forgotPassword() {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(this, SignInActivity.class));
    }
}
