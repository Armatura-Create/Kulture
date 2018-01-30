package app.kulture.kucherenko.init.com.kulture.ui.activity.launch_screen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.interfaces.AllClasses;
import app.kulture.kucherenko.init.com.kulture.interfaces.ApplicationDataLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.TeachersLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.ui.activity.extra_first.HowToActivity;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr.ClassesFragment;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class LaunchScreenActivity extends AppCompatActivity implements AllClasses, TeachersLoadingStatus, ApplicationDataLoadingStatus {

    private String TAG = "st18rai";

    private Handler handler;
    private Runnable runnable;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        Intent intent = new Intent(LaunchScreenActivity.this, HowToActivity.class);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("data_day_classes_id")) {
                intent.putExtra("data_day_classes_id", getIntent().getExtras().getString("data_day_classes_id"));
            }
        }

        runnable = () -> {
            startActivity(intent);
            overridePendingTransition(R.anim.abc_slide_in_bottom, android.R.anim.fade_out);
            finish();
        };

        if (Connection.isNetworkAvailable(this)) {
            // при успешном запросе - переход на другое активити
            Request.getInstance().allFutureDayClasses(this);
            Request.getInstance().getStudioTeachers(this, "1");
        } else SimpleAlert.showNoConnection(this);

        setContentView(R.layout.activity_launch_screen);

        animationView = findViewById(R.id.animation_view);
        animationView.useHardwareAcceleration(true);

        // переход на другое активити спустя 5 сек
        handler.postDelayed(runnable, 5000);
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        handler = null;
        runnable = null;
        animationView.cancelAnimation();

        Log.e(TAG, "onStop: splash screen");
        super.onStop();
    }

    @Override
    public void getData(List<AllDayClassModel> info) {
        MApplication.getInstance().setFutureClasses(info);
        MApplication.getInstance().setAllClasses(info);
        try {
            handler.post(runnable);
        } catch (Exception e) {
            Log.e(TAG, "Exception:  splash screen");
        }
        Log.e(TAG, "getData:  splash screen");
    }

    @Override
    public void onAppDataSuccess() {

    }

    @Override
    public void onAppDataFailure(String message) {

    }

    @Override
    public void onTeachersSuccess(String message) {

    }

    @Override
    public void onTeachersFailure(String message) {

    }
}