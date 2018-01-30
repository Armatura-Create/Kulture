package app.kulture.kucherenko.init.com.kulture.ui.activity.signup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.models.RegisterModel;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

/**
 * @author Alex Kucherenko(Godsmack)
 */
class SignUpPresenter implements SignUpContract.EventListener {


    private AlertDialog mAdSuccesRegistration;

    //mutable strings
    private static String responceMessageToAlertDialog = "";
    private static String responceMessageToAlertDialogHeader = "Registration error";

    //imutable strings
    private final static String SUCCESS_REGISTRATION_TITLE = "Success!!!";
    private final static String SUCCESS_REGISTRATION_MESSAGE = "Your registration has been successful, please check your email to activate your account.";

    private final static String NICK_NAME_IN_USE_MESSAGE = "Unfortunally your nickname in use, try register another nickname!";
    private final static String EMAIL_IN_USE_MESSAGE = "Your email in use, maybe you forgot password?";
    private final static String PHONE_NUMBER_IN_USE_MESSAGE = "Your phone number in use, maybe you forgot password?";


    private final static String NAME_ALREADY_IN_USE_RESPONCE = "{\"message\":\"Username already in use\"}";
    private final static String EMAIL_ALREADY_IN_USE_RESPONCE = "{\"message\":\"Email already in use\"}";
    private final static String PHONE_ALREADY_IN_USE_RESPONCE = "{\"message\":\"Phone number already in use\"}";


    private final static String CONNECTION_ERROR = "Connection error";
    private static final String CHECK_YOUR_CONNECTION = "please check your internet connection";

    private SignUpContract.View view;

    SignUpPresenter(SignUpContract.View view) {
        this.view = view;
    }

    @Override
    public void onSuccess(String info) {

       // Toast.makeText(view.getContext(), info +"Success", Toast.LENGTH_SHORT).show();
        startAlertDialog(SUCCESS_REGISTRATION_TITLE, SUCCESS_REGISTRATION_MESSAGE);
        //SimpleAlert.show(view.getContext(), SUCCESS_REGISTRATION_TITLE, SUCCESS_REGISTRATION_MESSAGE, "Ok");

      //  nextStep("goToSignIn");
//       startAlertDialog(responceMessageToAlertDialogHeader, responceMessageToAlertDialog);
    }


    private void nextStep(String step){
        switch (step) {

            case "goToMain":
                goToMain();
                break;

            case "goToSignIn":
                goToSignIn();
                break;
        }
    }

    @Override
    public void onFailure(String message) {
        //Toast.makeText(view.getContext(), message + "Failure", Toast.LENGTH_SHORT).show();

        switch (message){

            case CONNECTION_ERROR:
                responceMessageToAlertDialog = CHECK_YOUR_CONNECTION;
                break;

            case NAME_ALREADY_IN_USE_RESPONCE:
                responceMessageToAlertDialog = NICK_NAME_IN_USE_MESSAGE;
                break;

            case EMAIL_ALREADY_IN_USE_RESPONCE:
                responceMessageToAlertDialog = EMAIL_IN_USE_MESSAGE;
                break;

            case PHONE_ALREADY_IN_USE_RESPONCE:
                responceMessageToAlertDialog = PHONE_NUMBER_IN_USE_MESSAGE;
                break;

//                default:
//                    responceMessageToAlertDialog = "here must be \"name already in use\"";
//                    break;

//            default: //success of registration
//                responceMessageToAlertDialogHeader = SUCCESS_REGISTRATION_TITLE;
//                responceMessageToAlertDialog = SUCCESS_REGISTRATION_MESSAGE;
//                break;
        }

        startAlertDialog(responceMessageToAlertDialogHeader, responceMessageToAlertDialog);
    }

    @Override
    public void register(String name, String email, String password, String phone) {
        if(Connection.isNetworkAvailable(view.getContext())){
        RegisterModel model = new RegisterModel();
        model.setName(name);
        model.setEmail(email);
        model.setPhone(phone);
        model.setPassword(password);

        //save in share pref
        SharedPreferences sharedPref = MApplication.getInstance().getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("phone_number", phone);
        editor.apply();

        Request.getInstance().registerUser(model, this);
        } else onFailure(CONNECTION_ERROR);
    }

    @Override
    public void goToMain() {
        view.goToMain();
    }

    @Override
    public void goToSignIn() {
        view.goToSignIn();
    }

    private void startAlertDialog(String title, String message){
        mAdSuccesRegistration = new AlertDialog.Builder(view.getContext()).create();
        mAdSuccesRegistration.setTitle(title);
        mAdSuccesRegistration.setMessage(message);
        mAdSuccesRegistration.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        responceMessageToAlertDialog = "";
                        dialog.dismiss();
                        if(title.equals(SUCCESS_REGISTRATION_TITLE)){
                            nextStep("goToSignIn");
                        }
                    }
                });
        mAdSuccesRegistration.show();
    }
}