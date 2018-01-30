package app.kulture.kucherenko.init.com.kulture.models;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;

/**
 * Created by st18rai on 16.10.17.
 */

public class HomeModel {

    private String[] mDescription = {
            MApplication.getInstance().getMResources().getString(R.string.bouncefit),
            MApplication.getInstance().getMResources().getString(R.string.poundfit),
            MApplication.getInstance().getMResources().getString(R.string.dance),
            //MApplication.getInstance().getMResources().getString(R.string.music)
    };

    private int[] mImageIds = {
            R.drawable.boucefit_icon,
            R.drawable.poundfit_icon,
            R.drawable.dance_icon,
            //R.drawable.music_icon
    };

    public String[] getmDescription() {
        return mDescription;
    }

    public int[] getmImageIds() {
        return mImageIds;
    }
}
