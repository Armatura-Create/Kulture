package app.kulture.kucherenko.init.com.kulture.ui.fragments.mybookings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.interfaces.LeaveWaitingListStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.DayClassIdModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */

public class WaitingListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, UserInfoLoadingStatus, LeaveWaitingListStatus {

    private RecyclerAdapterWaitingList adapter;
    private SwipeRefreshLayout mSwipe;

    private List<AllDayClassModel> mAllDayClasses;
    private UserInfoModel mUserInfo;

    public static WaitingListFragment getInstance() {
        return new WaitingListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAllDayClasses = new ArrayList<>();
        adapter = new RecyclerAdapterWaitingList();
        return inflater.inflate(R.layout.fragment_waiting_list_my_bookings, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {

            mSwipe = view.findViewById(R.id.swipeRefresh_waiting);
            mSwipe.setOnRefreshListener(this);
            mSwipe.setRefreshing(true);

            if (Connection.isNetworkAvailable(getContext())) {
                Request.getInstance().getUserInfo(this);
                mAllDayClasses.clear();
            } else {
                SimpleAlert.showNoConnection(getContext());
                mSwipe.setRefreshing(false);
            }

            final RecyclerView rvWaitingList = view.findViewById(R.id.rv_waiting_list);

            rvWaitingList.setNestedScrollingEnabled(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            rvWaitingList.setLayoutManager(linearLayoutManager);
            ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter);
            scaleAdapter.setFirstOnly(false);
            rvWaitingList.setAdapter(scaleAdapter);

        }
    }

    private void addIntoList() {

        mAllDayClasses.clear();

        mUserInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);

        List<AllDayClassModel> classes = addClassFromToday(MApplication.getInstance().getAllClasses());

        int idAllClasses;
        if (mUserInfo != null) {
            int[] idWaiting = mUserInfo.getClassWaitingList();
            for (int i = 0; i < mUserInfo.getClassWaitingList().length; i++) {
                for (int j = 0; j < classes.size(); j++) {
                    idAllClasses = classes.get(j).getId();
                    if (idWaiting[i] == idAllClasses) {
                        mAllDayClasses.add(classes.get(j));
                    }
                }
            }

            //проверяем есть ли у нас классы которые уже зарезервированы, если да то делаем запрос на их удаление
            for (int i = 0; i < mAllDayClasses.size(); i++) {
                for (int j = 0; j < mUserInfo.getUserReservations().size(); j++) {
                    if (mAllDayClasses.get(i).getId() == mUserInfo.getUserReservations().get(j).getDayClassId()) {
                        if (Connection.isNetworkAvailable(getContext())) {
                            DayClassIdModel model = new DayClassIdModel();
                            model.setId(mAllDayClasses.get(j).getId());
                            Request.getInstance().leaveWaitingClass(model, this);
                            mSwipe.setRefreshing(true);
                        } else {
                            SimpleAlert.showNoConnection(getContext());
                            mSwipe.setRefreshing(false);
                        }
                    }
                }
            }

            adapter.setUserInfo(mAllDayClasses);
            adapter.notifyDataSetChanged();

            mSwipe.setRefreshing(false);
        }
    }

    //Вывод занятий от сегоднешнего дня
    private List<AllDayClassModel> addClassFromToday(List<AllDayClassModel> info) {
        List<AllDayClassModel> result = new ArrayList<>();

        final Calendar c = Calendar.getInstance();

        for (int i = 0; i < info.size(); i++) {
            if ((Integer.valueOf(info.get(i).getDate().substring(5, 7)) == c.get(Calendar.MONTH) + 1 &&
                    Integer.valueOf(info.get(i).getDate().substring(8, 10)) >= c.get(Calendar.DAY_OF_MONTH)) ||
                    Integer.valueOf(info.get(i).getDate().substring(5, 7)) > c.get(Calendar.MONTH) + 1 &&
                            Integer.valueOf(info.get(i).getDate().substring(0, 4)) >= c.get(Calendar.YEAR)) {

                result.add(info.get(i));
//                Log.e("addClassFromToday: ", info.get(i).getDate().substring(5, 7));
            }
        }
        return result;
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
    public void onUserInfoSuccess(UserInfoModel user) {
        mUserInfo = user;
        addIntoList();
    }

    @Override
    public void onUserInfoFailure(String message) {

    }

    @Override
    public void leaveWaitingListSuccess() {

    }

    @Override
    public void leaveWaitingListFailure() {

    }
}
