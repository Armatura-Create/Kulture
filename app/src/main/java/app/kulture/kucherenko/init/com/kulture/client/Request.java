package app.kulture.kucherenko.init.com.kulture.client;

import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import app.kulture.kucherenko.init.com.kulture.interfaces.AllClasses;
import app.kulture.kucherenko.init.com.kulture.interfaces.ApplicationDataLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.CancelClassStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.DayClassInfoStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.ForgotPassStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.GetBannerCode;
import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.LeaveWaitingListStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.TeachersLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.JoinWaitingListStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.MemberPriceLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.PaymentClassWithMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.PaymentLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.PaymentsHistory;
import app.kulture.kucherenko.init.com.kulture.interfaces.ReserveStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToUnlimitedMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UploadUserAvatar;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.AllStudios;
import app.kulture.kucherenko.init.com.kulture.models.ApplicationData;
import app.kulture.kucherenko.init.com.kulture.models.AuthenotificationKey;
import app.kulture.kucherenko.init.com.kulture.models.BraintreeTokenModel;
import app.kulture.kucherenko.init.com.kulture.models.CancelReserveModel;
import app.kulture.kucherenko.init.com.kulture.models.ClassItemModel;
import app.kulture.kucherenko.init.com.kulture.models.DayClassIdModel;
import app.kulture.kucherenko.init.com.kulture.models.FacebookTokenModel;
import app.kulture.kucherenko.init.com.kulture.models.MemberPriceModel;
import app.kulture.kucherenko.init.com.kulture.models.PaymentModel;
import app.kulture.kucherenko.init.com.kulture.models.RegisterModel;
import app.kulture.kucherenko.init.com.kulture.models.SendEmailVerifyModel;
import app.kulture.kucherenko.init.com.kulture.models.SignInModel;
import app.kulture.kucherenko.init.com.kulture.models.TokenAtRegistration;
import app.kulture.kucherenko.init.com.kulture.models.TokenModel;
import app.kulture.kucherenko.init.com.kulture.models.UpToMemberModel;
import app.kulture.kucherenko.init.com.kulture.models.UpToUnlimitedMemberModel;
import app.kulture.kucherenko.init.com.kulture.models.UpdateUserInfoModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.Teacher;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.ChangePassModel;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.ForgotPassModel;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.PassKeyModel;
import app.kulture.kucherenko.init.com.kulture.models.payments.PaymentsHistoryModel;
import app.kulture.kucherenko.init.com.kulture.models.user.PhoneModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserReservation;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton class for passing your requests
 * TODO: Extend it if necessary and divide on subclasses
 * TODO: Наследуйте класс если посчитаете нужным и разбейте на группы наследников,
 * TODO: и в дальнейшем можно будет исп. pattern FabricMethod
 */
public class Request {

    private final static String CONNECTION_ERROR = "Connection error";

    private static Request request;

    public static Request getInstance() {
        if (request == null) request = new Request();
        return request;
    }

    public void updateUserInfo(UpdateUserInfoModel user, final ILoadingStatus<String> loader) {
        Call<UserInfoModel> call;
        String token = MSharedPreferences.getInstance().getKey();
        call = RetrofitClient.getAPI().updateUser(token, user);
        call.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                if (response.isSuccessful()) {
                    loader.onSuccess("Body: " + response.body().toString());
                } else {
                    loader.onFailure("Code: " + response.code()
                            + "\nMessage" + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {
                loader.onFailure(CONNECTION_ERROR);
            }
        });
    }

    public void updatePhoneInfo(PhoneModel user, final ILoadingStatus<String> loader) {
        Call<UserInfoModel> call;
        String token = MSharedPreferences.getInstance().getKey();
        call = RetrofitClient.getAPI().updatePhone(token, user);
        call.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                if (response.isSuccessful()) {
                    loader.onSuccess("Body: " + response.body().toString());
                } else {
                    loader.onFailure("Code: " + response.code()
                            + "\nMessage" + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {
                loader.onFailure(CONNECTION_ERROR);
            }
        });
    }

