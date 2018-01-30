package app.kulture.kucherenko.init.com.kulture.ui.activity.how_to;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.kulture.kucherenko.init.com.kulture.R;

/**
 * Created by alex on 03.11.17.
 */

public class HowToMyProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        Toolbar backbutton = (Toolbar) findViewById(R.id.toolbar_how_to);
        backbutton.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
