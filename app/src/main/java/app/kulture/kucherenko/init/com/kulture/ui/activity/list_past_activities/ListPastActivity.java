package app.kulture.kucherenko.init.com.kulture.ui.activity.list_past_activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.interfaces.DayClassInfoStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class ListPastActivity extends AppCompatActivity implements UserInfoLoadingStatus, DayClassInfoStatus<AllDayClassModel> {

    private RecyclerAdapterListPast adapter;

    //Lists from reservations
    private UserInfoModel mUserInfo;
    private List<AllDayClassModel> mAllDayClasses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_previous);
        mAllDayClasses = new ArrayList<>();
        if (Connection.isNetworkAvailable(this)) {
            Request.getInstance().getUserInfo(this);


            adapter = new RecyclerAdapterListPast();
            Toolbar toolbar = findViewById(R.id.toolbar_list_past);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());

            initRecyclerView();
        } else SimpleAlert.showNoConnection(this);
    }

    private void initRecyclerView() {

        final RecyclerView rvListPast = findViewById(R.id.rv_list_past);

        rvListPast.setNestedScrollingEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListPast.setLayoutManager(linearLayoutManager);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        rvListPast.setAdapter(scaleAdapter);

    }

    private void addIntoList() {
        if (mUserInfo != null) {
            for (int i = 0; i < mUserInfo.getUserPastReservations().size(); i++) {
                if (Connection.isNetworkAvailable(this)) {
                    Request.getInstance().getAllDayClass(mUserInfo.getUserPastReservations().get(i).getDayClassId(), this);
                } else SimpleAlert.showNoConnection(this);
            }
        }
    }

    @Override
    public void onUserInfoSuccess(UserInfoModel user) {
        mUserInfo = user;
        addIntoList();
    }

    @Override
    public void onUserInfoFailure(String message) {

    }

    @Override
    public void onClassInfoSuccess(AllDayClassModel info) {
        mAllDayClasses.add(info);
        adapter.setPastClasses(mAllDayClasses);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClassInfoFailure(String message) {

    }
}
