package app.kulture.kucherenko.init.com.kulture.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.models.TokenModel;


public class KultureFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIDService";
    private static final String FRIENDLY_ENGAGE_TOPIC = "kulture";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
//        FirebaseMessaging.getInstance()
//                .subscribeToTopic(FRIENDLY_ENGAGE_TOPIC);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    public static void sendRegistrationToServer(String token) {
        TokenModel tokenModel = new TokenModel();
        tokenModel.setToken(token);
        Request.getInstance().registerDevice(tokenModel);
    }
}