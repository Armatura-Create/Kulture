package app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.braintree.BraintreeRequestActivity;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.FragmentBoundsPoundsBinding;
import app.kulture.kucherenko.init.com.kulture.databinding.ItemBinding;
import app.kulture.kucherenko.init.com.kulture.databinding.Row5Binding;
import app.kulture.kucherenko.init.com.kulture.databinding.Row6Binding;
import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.ReserveStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToUnlimitedMemberStatus;
import app.kulture.kucherenko.init.com.kulture.models.ClassItemModel;
import app.kulture.kucherenko.init.com.kulture.models.ReserveClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class BounceActivity extends BraintreeRequestActivity implements View.OnClickListener, ILoadingStatus<List<ClassItemModel>>, ReserveStatus<String>, UpToMemberStatus {

    private String[] packsPrice;

    //    BoundsContentBinding binding;
    FragmentBoundsPoundsBinding binding;
    List<ItemBinding> items;
    List<String> places;
    int counter = 0;
    private int countTicket = 0;
    private AllDayClassModel modelClasses;
    private List<ReserveClassModel> mReserveClassModels;
    private int mClassesId;
    private UserInfoModel userInfo;
    private int priceId;
    private int pricePosition;
    private int memberPrice;
    private boolean isWithMembership;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bounds_pounds);

        initAll();
        fillAlertData();
        setListeners();

        resolve1();
        changeColorPosition();
    }

    private void setListeners() {
        binding.toolbarTramplines.setNavigationOnClickListener(view -> onBackPressed());
    }

    //заполнение прайса для алерта
    private void fillAlertData() {
        for (int i = 0; i < classesModel.getClasses().getPrices().size(); i++) {
            packsPrice[i] = classesModel.getClasses().getPrices().get(i).getCount() + "  -  " +
                    classesModel.getClasses().getPrices().get(i).getPrice() + " S$";
        }
    }

    private void initAll() {
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_bounds_pounds);
        modelClasses = getIntent().getBundleExtra("classModel").getParcelable("classModel");

        userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);
        memberPrice = getIntent().getIntExtra("memberPrice", 0);
        packsPrice = new String[classesModel.getClasses().getPrices().size()];
        isWithMembership = getIntent().getBooleanExtra("isWithMember", false);

        pricePosition = getIntent().getIntExtra("pricePosition", -1);

        if (pricePosition >= 0)
            priceId = classesModel.getClasses().getPrices().get(pricePosition).getId();

        if (modelClasses != null) {
            mClassesId = modelClasses.getId();
        }
    }

    @Override
    public void onClick(View view) {
        final boolean[] check = {true};
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            String textItem = textView.getText().toString();

            if (counter == 0 && !places.contains(textItem)) {
                textView.setTextColor(ContextCompat.getColor(this, R.color.green));
                places.add(textItem);
                counter++;
                binding.confirm.setText(getString(R.string.book_now));

                addToModel(mClassesId, userInfo.getId(), Integer.valueOf(textItem));

            } else if ((counter > 0 && counter < 3) && !places.contains(textItem)) {

                counter++;
                final int[] itemFriend = {0};

                if (userInfo.isMember() || isWithMembership) {

                    if (userInfo.getFriends().size() != 0) {

                        final AlertDialog.Builder[] builder = {new AlertDialog.Builder(this)};
                        builder[0].setTitle("Choice Friends")
                                .setCancelable(false)
                                .setNegativeButton("Cancel",
                                        (dialog, id) -> {
                                            counter--;
                                            dialog.cancel();
                                        })

                                .setPositiveButton("OK", (dialog, i) -> {

                                    textView.setTextColor(ContextCompat.getColor(BounceActivity.this, R.color.green));
                                    places.add(textItem);

                                    for (int k = 0; k < modelClasses.getClassReservations().size(); k++) {
                                        if (modelClasses.getClassReservations().get(k).getUser().getId() ==
                                                userInfo.getFriends().get(itemFriend[0]).getId()) {
                                            counter--;
                                            places.remove(textItem);
                                            textView.setTextColor(Color.WHITE);
                                            Toast.makeText(view.getContext(), "This friend has already reserved", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }

                                    for (int j = 0; j < mReserveClassModels.size(); j++) {
                                        if (userInfo.getFriends().get(itemFriend[0]).getId() == mReserveClassModels.get(j).getUserId()) {
                                            check[0] = false;
                                        }
                                    }

                                    if (check[0]) {
                                        addToModel(mClassesId, userInfo.getFriends().get(itemFriend[0]).getId(), Integer.valueOf(textItem));

                                    } else {
                                        Toast.makeText(this, "You have already selected this friend. "
                                                + mReserveClassModels.get(1).getPosition() + " position", Toast.LENGTH_LONG).show();
                                        textView.setTextColor(Color.WHITE);
                                        counter--;
                                        places.remove(textItem);
                                    }
                                })

                                .setSingleChoiceItems(addFriendInList(userInfo.getFriends().size()), -1,
                                        (dialog, item) -> itemFriend[0] = item);
                        builder[0].show();
                    } else {
                        counter--;
                        makeSimpleAlert("Sorry", "You not have friends");
                    }
                } else {
                    counter--;
                    makeSimpleAlert("You not member!", "You can not reserve friends");
                }

            } else if (places.contains(textItem)) {
                places.remove(textItem);
                textView.setTextColor(Color.WHITE);
                counter--;

                for (int i = 0; i < mReserveClassModels.size(); i++) {
                    if (String.valueOf(mReserveClassModels.get(i).getPosition()).equals(textItem)) {
                        mReserveClassModels.remove(i);
                    }
                }
            }
        }
    }

    private void addToModel(int dayClassId, int userId, int position) {
        ReserveClassModel model = new ReserveClassModel();
        model.setDayClassId(dayClassId);
        model.setUserId(userId);
        model.setPosition(position);

        mReserveClassModels.add(model);
    }

    private void makeSimpleAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, i) -> dialog.cancel());
        builder.show();
    }

    private String[] addFriendInList(int size) {
        String result[] = new String[size];

        for (int i = 0; i < result.length; i++) {
            if (userInfo.getFriends().get(i).getFirstName().length() != 0) {
                result[i] = userInfo.getFriends().get(i).getFirstName();
            } else {
                result[i] = userInfo.getFriends().get(i).getUsername();
            }

        }

        return result;
    }

    //заполняет занятые позиции
    private void changeColorPosition() {
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < classesModel.getClassReservations().size(); j++) {
                if (Integer.valueOf(String.valueOf(items.get(i).index.getText())) == (classesModel.getClassReservations().get(j).getPosition())) {
                    items.get(i).imageTrampoline.setImageDrawable(getResources().getDrawable(R.drawable.trampoline_booking));
                    items.get(i).index.setTextColor(ContextCompat.getColor(this, R.color.orange_special));
                    items.get(i).index.setClickable(false);
                }
            }
        }
    }

    void resolve1() {
        mReserveClassModels = new ArrayList<>();
        List<Row6Binding> rows6 = new ArrayList<>();
        List<Row5Binding> rows5 = new ArrayList<>();

        rows5.add(binding.grid.row1);
        rows5.add(binding.grid.row3);
        rows5.add(binding.grid.row4);
        rows5.add(binding.grid.row6);

        rows6.add(binding.grid.row2);
        rows6.add(binding.grid.row5);

        items = new ArrayList<>();
        places = new ArrayList<>();

        int a = 0;
        for (int i = 0; i < rows5.size() + rows6.size(); i++) {
            if (i == 0 || i == 2 || i == 3 || i == 5) {
                switch (i) {
                    case 0:
                        a = 0;
                        break;
                    case 2:
                        a = 1;
                        break;
                    case 3:
                        a = 2;
                        break;
                    case 5:
                        a = 3;
                        break;
                }
                items.add(rows5.get(a).item1);
                items.add(rows5.get(a).item2);
                items.add(rows5.get(a).item3);
                items.add(rows5.get(a).item4);
                items.add(rows5.get(a).item5);
            }
            if (i == 1 || i == 4) {
                switch (i) {
                    case 1:
                        a = 0;
                        break;
                    case 4:
                        a = 1;
                        break;
                }
                items.add(rows6.get(a).item1);
                items.add(rows6.get(a).item2);
                items.add(rows6.get(a).item3);
                items.add(rows6.get(a).item4);
                items.add(rows6.get(a).item5);
                items.add(rows6.get(a).item6);
            }
        }


        for (int i = 0; i < items.size(); i++) {
            items.get(i).index.setTextColor(Color.WHITE);
            items.get(i).index.setText(String.valueOf(i + 1));
            items.get(i).index.setOnClickListener(this);
        }

        binding.confirm.setOnClickListener(v -> {
            if (counter > 0) {
                Request.getInstance().getAllItems(this);
            } else Toast.makeText(this, "Please select position", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected Map<String, String> getReserveData() {
        Map<String, String> data = new HashMap<>();

        data.put("data", new Gson().toJson(mReserveClassModels));

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

        if (countTicket >= mReserveClassModels.size()) {
            reserveClassRequest(getReserveData());
            return;
        }

        if (pricePosition == -1 ||
                classesModel.getClasses().getPrices().get(pricePosition).getCount() < mReserveClassModels.size()) {
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
        SimpleAlert.show(this, "Error", message, "Ok");
    }

    @Override
    public void onReserveSuccess(String info) {
        goToReserveList(RESULT_OK);
    }

    @Override
    public void onReserveFailure(String message) {

    }

    @Override
    public void onUpdateMemberSuccess(String info) {
        userInfo.setIsMember(true);
        MSharedPreferences.getInstance().setUserInfo(new Gson().toJson(userInfo));
    }

    @Override
    public void onUpdateMemberFailure(String message) {

    }
}