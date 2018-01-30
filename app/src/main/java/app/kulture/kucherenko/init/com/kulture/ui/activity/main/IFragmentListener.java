package app.kulture.kucherenko.init.com.kulture.ui.activity.main;

import android.support.v4.view.ViewPager;

/**
 * Created by alex on 11/9/17.
 */

public interface IFragmentListener extends ViewPager.OnPageChangeListener {
    void replaceFragment(String name);
}
