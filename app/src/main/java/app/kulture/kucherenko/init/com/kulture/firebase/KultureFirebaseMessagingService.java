package app.kulture.kucherenko.init.com.kulture.firebase;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by slava on 02.11.17.
 */

public class KultureFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCMService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "From title: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "From body: " + remoteMessage.getNotification().getBody());
            Log.e(TAG, "actionClick: " + remoteMessage.getNotification().getClickAction());
        }

//        RemoteMessage.Notification notification = remoteMessage.getNotification();
//        Map<String, String> data = remoteMessage.getData();

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData().toString());
        }

        Intent intent = new Intent("Kulture_D-Studio");
        intent.putExtra("data_click_action", remoteMessage.getData().get("data_click_action"));
        intent.putExtra("data_day_classes_id", remoteMessage.getData().get("data_day_classes_id"));
        intent.putExtra("body", remoteMessage.getNotification().getBody());
        intent.putExtra("from", remoteMessage.getNotification().getTitle());

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}