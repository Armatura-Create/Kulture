package app.kulture.kucherenko.init.com.kulture.ui.activity.main;

import android.content.Context;
import android.support.v4.view.ViewPager;

/**
 * Created by Alex Kucherenko(Godsmack) on 10/14/17.
 */

interface MainContract {

    interface View extends IFragmentListener {
        Context getContext();

    }

    interface EventListener {

        void setUpViewPager(ViewPager pager);
    }
}
