package app.kulture.kucherenko.init.com.kulture.ui.activity.class_detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivityFullScreenImageBinding;

public class FullScreenImage extends AppCompatActivity {

    private ActivityFullScreenImageBinding binding;
    private String imageURLs;
    private String host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_screen_image);
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        imageURLs = getIntent().getStringExtra("Image URL");
        host = getString(R.string.host);

        Uri uri = Uri.parse(host + imageURLs);
        Glide.with(getApplicationContext()) //передаем контекст приложения
                .load(uri)
                .fitCenter()
                .thumbnail(0.5f)
                .priority(Priority.IMMEDIATE)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(binding.imageViewFullScreen); //ссылка на ImageView


//        binding.webViewImage.setBackgroundColor(Color.BLACK);
//        binding.webViewImage.getSettings().setSupportZoom(true);
//        binding.webViewImage.getSettings().setBuiltInZoomControls(true);
//        binding.webViewImage.setPadding(0, 0, 0, 0);
//        binding.webViewImage.setScrollbarFadingEnabled(true);
//        binding.webViewImage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        binding.webViewImage.loadUrl(host + imageURLs);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_screen_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, host + imageURLs);
            startActivity(Intent.createChooser(shareIntent, "Send ..."));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
