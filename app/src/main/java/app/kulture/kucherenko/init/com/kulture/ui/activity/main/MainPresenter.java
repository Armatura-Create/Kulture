package app.kulture.kucherenko.init.com.kulture.ui.activity.main;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import app.kulture.kucherenko.init.com.kulture.ui.fragments.FragmentsAdapter;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr.ClassesFragment;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.home.HomeFragment;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.shop.ShopFragment;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.mybookings.MyBookingsFragment;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.myprofile.MyProfileFragment;

import static app.kulture.kucherenko.init.com.kulture.utils.StaticValues.FRAGMENT_APPARELS;
import static app.kulture.kucherenko.init.com.kulture.utils.StaticValues.FRAGMENT_CLASSES;
import static app.kulture.kucherenko.init.com.kulture.utils.StaticValues.FRAGMENT_HOME;
import static app.kulture.kucherenko.init.com.kulture.utils.StaticValues.FRAGMENT_PROFILE;
import static app.kulture.kucherenko.init.com.kulture.utils.StaticValues.FRAGMENT_SHOP;

/**
 * Created by alex on 10/14/17.
 */

public class MainPresenter implements MainContract.EventListener {

    private MainContract.View view;

    private Fragment homeFragment;
    private Fragment profileFragment;
    private Fragment apparelsFragment;
    private Fragment classesFragment;
    private Fragment moreFragment;

    private HashMap<String, Fragment> frMap;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setUpViewPager(ViewPager pager) {
        if (view.getContext() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            FragmentsAdapter adapter = new FragmentsAdapter(activity.getSupportFragmentManager());
            homeFragment = HomeFragment.newInstance(view);
            classesFragment = ClassesFragment.newInstance();
            apparelsFragment = MyBookingsFragment.newInstance();
            profileFragment = MyProfileFragment.newInstance();
            moreFragment = ShopFragment.newInstance();

            setUpFragments();

            adapter.addFragment(frMap.get(FRAGMENT_HOME));
            adapter.addFragment(frMap.get(FRAGMENT_CLASSES));
            adapter.addFragment(frMap.get(FRAGMENT_APPARELS));
            adapter.addFragment(frMap.get(FRAGMENT_SHOP));
            adapter.addFragment(frMap.get(FRAGMENT_PROFILE));

            pager.setAdapter(adapter);
        }
    }

    public HashMap<String, Fragment> getFragmentsMap() {
        return frMap;
    }


    private void setUpFragments() {
        frMap = new HashMap<>();
        frMap.put(FRAGMENT_HOME, homeFragment);
        frMap.put(FRAGMENT_CLASSES, classesFragment);
        frMap.put(FRAGMENT_APPARELS, apparelsFragment);
        frMap.put(FRAGMENT_PROFILE, profileFragment);
        frMap.put(FRAGMENT_SHOP, moreFragment);
    }
}