    public void getUserInfo(final UserInfoLoadingStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<UserInfoModel> call = RetrofitClient.getAPI().getUserInfo(token);
        call.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                if (response.isSuccessful()) {
                    UserInfoModel userInfoModel = response.body();

                    MSharedPreferences.getInstance().setUserInfo(new Gson().toJson(userInfoModel));
                    Log.e("SharePref", MSharedPreferences.getInstance().getUserInfo());

                    loader.onUserInfoSuccess(response.body());
                } else {
                }
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {
                loader.onUserInfoFailure(CONNECTION_ERROR);
            }
        });
    }

    public void getStudioTeachers(final TeachersLoadingStatus loader, String id) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<List<Teacher>> call = RetrofitClient.getAPI().getStudioInstructors(token, id);
        call.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if (response.isSuccessful()) {

                    MApplication.getInstance().setTeachers(response.body());
                    Log.e("Teachers", new Gson().toJson(response.body()));

                    loader.onTeachersSuccess(" ");
                } else {
                    Log.e("Teachers", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                loader.onTeachersFailure(CONNECTION_ERROR);
            }
        });
    }

    public void loginWithFbToken(String token, final ILoadingStatus<String> loader) {
        FacebookTokenModel model = new FacebookTokenModel();
        model.setFacebookToken(token);
        Call<TokenModel> call;
        call = RetrofitClient.getAPI().facebookSignUP(model);
        call.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                if (response.isSuccessful()) {
                    MSharedPreferences.getInstance().setKey(response.body().getToken());
                    // TODO save token to SharedPreference

                    loader.onSuccess("getUserInfo");
                } else {
                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {

                loader.onFailure(CONNECTION_ERROR);
            }
        });
    }

    public void registerUser(RegisterModel model, final ILoadingStatus<String> loader) {
        Call<TokenAtRegistration> call;
        call = RetrofitClient.getAPI().registerUser(model);

        call.enqueue(new Callback<TokenAtRegistration>() {
            @Override
            public void onResponse(Call<TokenAtRegistration> call, Response<TokenAtRegistration> response) {

                if (response.isSuccessful()) {
                    loader.onSuccess(response.message());

                } else {
                    //if responce is failed we send message with error body to alertDialog
                    String errMessage = "";
                    try {
                        errMessage = response.errorBody().string();
                        loader.onFailure(errMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    Log.e("onResponse: ", response.headers().toString());
//                    Log.e("onResponse: ", response.message());
//                    Log.e("onResponse: ", String.valueOf(response.code()));
//                    Log.e("onResponse: ", errMessage);
                }
            }

            @Override
            public void onFailure(Call<TokenAtRegistration> call, Throwable t) {
                loader.onFailure(CONNECTION_ERROR);// don't change this string
            }
        });
    }

    public void signIn(SignInModel data, final ILoadingStatus<String> loader) {
        Call<AuthenotificationKey> call = RetrofitClient.getAPI().login(data);
        call.enqueue(new Callback<AuthenotificationKey>() {
            @Override
            public void onResponse(Call<AuthenotificationKey> call, Response<AuthenotificationKey> response) {
                if (response.isSuccessful()) {
                    MSharedPreferences.getInstance().setKey(response.body().getKey());
                    loader.onSuccess("getUserInfo");
                } else {
                    try {
                        loader.onFailure(response.errorBody().string());
//                        Log.e("Credentials", response.body().toString());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AuthenotificationKey> call, Throwable t) {
                loader.onFailure(CONNECTION_ERROR);
            }
        });
    }

    public void getMemberPrice(final MemberPriceLoadingStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<MemberPriceModel> call = RetrofitClient.getAPI().getMemberPrice(token);
        call.enqueue(new Callback<MemberPriceModel>() {
            @Override
            public void onResponse(Call<MemberPriceModel> call, Response<MemberPriceModel> response) {
                if (response.isSuccessful()) {
                    loader.onMemberPriceSuccess(response.body().getValue());
                } else {

                }
            }

            @Override
            public void onFailure(Call<MemberPriceModel> call, Throwable t) {
                loader.onMemberPriceFailure(CONNECTION_ERROR);
            }
        });
    }

    public void getApplicationData(final ApplicationDataLoadingStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<ApplicationData> call = RetrofitClient.getAPI().getApplicationData(token);
        call.enqueue(new Callback<ApplicationData>() {
            @Override
            public void onResponse(Call<ApplicationData> call, Response<ApplicationData> response) {
                if (response.isSuccessful()) {
                    MApplication.getInstance().setApplicationData(response.body());
                    MSharedPreferences.getInstance().setAppData(new Gson().toJson(response.body()));
                    loader.onAppDataSuccess();
                    Log.e("AppData: ", new Gson().toJson(response.body()));
                } else {

                }
            }

            @Override
            public void onFailure(Call<ApplicationData> call, Throwable t) {
                loader.onAppDataFailure(CONNECTION_ERROR);
            }
        });
    }

    public void getBraintreeToken(String token, final PaymentLoadingStatus<String> loader) {
        Call<BraintreeTokenModel> call = RetrofitClient.getAPI().getBraintreeToken(token);
        call.enqueue(new Callback<BraintreeTokenModel>() {
            @Override
            public void onResponse(Call<BraintreeTokenModel> call, Response<BraintreeTokenModel> response) {
                if (response.isSuccessful()) {
                    MSharedPreferences.getInstance().setBraintreeToken(response.body().getBraintreeToken());
                    loader.onBTokenSuccess(response.body().getBraintreeToken());
                } else {

                }
            }

            @Override
            public void onFailure(Call<BraintreeTokenModel> call, Throwable t) {
                loader.onBTokenFailure(CONNECTION_ERROR);
            }
        });
    }

    public void createTransaction(PaymentModel model, final PaymentLoadingStatus<String> loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().paymentBraintree(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loader.onPaymentSuccess(String.valueOf(response.code()));
                    Log.e("onResponsePay: ", response.body().toString());
                    Log.e("onResponsePay: ", String.valueOf(response.code()));
                } else {
                    Log.e("onResponsePay: ", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.onBTokenFailure(CONNECTION_ERROR);
            }
        });
    }

    public void createTransactionWithMembership(PaymentModel model, final PaymentClassWithMemberStatus<String> loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().createTransactionWithMembership(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loader.onSuccessWithMembership(response.body().toString());
                    Log.e("onResponsePayMem: ", response.body().toString());
                    Log.e("onResponsePayMem: ", String.valueOf(response.code()));
                } else {
                    Log.e("onResponsePayMem: ", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.onFailureWithMembership(CONNECTION_ERROR);
            }
        });
    }

    public void registerDevice(TokenModel model) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().registerDevice(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                } else {
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            }
        });
    }

    public void getPaymentsHistory(final PaymentsHistory loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<List<PaymentsHistoryModel>> call = RetrofitClient.getAPI().getPaymentsHistory(token);
        call.enqueue(new Callback<List<PaymentsHistoryModel>>() {
            @Override
            public void onResponse(Call<List<PaymentsHistoryModel>> call, Response<List<PaymentsHistoryModel>> response) {
                if (response.isSuccessful()) {

                    loader.getData(response.body());

                } else {
                }
            }

            @Override
            public void onFailure(Call<List<PaymentsHistoryModel>> call, Throwable t) {

            }
        });
    }

    public void allDayClasses(final AllClasses loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<List<AllDayClassModel>> call = RetrofitClient.getAPI().getAllDayClasses(token);
        call.enqueue(new Callback<List<AllDayClassModel>>() {
            @Override
            public void onResponse(Call<List<AllDayClassModel>> call, Response<List<AllDayClassModel>> response) {
                if (response.isSuccessful()) {
                    loader.getData(response.body());
                    MApplication.getInstance().setAllClasses(response.body());
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<AllDayClassModel>> call, Throwable t) {
            }
        });
    }

    public void allFutureDayClasses(final AllClasses loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<List<AllDayClassModel>> call = RetrofitClient.getAPI().getAllFutureDayClasses(token);
        call.enqueue(new Callback<List<AllDayClassModel>>() {
            @Override
            public void onResponse(Call<List<AllDayClassModel>> call, Response<List<AllDayClassModel>> response) {
                if (response.isSuccessful()) {
                    loader.getData(response.body());
                    MApplication.getInstance().setFutureClasses(response.body());
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<AllDayClassModel>> call, Throwable t) {
            }
        });
    }

    public void logOut() {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().logOut(token);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                } else {
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
            }
        });
    }

    public void reserveClass(Map<String, String> model, final ReserveStatus<String> loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().reserveClass(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    // TODO do something
                    loader.onReserveSuccess(response.body().toString());
                } else {
                    try {
                        loader.onReserveFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.onReserveFailure(CONNECTION_ERROR);
            }
        });
    }

    public void getAllItems(final ILoadingStatus<List<ClassItemModel>> loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<List<ClassItemModel>> call = RetrofitClient.getAPI().getAllItems(token);
        call.enqueue(new Callback<List<ClassItemModel>>() {
            @Override
            public void onResponse(Call<List<ClassItemModel>> call, Response<List<ClassItemModel>> response) {
                if (response.isSuccessful()) {
                    loader.onSuccess(response.body());
//                    Log.e("onResponseItem: ", new Gson().toJson(response.body()));
                } else {
                    try {
                        loader.onFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ClassItemModel>> call, Throwable t) {
                loader.onFailure(CONNECTION_ERROR);
            }
        });
    }

    public void cancelReserve(CancelReserveModel model, final CancelClassStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().cancelReserve(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    // TODO delete class from recyclerView in Reserve List
                    loader.onSuccessCanceled("canceled");
                } else {
                    loader.onFailureCanceled("Not canceled");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.onFailureCanceled(CONNECTION_ERROR);
            }
        });
    }

    public void upToUnlimitedMember(UpToUnlimitedMemberModel nonce, final UpToUnlimitedMemberStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().updateToUnlimitedMember(token, nonce);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loader.onUpdateUnlimitedMemberSuccess(" ");
                } else {
                    try {
                        loader.onUpdateUnlimitedMemberFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.onUpdateUnlimitedMemberFailure(CONNECTION_ERROR);
                Log.e("upToMember", "onFailure: " + t.getMessage());
            }
        });
    }

    public void upToMember(UpToMemberModel nonce, final UpToMemberStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().updateToMember(token, nonce);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loader.onUpdateMemberSuccess("");
                } else {
                    try {
                        loader.onUpdateMemberFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.onUpdateMemberFailure(CONNECTION_ERROR);
                Log.e("upToMember", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getAllDayClass(int id, final DayClassInfoStatus<AllDayClassModel> loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<AllDayClassModel> call = RetrofitClient.getAPI().getAllClassInfo(token, String.valueOf(id));
        call.enqueue(new Callback<AllDayClassModel>() {
            @Override
            public void onResponse(Call<AllDayClassModel> call, Response<AllDayClassModel> response) {
                if (response.isSuccessful()) {
                    loader.onClassInfoSuccess(response.body());
                } else {
                    if (response.code() == 404)
                        loader.onClassInfoFailure("day class not found");
                }
            }

            @Override
            public void onFailure(Call<AllDayClassModel> call, Throwable t) {
                loader.onClassInfoFailure(CONNECTION_ERROR);
                loader.onClassInfoFailure(t.getMessage());
            }
        });
    }

    public void joinWaitingClass(DayClassIdModel model, final JoinWaitingListStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().joinWaitingClass(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    // TODO add class to recyclerView in Waiting List
//                    Log.e("joinWaitingClass", response.body().toString());
                    loader.joinWaitingListSuccess();
                } else {
//                    Log.e("joinWaitingClass", response.code() + " " + response.message());
                    loader.joinWaitingListFailure();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.joinWaitingListFailure();
//                Log.e("joinWaitingClass", t.getMessage());
            }
        });
    }

    public void sendEmailVerify(String token) {
        SendEmailVerifyModel model = new SendEmailVerifyModel();
        model.setUsername("");
        Call<Object> call = RetrofitClient.getAPI().sendEmailVerify(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
//                    Log.e("VerifyS: ", response.message());
                } else {
//                    Log.e("VerifySF: ", response.message());
//                    Log.e("VerifySF: ", String.valueOf(response.code()));

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("VerifyF: ", t.getMessage());
                MSharedPreferences.getInstance().setKey(null);
            }
        });
    }

    public void leaveWaitingClass(DayClassIdModel model, final LeaveWaitingListStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().leaveWaitingClass(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loader.leaveWaitingListSuccess();
                } else {
                    loader.leaveWaitingListFailure();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                loader.leaveWaitingListFailure();
            }
        });
    }

    public void forgotPass(ForgotPassModel model, final ForgotPassStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().forgotPass(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    loader.onSendEmailSuccess(response.body().toString());
//                    Log.e("leaveWaitingClass", response.body().toString());
                } else {
                    loader.onSendEmailFailure(String.valueOf(response.code()));
//                    Log.e("leaveWaitingClass", response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
//                Log.e("leaveWaitingClass", t.getMessage());
                loader.onChangePassFailure(CONNECTION_ERROR);
            }
        });
    }

    public void isValidForgotPass(PassKeyModel model, final ForgotPassStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().isValidForgotPass(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    // TODO send new pass to server
                    loader.onSecretKeySuccess(response.body().toString());
//                    Log.e("leaveWaitingClass", response.body().toString());
                } else {
                    loader.onSecretKeyFailure(String.valueOf(response.code()));
//                    Log.e("leaveWaitingClass", response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
//                Log.e("leaveWaitingClass", t.getMessage());
                loader.onChangePassFailure(CONNECTION_ERROR);
            }
        });
    }

    public void changePass(ChangePassModel model, final ForgotPassStatus loader) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<Object> call = RetrofitClient.getAPI().changePass(token, model);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    //TODO do something
                    loader.onChangePassSuccess(response.body().toString());
