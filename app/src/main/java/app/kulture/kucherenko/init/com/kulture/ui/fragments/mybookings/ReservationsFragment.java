package app.kulture.kucherenko.init.com.kulture.ui.fragments.mybookings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.interfaces.CancelClassStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.CancelReserveModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.ui.activity.list_past_activities.ListPastActivity;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class ReservationsFragment extends android.support.v4.app.Fragment
        implements SwipeRefreshLayout.OnRefreshListener, UserInfoLoadingStatus, CancelClassStatus {

    private RecyclerAdapterReservation adapter;
    private SwipeRefreshLayout mSwipe;
    private TextView mTvPastActivities;
    RecyclerAdapterReservation.CancelClassesStatus mCancelClassesStatus;

    //Lists from reservations
    private UserInfoModel mUserInfo;
    private List<AllDayClassModel> mAllDayClasses;

    private AlertDialog mAdLogout;
    private int position;
    private boolean isDeleted = false;

    private ScaleInAnimationAdapter scaleAdapter;
    private RecyclerView rvReservations;

    public static ReservationsFragment getInstance() {
        return new ReservationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAllDayClasses = new ArrayList<>();
        adapter = new RecyclerAdapterReservation(this);

        mCancelClassesStatus = (id, position) -> {
            mAdLogout = new AlertDialog.Builder(getContext()).create();
            mAdLogout.setMessage("Are you sure?");
            mAdLogout.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                    (dialog, which) -> mAdLogout.cancel());
            mAdLogout.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    (dialog, which) -> {
                        CancelReserveModel cancelReserveModel = new CancelReserveModel();
                        cancelReserveModel.setReserveId(id);
                        if (Connection.isNetworkAvailable(getContext())) {
                            Request.getInstance().cancelReserve(cancelReserveModel, ReservationsFragment.this);
                            mSwipe.setRefreshing(true);
                        } else {
                            SimpleAlert.showNoConnection(getContext());
                            mSwipe.setRefreshing(false);
                        }
                        this.position = position;
                    });
            mAdLogout.show();
        };

        return inflater.inflate(R.layout.fragment_reservations_my_bookings, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        assert view != null;
        mTvPastActivities = view.findViewById(R.id.tv_past_activities);
        mSwipe = view.findViewById(R.id.swipeRefresh_reservation);

        mSwipe.setOnRefreshListener(this);
        mSwipe.setRefreshing(true);

        if (Connection.isNetworkAvailable(getContext())) {
            Request.getInstance().getUserInfo(this);
            mAllDayClasses.clear();
        } else {
            SimpleAlert.showNoConnection(getContext());
            mSwipe.setRefreshing(false);
        }

        rvReservations = view.findViewById(R.id.rv_reservations);
        rvReservations.setNestedScrollingEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvReservations.setLayoutManager(linearLayoutManager);
        scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        rvReservations.setAdapter(scaleAdapter);

        mTvPastActivities.setText("View past activities (" + (mUserInfo == null ? 0 :
                mUserInfo.getUserPastReservations().size()) + ")");

        //слушаель на нажатие кнопки List Previous Activities
        MaterialRippleLayout listPast = view.findViewById(R.id.ripper_list_past);

        listPast.setOnClickListener(view1 -> startActivity(new Intent(getActivity().getApplication(), ListPastActivity.class)));
    }

    @SuppressLint("SetTextI18n")
    private void addIntoList() {

        scaleAdapter.setFirstOnly(false);
        mAllDayClasses.clear();
        mUserInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);

        int idReservation;
        int idAllClasses;
        if (mUserInfo != null) {
            for (int i = 0; i < mUserInfo.getUserReservations().size(); i++) {
                for (int j = 0; j < MApplication.getInstance().getAllClasses().size(); j++) {
                    idReservation = mUserInfo.getUserReservations().get(i).getDayClassId();
                    idAllClasses = MApplication.getInstance().getAllClasses().get(j).getId();
                    if (idReservation == idAllClasses) {
                        mAllDayClasses.add(MApplication.getInstance().getAllClasses().get(j));
                    }
                }
            }
            adapter.setClassesReservations(mAllDayClasses);
            adapter.setUserInfo(mUserInfo);
            adapter.notifyDataSetChanged();

        }

        mTvPastActivities.setText("View past activities (" + (mUserInfo == null ? 0 :
                mUserInfo.getUserPastReservations().size()) + ")");

        mSwipe.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        if (Connection.isNetworkAvailable(getContext())) {
            Request.getInstance().getUserInfo(this);
        } else {
            SimpleAlert.showNoConnection(getContext());
            mSwipe.setRefreshing(false);
        }
    }

    @Override
    public void onUserInfoFailure(String message) {

    }

    @Override
    public void onUserInfoSuccess(UserInfoModel user) {
        if (!isDeleted) {
            mUserInfo = user;
            addIntoList();
        } else {
            adapter.remove(position);
            adapter.delReservation(mAllDayClasses.get(position));
            isDeleted = false;
            adapter.notifyDataSetChanged();
            scaleAdapter.setFirstOnly(true);
        }
        mSwipe.setRefreshing(false);
    }

    @Override
    public void onSuccessCanceled(String info) {
        isDeleted = true;
        Request.getInstance().getUserInfo(this);
    }

    @Override
    public void onFailureCanceled(String message) {
        mSwipe.setRefreshing(false);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
