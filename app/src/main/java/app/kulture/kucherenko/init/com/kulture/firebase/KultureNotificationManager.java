package app.kulture.kucherenko.init.com.kulture.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.interfaces.DayClassInfoStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.MemberPriceLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.activity.class_detail.DetailClass;
import app.kulture.kucherenko.init.com.kulture.ui.activity.main.MainActivity;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

/**
 * Created by slava on 24.11.17.
 */

public class KultureNotificationManager implements DayClassInfoStatus<AllDayClassModel>, MemberPriceLoadingStatus {

    private Context mContext;
    private Intent data;
    private static final String CHANNEL = "Kulture125";
    private boolean showNotific;
    private int memberPrice;
    private AllDayClassModel info;

    public KultureNotificationManager(Context context, Intent data) {
        this.mContext = context;
        this.data = data;
    }

    public void showNotification(String body, String from, Intent intent) {


        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext,
                0 /* Request code */,
                intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(mContext, CHANNEL)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(from)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public void updateClassInfo(int id, boolean showNotific) {
        this.showNotific = showNotific;
        if (Connection.isNetworkAvailable(mContext)) {
            Request.getInstance().getAllDayClass(id, this);
        } else {
            SimpleAlert.showNoConnection(mContext);
        }

    }

    public Intent goToDetailClass(AllDayClassModel model) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("classModel", model);

        Intent intent = new Intent(mContext, DetailClass.class);
        intent.putExtra("classModel", bundle);
        boolean isFromWaiting = isFromWaiting(model);
        intent.putExtra("activity detail", isFromWaiting);
        intent.putExtra("memberPrice", memberPrice);
        if (isFromWaiting){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        return intent;
    }

    private boolean isFromWaiting(AllDayClassModel model){
        UserInfoModel userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);
        if (userInfo != null && userInfo.getClassWaitingList().length > 0) {
            for (int i : userInfo.getClassWaitingList()) {
                if (i == model.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onClassInfoSuccess(AllDayClassModel info) {
        this.info = info;
        Request.getInstance().getMemberPrice(this);
    }

    @Override
    public void onClassInfoFailure(String message) {

    }

    @Override
    public void onMemberPriceSuccess(int price) {
        memberPrice = price;
        if (showNotific){
            showNotification(
                    data.getExtras().getString("body"),
                    data.getExtras().getString("from"),
                    goToDetailClass(info));
        } else {
            mContext.startActivity(goToDetailClass(info));
        }
    }

    @Override
    public void onMemberPriceFailure(String message) {

    }
}