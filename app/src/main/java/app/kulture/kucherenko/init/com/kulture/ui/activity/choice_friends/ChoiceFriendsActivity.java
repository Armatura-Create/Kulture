package app.kulture.kucherenko.init.com.kulture.ui.activity.choice_friends;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.braintree.BraintreeRequestActivity;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivityChoiceFriendsBinding;
import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.ReserveStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToUnlimitedMemberStatus;
import app.kulture.kucherenko.init.com.kulture.models.ClassItemModel;
import app.kulture.kucherenko.init.com.kulture.models.ReserveSimpleClassModel;
import app.kulture.kucherenko.init.com.kulture.models.UpToUnlimitedMemberModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class ChoiceFriendsActivity extends BraintreeRequestActivity
        implements ChoiceFriendsContract.View, ILoadingStatus<List<ClassItemModel>>, ReserveStatus<String>, UpToMemberStatus {

    private static final int MAX_FRIENDS = 2;
    private ActivityChoiceFriendsBinding binding;
    private ChoiceFriendsPresenter presenter;
    private List<ReserveSimpleClassModel> models;

    private int pricePosition;
    private int priceId;
    private int countTicket;
    private int memberPrice;
    private boolean isWithMembership;
    private String[] packsPrice;
    private UserInfoModel userInfo;

    private RecyclerAdapterChoiceFriends adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_friends);

        initAll();
        addToModels(userInfo.getId(), classesModel.getId());
        fillAlertData();
        initRecycler();
        initListeners();
    }

    private void initListeners() {
        //установка слушателя на кнопку назад
        binding.toolbarFriends.setNavigationOnClickListener(view -> onBackPressed());

        //выполнение логики по нажатию на кнопку резервации
        binding.btContinueChoiceFriends.setOnClickListener(
                view -> presenter.onClickContinue(adapter.getmReserveClassModels()));

    }

    //установка recycler выбора друзей
    private void initRecycler() {
        binding.recyclerViewClasses.setNestedScrollingEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewClasses.setLayoutManager(linearLayoutManager);
        binding.recyclerViewClasses.setAdapter(adapter =
                new RecyclerAdapterChoiceFriends(userInfo,
                        MAX_FRIENDS, getClassesModel(), models));

        binding.recyclerViewClasses.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    //заполнение прайса для алерта
    private void fillAlertData() {
        for (int i = 0; i < classesModel.getClasses().getPrices().size(); i++) {
            packsPrice[i] = classesModel.getClasses().getPrices().get(i).getCount() + "  -  " +
                    classesModel.getClasses().getPrices().get(i).getPrice() + " S$";
        }
    }

    private void addToModels(int user_id, int day_class_id) {
        ReserveSimpleClassModel myModel = new ReserveSimpleClassModel();
        myModel.setUserId(user_id);
        myModel.setDayClassId(day_class_id);
        models.add(myModel);
    }

    private void initAll() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choice_friends);

        memberPrice = getIntent().getIntExtra("memberPrice", 0);
        presenter = new ChoiceFriendsPresenter(this);
        isWithMembership = getIntent().getBooleanExtra("isWithMember", false);

        pricePosition = getIntent().getIntExtra("pricePosition", -1);
        userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);
        models = new ArrayList<>();

        if (pricePosition >= 0)
            priceId = classesModel.getClasses().getPrices().get(pricePosition).getId();

        packsPrice = new String[classesModel.getClasses().getPrices().size()];

        binding.tvCountPackage.setText(String.valueOf(getIntent().getIntExtra("countTicket", 0)));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void reservePositions() {
        if (Connection.isNetworkAvailable(getContext())) {
            //запрос на проверку купленых занятий
            models = adapter.getmReserveClassModels();
            Request.getInstance().getAllItems(this);
        } else SimpleAlert.showNoConnection(getContext());
    }

    @Override
    protected Map<String, String> getReserveData() {
        Map<String, String> data = new HashMap<>();

        data.put("data", new Gson().toJson(adapter.getmReserveClassModels()));

        return data;
    }

    @Override
    protected ReserveStatus<String> getLoading() {
        return this;
    }

    @Override
    protected void goToReserveList(int code) {
        setResult(RESULT_OK, null);
        finish();
    }

    @Override
    protected AllDayClassModel getClassesModel() {
        return getIntent().getBundleExtra("classModel").getParcelable("classModel");
    }

    @Override
    protected UpToMemberStatus getMemberStatus() {
        return this;
    }

    @Override
    protected UpToUnlimitedMemberStatus getUnlimitedMemberStatus() {
        return null;
    }

    @Override
    protected int getPriceId() {

        return priceId;
    }

    @Override
    public void onSuccess(List<ClassItemModel> info) {

        countTicket = getTicketCount(info);

        if (countTicket >= models.size()) {
            reserveClassRequest(getReserveData());
            return;
        }

        if (pricePosition == -1 ||
                classesModel.getClasses().getPrices().get(pricePosition).getCount() < models.size()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose one and class packs")
                    .setCancelable(false)
                    .setSingleChoiceItems(packsPrice, -1,
                            (dialog, item) -> pricePosition = item)
                    .setNegativeButton("Cancel",
                            (dialog, id) -> dialog.cancel())

                    .setPositiveButton("OK", (dialog, i) -> {
                        priceId = classesModel.getClasses().getPrices().get(pricePosition).getId();
                        makeRequest(isWithMembership, pricePosition, memberPrice);
                        dialog.cancel();
                    });

            builder.show();
        } else {
            makeRequest(isWithMembership, pricePosition, memberPrice);
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onReserveSuccess(String info) {
        goToReserveList(RESULT_OK);
    }

    @Override
    public void onReserveFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateMemberSuccess(String info) {
        UserInfoModel userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);
        userInfo.setIsMember(true);
        MSharedPreferences.getInstance().setUserInfo(new Gson().toJson(userInfo));
    }

    @Override
    public void onUpdateMemberFailure(String message) {

    }
}