//                    Log.e("leaveWaitingClass", response.body().toString());
                } else {
                    loader.onChangePassFailure(String.valueOf(response.code()));
//                    Log.e("leaveWaitingClass", response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
//                Log.e("leaveWaitingClass", t.getMessage());
                loader.onChangePassFailure(CONNECTION_ERROR);
            }
        });
    }

    public void getAllStudios(final GetBannerCode getBannerCode) {
        String token = MSharedPreferences.getInstance().getKey();
        Call<List<AllStudios>> call = RetrofitClient.getAPI().getAllStudios(token);
        call.enqueue(new Callback<List<AllStudios>>() {
            @Override
            public void onResponse(Call<List<AllStudios>> call, Response<List<AllStudios>> response) {
                if (response.isSuccessful()) {
//                    List<AllStudios> allStudios = new ArrayList<>();
//                    allStudios = response.body();
                    getBannerCode.getBannerCode(response.body().get(0).getBanner().toString());
                    //videoCode = response.body().get(0).getBanner().toString();

//                    if (allStudios != null && allStudios.get(0).getTeachers().length > 0) {
//
//                    }
//                    MSharedPreferences.getInstance().setTeachers(new Gson().toJson(teachers));

                    Log.e("getAllStudios", response.body().get(0).getBanner().toString());
                } else {
                    Log.e("getAllStudios", response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<AllStudios>> call, Throwable t) {
                Log.e("getAllStudios", t.getMessage());
            }
        });


    }

    public void uploadAvatar(String pathAvatar, UploadUserAvatar<String> uploadUserAvatar) {
        String token = MSharedPreferences.getInstance().getKey();

        Log.e("0x008800", "uploadAvatar: string pathAvatar" + pathAvatar);
        File file = new File(pathAvatar);
        Log.e("0x008800", "uploadAvatar: path avatar " + file.getPath());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        Log.e("0x008800", "uploadAvatar: requesBody " + requestBody);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        Log.e("0x008800", "uploadAvatar: body " + body);
//        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), file.getName());
//        Log.e("0x008800", "uploadAvatar: file get name " + file.getName() );
//        String desc = "jpg";


        // Log.e("0x008800", "uploadAvatar: name " + name );
//        UploadAvatar model = new UploadAvatar();
//        model.setAvatar(file);

        Call<ResponseBody> call = RetrofitClient.getAPI().uploadAvatar(token, body);
        Log.e("0x008800", "uploadAvatar: token " + token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 205) {

                    Log.e("0x008800", "onResponse: sucessful upload file");
                    uploadUserAvatar.onSuccessUploadUserAvatar("successful upload avatar");

                } else {

                    Log.e("0x008800", "onResponse: dont upload file");
                    Log.e("0x008800", "onResponse: code " + response.code());
                    try {
                        Log.e("0x008800", "onResponse: errobody " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    uploadUserAvatar.onFailureUploadUserAvatar("fail upload avatar");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e("0x008800", "onFailure: upload file" + t.getMessage());
            }
        });
    }
}
