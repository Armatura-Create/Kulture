package app.kulture.kucherenko.init.com.kulture.ui.activity.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivitySignInBinding;
import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.SignInModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.activity.main.MainActivity;
import app.kulture.kucherenko.init.com.kulture.ui.activity.phone.SendPhoneActivity;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import app.kulture.kucherenko.init.com.kulture.utils.StaticValues;

class SignInPresenter implements SignInContract.EventListener, UserInfoLoadingStatus, ILoadingStatus<String> {
    private SignInContract.View view;
    private CallbackManager mCallbackManager;
    private UserInfoModel mUser;
    private ActivitySignInBinding binding;
    private boolean isLoginWithEmail = false;

    SignInPresenter(SignInContract.View view, ActivitySignInBinding binding) {
        this.view = view;
        this.binding = binding;
        mCallbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onSuccess(String info) {
        getUserInfo();
    }

    @Override
    public void onFailure(String message) {
        binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.INVISIBLE);
        binding.signinLayout.pbLogin.setVisibility(View.INVISIBLE);
        SimpleAlert.show(view.getContext(),
                "Entering error",
                "Unable to log in with provided credentials",
                StaticValues.TRY_AGAIN);

        //Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        //

//        if (message.equals("{\n" +
//                "  \"non_field_errors\": [\n" +
//                "    \"Unable to log in with provided credentials.\"\n" +
//                "  ]\n" +
//                "}"))
//            SimpleAlert.show(view.getContext(),
//                    "Entering error",
//                    "Unable to log in with provided credentials",
//                    StaticValues.TRY_AGAIN);

    }

    @Override
    public void signIn(String password, String phone_number) {
        if (Connection.isNetworkAvailable(view.getContext())) {
            SignInModel model = new SignInModel();
            model.setPassword(password);
            model.setPhoneNumber(phone_number);
            Request.getInstance().signIn(model, this);
            isLoginWithEmail = true;
        } else onFailure("Not network");

    }

    public void loginWithFB() {
        isLoginWithEmail = false;
        Log.e("Facebook", "It was started");
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("Facebook", "All is ok. Token: " + loginResult.getAccessToken().getToken());
//                Toast.makeText(view.getContext(), "All is ok. Token: " + loginResult.getAccessToken().getToken(), Toast.LENGTH_SHORT).show();
                loginUser(loginResult.getAccessToken().getToken());

                //Сохраняем токен при регистрации у себя на телефоне
                MSharedPreferences.getInstance().setKey(loginResult.getAccessToken().getToken());
                //TODO start home_activity if phone exist and enter_phone_activity if not
            }

            @Override
            public void onCancel() {
                Log.e("Facebook", "Canceled");
                Toast.makeText(view.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("Facebook", "Error: " + error.getMessage());
                Toast.makeText(view.getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginUser(String fbToken) {
        Log.e("user_info", "Send FB token");
        Request.getInstance().loginWithFbToken(fbToken, SignInPresenter.this);
    }

    @Override
    public void getUserInfo() {
        if (Connection.isNetworkAvailable(view.getContext())) {
            Log.e("user_info", "Get user info");
            Request.getInstance().getUserInfo(SignInPresenter.this);
        } else SimpleAlert.showNoConnection(view.getContext());
    }

    @Override
    public void isPhoneExist() {
        String phone = mUser.getPhoneNumber();
        if (phone == null || phone.length() == 0) {
            Intent intent = new Intent(view.getContext(), SendPhoneActivity.class);
            view.getContext().startActivity(intent);
        } else
            view.getContext().startActivity(new Intent(view.getContext(), MainActivity.class));
    }

    public void goToMain() {
        view.goToMain();
    }

    @Override
    public void onUserInfoSuccess(UserInfoModel user) {
        mUser = user;
        if (isLoginWithEmail) {
            if (user.isIsVerifiedEmail()) goToMain();
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Verify your Email")
                        .setMessage("Do you want to resend a letter to your Email?")
                        .setCancelable(false)
                        .setNegativeButton("NO",
                                (dialog, id) -> {
                                    MSharedPreferences.getInstance().setKey(null);
                                    isLoginWithEmail = false;
                                    binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.INVISIBLE);
                                    binding.signinLayout.pbLogin.setVisibility(View.INVISIBLE);
                                    dialog.cancel();
                                })
                        .setPositiveButton("YES", (dialog, i) -> {
                            Request.getInstance().sendEmailVerify(MSharedPreferences.getInstance().getKey());
                            MSharedPreferences.getInstance().setKey(null);
                            isLoginWithEmail = false;
                            binding.signinLayout.ivBackgroundPbLogin.setVisibility(View.INVISIBLE);
                            binding.signinLayout.pbLogin.setVisibility(View.INVISIBLE);
                        });
                builder.show();
            }
        } else {
            isPhoneExist();
        }
    }

    @Override
    public void onUserInfoFailure(String message) {

    }

    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }
}
