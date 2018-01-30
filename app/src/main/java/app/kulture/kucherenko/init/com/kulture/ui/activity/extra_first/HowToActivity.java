package app.kulture.kucherenko.init.com.kulture.ui.activity.extra_first;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.activity.launch_screen.LaunchScreenActivity;
import app.kulture.kucherenko.init.com.kulture.ui.activity.main.MainActivity;
import app.kulture.kucherenko.init.com.kulture.ui.activity.signin.SignInActivity;

/**
 * Created by alex on 10/13/17.
 */

public class HowToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_layout);
//        if (!MSharedPreferences.getInstance().isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
//        }
//        Button skip = (Button) findViewById(R.id.skipBtn);
//        skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchHomeScreen();
//            }
//        });

    }

    private void launchHomeScreen() {
        MSharedPreferences.getInstance().setFirstTimeLaunch(false);
        if (MSharedPreferences.getInstance().getKey() == null)
            startActivity(new Intent(this, SignInActivity.class));
        else {
            //TODO CHECK KEY VALIDATION
            Intent intent = new Intent(this, MainActivity.class);
            if (getIntent().getExtras() != null){
                if (getIntent().getExtras().containsKey("data_day_classes_id")){
                    intent.putExtra("data_day_classes_id", getIntent().getExtras().getString("data_day_classes_id"));
                }
            }
            startActivity(intent);
        }
    }
}
