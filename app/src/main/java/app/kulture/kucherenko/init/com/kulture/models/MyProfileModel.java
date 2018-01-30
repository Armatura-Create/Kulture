package app.kulture.kucherenko.init.com.kulture.models;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;

public class MyProfileModel {

    private String[] mCategories = {
            MApplication.getInstance().getMResources().getString(R.string.payments),
            MApplication.getInstance().getMResources().getString(R.string.credits_packages),
            MApplication.getInstance().getMResources().getString(R.string.upgrade_to_kulture),
            MApplication.getInstance().getMResources().getString(R.string.upgrade_to_unlimited),
//            MApplication.getInstance().getMResources().getString(R.string.how_to),
            MApplication.getInstance().getMResources().getString(R.string.contacts),
            MApplication.getInstance().getMResources().getString(R.string.share)
    };

    private String[] mDescription = {
            MApplication.getInstance().getMResources().getString(R.string.payments_description),
            MApplication.getInstance().getMResources().getString(R.string.credits_description),
            MApplication.getInstance().getMResources().getString(R.string.upgrade_description),
            MApplication.getInstance().getMResources().getString(R.string.vip_description),
//            MApplication.getInstance().getString(R.string.need_help),
            MApplication.getInstance().getMResources().getString(R.string.addresses),
            MApplication.getInstance().getMResources().getString(R.string.share_this_app)
    };

    private int[] mImageIds = {
            R.drawable.payments_icon,
            R.mipmap.ic_launcher,
            R.drawable.member_icon,
            R.mipmap.ic_launcher,
//            R.drawable.help_icon,
            R.drawable.contact_icon,
            R.drawable.share_icon
    };

    public String[] getmCategories() {
        return mCategories;
    }

    public String[] getmDescription() {
        return mDescription;
    }

    public int[] getmImageIds() {
        return mImageIds;
    }
}
