package app.kulture.kucherenko.init.com.kulture.ui.activity.forgot_password;

import android.widget.Toast;

import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivityForgotPasswordBinding;
import app.kulture.kucherenko.init.com.kulture.interfaces.ForgotPassStatus;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.ChangePassModel;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.ForgotPassModel;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.PassKeyModel;
import app.kulture.kucherenko.init.com.kulture.utils.CheckerInputData;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class ForgotPasswordPresenter implements ForgotPasswordContact.EventListener{

    private static final String RECOVERING_ERROR = "Recovering error";
    private final static String CONNECTION_ERROR = "Connection error";

    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordContact.View view;

    ForgotPasswordPresenter(ForgotPasswordContact.View view, ActivityForgotPasswordBinding binding) {
        this.binding = binding;
        this.view = view;
    }

    @Override
    public void onContinue(int count) {
        if (Connection.isNetworkAvailable(view.getContext())){
        if (!view.getEmail() && count == 0){
            //check email for regex
//            if(!CheckerInputData.isEmail(binding.forgotPasswordLayout.etForgotPasswordEmail.getText().toString().trim())){
//                SimpleAlert.show(view.getContext(), "Error entering email", "wrong email", "Try again");
//            }

//            if(Connection.isNetworkAvailable(view.getContext())){
            ForgotPassModel model = new ForgotPassModel();
            model.setEmail(binding.forgotPasswordLayout.etForgotPasswordEmail.getText().toString().trim());
            Request.getInstance().forgotPass(model, (ForgotPassStatus) view.getContext());
//            } else SimpleAlert.show(view.getContext(), RECOVERING_ERROR, CONNECTION_ERROR, "Try again");
        }

        if(!view.getSecretKey() && count == 1){
            // check key for regex
//            if(!CheckerInputData.isKeyForgotPass(binding.forgotPasswordLayout.etSecretKey.getText().toString().trim())){
//                SimpleAlert.show(view.getContext(), "Error entering secret key", "wrong secret key", "Try again" );
//            }
//            if(Connection.isNetworkAvailable(view.getContext())){
            PassKeyModel model = new PassKeyModel();
            model.setForgotPassKey(binding.forgotPasswordLayout.etSecretKey.getText().toString().trim());
            Request.getInstance().isValidForgotPass(model, (ForgotPassStatus) view.getContext());
//            } else SimpleAlert.show(view.getContext(), RECOVERING_ERROR, CONNECTION_ERROR, "Try again");

        }

        if(view.getSecretKey() && count == 2){
//            if(Connection.isNetworkAvailable(view.getContext())){
            // check pass for regex
            if(!CheckerInputData.isPassword(binding.forgotPasswordLayout.etNewPassword.getText().toString().trim())){
                SimpleAlert.show(view.getContext(),"Error", "Password must be at last 8 characters long", "Try again");
                return;
            }


            if(binding.forgotPasswordLayout.etNewPassword.getText().toString().equals(binding.forgotPasswordLayout.etNewPasswordAgain.getText().toString())) {
                ChangePassModel model = new ChangePassModel();
                model.setForgotPassKey(view.getKey());
                model.setNewPass(binding.forgotPasswordLayout.etNewPasswordAgain.getText().toString());
                Request.getInstance().changePass(model, (ForgotPassStatus) view.getContext());
            } else {
                //Toast.makeText(view.getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                SimpleAlert.show(view.getContext(), "Error", "Passwords do not match", "Try again");
            }
//            }else SimpleAlert.show(view.getContext(), RECOVERING_ERROR, CONNECTION_ERROR, "Try again");

        }
    } else SimpleAlert.showNoConnection(view.getContext());
    }


}
