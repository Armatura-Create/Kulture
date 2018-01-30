package app.kulture.kucherenko.init.com.kulture.ui.activity.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.braintree.BraintreeRequestActivity;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivityProfileBinding;
import app.kulture.kucherenko.init.com.kulture.events.BuyMemberEvent;
import app.kulture.kucherenko.init.com.kulture.events.BuyUnlimitedEvent;
import app.kulture.kucherenko.init.com.kulture.firebase.KultureFirebaseInstanceIDService;
import app.kulture.kucherenko.init.com.kulture.firebase.KultureNotificationManager;
import app.kulture.kucherenko.init.com.kulture.interfaces.ApplicationDataLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.ReserveStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToUnlimitedMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import app.kulture.kucherenko.init.com.kulture.utils.StaticValues;

import static android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import static app.kulture.kucherenko.init.com.kulture.utils.StaticValues.FRAGMENT_PROFILE;

/**
 * @author Alex Kucherenko(Godsmack)
 */
public class MainActivity extends BraintreeRequestActivity implements MainContract.View, UpToMemberStatus, UpToUnlimitedMemberStatus, ApplicationDataLoadingStatus, UserInfoLoadingStatus {


    private static final String TAG = "MainActivity";

    private int buyMember = 0;

    private ActivityProfileBinding binding;
    private MainPresenter presenter;
    private MenuItem prevMenuItem;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("Kulture_D-Studio"));

        intent = getIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        presenter = new MainPresenter(this);
        presenter.setUpViewPager(binding.pager);
        binding.pager.addOnPageChangeListener(this);
        binding.pager.setOffscreenPageLimit(5);
        binding.navigation.setOnNavigationItemSelectedListener(listener);

        if (MSharedPreferences.getInstance().getAppData() == null){
            Request.getInstance().getApplicationData(this);
        }

        initIntent();

        firebase();
        alert();
    }

    private void alert() {
        UserInfoModel userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);
        if (userInfo.isVIP()) {
            if (userInfo.getVipFinishDate() != null) {
                final Calendar finish = Calendar.getInstance();
                final Calendar today = Calendar.getInstance();

                finish.setTime(new Date(Integer.valueOf("1" + userInfo.getVipFinishDate().substring(2, 4)),
                        Integer.valueOf(userInfo.getVipFinishDate().substring(5, 7)),
                        Integer.valueOf(userInfo.getVipFinishDate().substring(8, 10))));
                finish.add(Calendar.DATE, -7);

                Log.e("alert: ", finish.toString());
                Log.e("alert: ", String.valueOf(Integer.valueOf(userInfo.getVipFinishDate().substring(0, 4))));

                if (finish.get(Calendar.MONTH) == today.get(Calendar.MONTH) + 1 && finish.get(Calendar.DAY_OF_MONTH) < today.get(Calendar.DAY_OF_MONTH) + 7) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Unlimited UserPack")
                            .setMessage("You have to pay an unlimited user for further use of the privileges," +
                                    " the end date - " + userInfo.getVipFinishDate().substring(0,10))
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, i) -> {
                                dialog.cancel();
                            });
                    builder.show();
                }
            }
        }
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().register(this);

        super.onResume();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);

        super.onStop();
    }

    void initIntent() {
        if (intent.getExtras() != null) {
            if (intent.getExtras().containsKey("data_day_classes_id")) {

                KultureNotificationManager manager = new KultureNotificationManager(this, getIntent());
                manager.updateClassInfo(
                        Integer.valueOf(intent.getExtras().getString("data_day_classes_id"))
                        , false);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        this.intent = intent;
        initIntent();
    }

    private void firebase() {

        //get token
        String token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        String msg = getString(R.string.msg_token_fmt, token);
        Log.e(TAG, msg);

        KultureFirebaseInstanceIDService.sendRegistrationToServer(token);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            KultureNotificationManager manager =
                    new KultureNotificationManager(getApplicationContext(), intent);
            manager.updateClassInfo(Integer.valueOf(intent.getExtras().getString("data_day_classes_id")), true);
        }
    };

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here.
     * The action bar will automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

    private final OnNavigationItemSelectedListener listener = new OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    binding.pager.setCurrentItem(0);
                    break;
                }
                case R.id.navigation_classes: {
                    binding.pager.setCurrentItem(1);
                    break;
                }

                case R.id.navigation_mybookings: {
                    binding.pager.setCurrentItem(2);
                    break;
                }
                case R.id.navigation_shop: {
                    binding.pager.setCurrentItem(3);
                    break;
                }
                case R.id.navigation_profile: {
                    binding.pager.setCurrentItem(4);
                    break;
                }
            }
            return true;
        }

    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) prevMenuItem.setChecked(false);
        else binding.navigation.getMenu().getItem(0).setChecked(false);
        Log.d("page", String.valueOf(position));
        binding.navigation.getMenu().getItem(position).setChecked(true);
        prevMenuItem = binding.navigation.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void replaceFragment(String name) {
        switch (name) {
            case StaticValues.FRAGMENT_HOME: {
                binding.pager.setCurrentItem(0);
                break;
            }
            case StaticValues.FRAGMENT_CLASSES: {
                binding.pager.setCurrentItem(1);
                break;
            }
            case StaticValues.FRAGMENT_APPARELS: {
                binding.pager.setCurrentItem(2);
                break;
            }
            case StaticValues.FRAGMENT_SHOP: {
                binding.pager.setCurrentItem(3);
                break;
            }
            case FRAGMENT_PROFILE: {
                binding.pager.setCurrentItem(4);
                break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBuyMemberEvent(BuyMemberEvent event) {
        if (Connection.isNetworkAvailable(getContext())) {
            buyMember = 1;
            Request.getInstance().getApplicationData(this);
        } else {
            SimpleAlert.showNoConnection(getContext());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBuyUnlimitedEvent(BuyUnlimitedEvent event) {
        if (Connection.isNetworkAvailable(getContext())) {
            buyMember = 2;
            Request.getInstance().getApplicationData(this);
        } else {
            SimpleAlert.showNoConnection(getContext());
        }
    }

    @Override
    public void onUpdateMemberSuccess(String info) {
        UserInfoModel userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);
        userInfo.setIsMember(true);
        MSharedPreferences.getInstance().setUserInfo(new Gson().toJson(userInfo));
    }

    @Override
    public void onUpdateMemberFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateUnlimitedMemberSuccess(String info) {
        Request.getInstance().getUserInfo(this);
    }

    @Override
    public void onUpdateUnlimitedMemberFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Map<String, String> getReserveData() {
        return null;
    }

    @Override
    protected ReserveStatus<String> getLoading() {
        return null;
    }

    @Override
    protected void goToReserveList(int code) {

    }

    @Override
    protected AllDayClassModel getClassesModel() {
        return null;
    }

    @Override
    protected UpToMemberStatus getMemberStatus() {
        return this;
    }

    @Override
    protected UpToUnlimitedMemberStatus getUnlimitedMemberStatus() {
        return this;
    }

    @Override
    protected int getPriceId() {
        return 0;
    }

    @Override
    public void onAppDataSuccess() {
        switch (buyMember) {
            case 1:
                makePaymentRequest(MApplication.getInstance().getApplicationData().getMemberPrice(), MEMBER_CODE);
                break;
            case 2:
                makePaymentRequest(MApplication.getInstance().getApplicationData().getVipPrice(), MEMBER_UNLIMITED_CODE);
                break;
        }
    }

    @Override
    public void onAppDataFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("0x008800", "onActivityResult: activity");
        presenter.getFragmentsMap().get(StaticValues.FRAGMENT_PROFILE).onActivityResult(requestCode, resultCode, data);
    }

    public static void callOnActivityResultOnChildFragments(Fragment parent, int requestCode, int resultCode, Intent data) {
        FragmentManager childFragmentManager = parent.getChildFragmentManager();
        if (childFragmentManager != null) {
            List<Fragment> childFragments = childFragmentManager.getFragments();
            if (childFragments == null) {
                return;
            }
            for (Fragment child : childFragments) {
                if (child != null && !child.isDetached() && !child.isRemoving()) {
                    child.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    @Override
    public void onUserInfoSuccess(UserInfoModel user) {

    }

    @Override
    public void onUserInfoFailure(String message) {

    }

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
}