package app.kulture.kucherenko.init.com.kulture.ui.activity.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.GoogleMapContact;

/**
 * Created by alex on 25.10.17.
 */

public class ContactActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        fragmentManager = getSupportFragmentManager();
        initActions();
    }

    private void initActions() {
        Toolbar backbutton = (Toolbar) findViewById(R.id.toolbar_contact);
        backbutton.setNavigationOnClickListener(view -> onBackPressed());

        TextView phone = (TextView) findViewById(R.id.tv_tel);
        phone.setOnClickListener(view -> {
            Uri number = Uri.parse("tel:63846680");
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(callIntent);
        });

        TextView facebook = (TextView) findViewById(R.id.tv_facebook);
        facebook.setOnClickListener(view -> {
            Uri number = Uri.parse("http://www.facebook.com/kulturestudios");
            Intent callIntent = new Intent(Intent.ACTION_VIEW, number);
            startActivity(callIntent);
        });

        TextView instagram = (TextView) findViewById(R.id.tv_instagram);
        instagram.setOnClickListener(view -> {
            Uri number = Uri.parse("http://www.instagram.com/kulture_studios");
            Intent callIntent = new Intent(Intent.ACTION_VIEW, number);
            startActivity(callIntent);
        });

        TextView youtube = (TextView) findViewById(R.id.tv_youtube);
        youtube.setOnClickListener(view -> {
            Uri number = Uri.parse("http://www.youtube.com/kultureStudios");
            Intent callIntent = new Intent(Intent.ACTION_VIEW, number);
            startActivity(callIntent);
        });

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container_map, new GoogleMapContact()).commit();

    }
}
