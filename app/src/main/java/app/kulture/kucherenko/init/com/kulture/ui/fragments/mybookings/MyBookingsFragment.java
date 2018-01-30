package app.kulture.kucherenko.init.com.kulture.ui.fragments.mybookings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.kulture.kucherenko.init.com.kulture.R;

/**
 * Created by alex on 10/14/17.
 */

public class MyBookingsFragment extends android.support.v4.app.Fragment {

    private FragmentTabHost mTabHost;

    public static MyBookingsFragment newInstance() {
        return new MyBookingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("reservation").setIndicator("Reservation"),
                ReservationsFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("waiting list").setIndicator("Waiting List"),
                WaitingListFragment.class, null);

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
