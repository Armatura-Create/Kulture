package app.kulture.kucherenko.init.com.kulture.client;

import java.io.File;
import java.util.List;
import java.util.Map;

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
import app.kulture.kucherenko.init.com.kulture.models.UploadAvatar;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.Teacher;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.ChangePassModel;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.ForgotPassModel;
import app.kulture.kucherenko.init.com.kulture.models.forgotpass.PassKeyModel;
import app.kulture.kucherenko.init.com.kulture.models.payments.PaymentsHistoryModel;
import app.kulture.kucherenko.init.com.kulture.models.user.PhoneModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

interface KultureAPI {

    //WARNING! Use POST or GET annotation as requested by server restAPI documentation
    //WARNING! используйте POST или GET аннотации в соответствии с указаниями сервера

    @POST("/authorization/forgot_password")
    Call<Object> forgotPass(@Header("Authorization") String token, @Body ForgotPassModel data);

    @POST("/authorization/is_valid_forgot_password_key")
    Call<Object> isValidForgotPass(@Header("Authorization") String token, @Body PassKeyModel data);

    @POST("/authorization/change_password")
    Call<Object> changePass(@Header("Authorization") String token, @Body ChangePassModel data);

    @POST("/authorization/registration/")
    Call<TokenAtRegistration> registerUser(@Body RegisterModel data);

    @POST("/authorization/login/")
    Call<AuthenotificationKey> login(@Body SignInModel data);

    @POST("/authorization/authorization_with_facebook/")
    Call<TokenModel> facebookSignUP(@Body FacebookTokenModel data);

    @POST("/notifications/register_android_device/")
    Call<Object> registerDevice(@Header("Authorization") String token, @Body TokenModel data);

    @POST("/payment/create_transaction/")
    Call<Object> paymentBraintree(@Header("Authorization") String token, @Body PaymentModel model);

    @POST("/payment/create_transaction_with_membership/")
    Call<Object> createTransactionWithMembership(@Header("Authorization") String token, @Body PaymentModel model);

    @POST("/classes/join_waiting_list/")
    Call<Object> joinWaitingClass(@Header("Authorization") String token, @Body DayClassIdModel model);

    @POST("/authorization/send_verification_email/")
    Call<Object> sendEmailVerify(@Header("Authorization") String token, @Body SendEmailVerifyModel model);

    @POST("/classes/leave_waiting_list/")
    Call<Object> leaveWaitingClass(@Header("Authorization") String token, @Body DayClassIdModel model);

    @POST("/authorization/logout/")
    Call<Object> logOut(@Header("Authorization") String token);

    @POST("/payment/buy_membership/")
    Call<Object> updateToMember(@Header("Authorization") String token, @Body UpToMemberModel model);

    @POST("/payment/buy_vip/")
    Call<Object> updateToUnlimitedMember(@Header("Authorization") String token, @Body UpToUnlimitedMemberModel model);

    @POST("/classes/reserve_day_class/")
    @FormUrlEncoded
    Call<Object> reserveClass(@Header("Authorization") String token, @FieldMap(encoded = true) Map<String,String> params);


    @PATCH("/authorization/my_user")
    Call<UserInfoModel> updateUser(@Header("Authorization") String token, @Body UpdateUserInfoModel info);

    @PATCH("/authorization/my_user")
    Call<UserInfoModel> updatePhone(@Header("Authorization") String token, @Body PhoneModel info);


    @POST("/classes/cancel_reserve/")
    Call<Object> cancelReserve(@Header("Authorization") String header, @Body CancelReserveModel model);

    @GET("/payment/get_token/")
    Call<BraintreeTokenModel> getBraintreeToken(@Header("Authorization") String header);

    @GET("/authorization/my_user")
    Call<UserInfoModel> getUserInfo(@Header("Authorization") String header);

    @GET("/studio/all_studio_teachers/{studio_id}/")
    Call<List<Teacher>> getStudioInstructors(@Header("Authorization") String header, @Path("studio_id") String id);

    @GET("/classes/all_day_classes")
    Call<List<AllDayClassModel>> getAllDayClasses(@Header("Authorization") String header);

    @GET("/classes/all_future_day_classes")
    Call<List<AllDayClassModel>> getAllFutureDayClasses(@Header("Authorization") String header);

    @GET("/payment/my_payments_history/")
    Call<List<PaymentsHistoryModel>> getPaymentsHistory(@Header("Authorization") String token);

    @GET("/studio/all_studios/")
    Call<List<AllStudios>> getAllStudios(@Header("Authorization") String token);

    @GET("/payment/my_payment_items/")
    Call<List<ClassItemModel>> getAllItems(@Header("Authorization") String token);

    @GET("/payment/get_member_price")
    Call<MemberPriceModel> getMemberPrice(@Header("Authorization") String token);

    @GET("/application/get_application_data")
    Call<ApplicationData> getApplicationData(@Header("Authorization") String token);

    @GET("/classes/day_class_details/{id}/")
    Call<AllDayClassModel> getAllClassInfo(@Header("Authorization") String token, @Path("id") String id);

    @Multipart
    @POST("/authorization/upload_image")
    Call<ResponseBody> uploadAvatar(@Header("Authorization") String token, @Part MultipartBody.Part image);

//    @POST("/authorization/upload_image/")
//    Call<ResponseBody> uploadAvatar(@Header("Authorization") String token, @Body UploadAvatar model);


}
