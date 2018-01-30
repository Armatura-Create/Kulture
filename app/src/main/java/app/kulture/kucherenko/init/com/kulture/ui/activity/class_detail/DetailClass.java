package app.kulture.kucherenko.init.com.kulture.ui.activity.class_detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.braintree.BraintreeRequestActivity;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivityDetailClassBinding;
import app.kulture.kucherenko.init.com.kulture.interfaces.CancelClassStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.JoinWaitingListStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.LeaveWaitingListStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.ReserveStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToUnlimitedMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.CancelReserveModel;
import app.kulture.kucherenko.init.com.kulture.models.ClassItemModel;
import app.kulture.kucherenko.init.com.kulture.models.DayClassIdModel;
import app.kulture.kucherenko.init.com.kulture.models.ReserveSimpleClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.Price;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.activity.choice_friends.ChoiceFriendsActivity;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr.BounceActivity;
import app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr.ClassesFragment;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.ItemClickSupport;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

import static app.kulture.kucherenko.init.com.kulture.utils.FormatDate.formatDate;

public class DetailClass extends BraintreeRequestActivity
        implements DetailClassContract.View,
        ReserveStatus<String>, ILoadingStatus<List<ClassItemModel>>, UpToMemberStatus,
        CancelClassStatus, UserInfoLoadingStatus, JoinWaitingListStatus, LeaveWaitingListStatus {


    private static final int PRICE_POSITION_FIRST = 0;
    private static final int PRICE_POSITION_SECOND = 1;
    private static final int PRICE_POSITION_THIRD = 2;
    private static final int PRICE_POSITION_FOURTH = 3;

    private static final String DOLLAR = "S$ ";
    private static final int MEMBER_OK = -2;

    private List<String> imageURLs;
    private String TAG = "st18rai";
    private ActivityDetailClassBinding binding;
    private Price cur_price;
    private UserInfoModel userInfo;
    private int memberPrice;
    private int countTicket = 0;
    private int pricePosition;

    //брать из интента при открытии активити
    private Boolean mBoolean;
    private Boolean isReservedThisClass = false;
    private Boolean isJoinWaitingList = false;
    private int idReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_class);
        classesModel = getIntent().getBundleExtra("classModel").getParcelable("classModel");
        Log.e("DetailClass", String.format("ClassID = %d", classesModel.getId()));

        memberPrice = getIntent().getIntExtra("memberPrice", 0);
        imageURLs = new ArrayList<>();

        for (int i = 0; i < classesModel.getClasses().getClassPhotos().size(); i++) {
            imageURLs.add(classesModel.getClasses().getClassPhotos().get(i).getImage());
            Log.e(TAG, "detail classes photo URL: " + classesModel.getClasses().getClassPhotos().get(i).getImage());
        }

        mBoolean = classesModel != null && classesModel.getReservedPositionsCount() <= classesModel.getClasses().getMaxUsersCount();

        userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);

        Request.getInstance().getAllItems(this);

        cur_price = classesModel.getClasses().getPrices().get(0);

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onStart() {
        initAll();
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initAll();
        if(countTicket > 0){
            setGroupVisible(View.GONE);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goToReserveList(int code) {
        Toast.makeText(this, "you reserve class", Toast.LENGTH_LONG).show();
//        setResult(code, null);
        finish();
    }

    private void logicReserve() {
        Log.e("logicReserve: ", String.valueOf(classesModel.getId()));

        if (!ClassesFragment.isAvailableForStatusMember(classesModel)) {
            showSimpleAlert("Error", getString(R.string.you_are_not_unlimited));
            return;
        }

        if (classesModel.getClasses().getType().equals(getString(R.string.bounce_fit))) {

            startActivityForResult(
                    getActivityIntent(BounceActivity.class, false),
                    RESERVE_CODE);

            return;
        }

        if (userInfo.isIsMember()) {

            if (classesModel.getClasses().getType().equals(getString(R.string.pound_fit))) {

                startActivityForResult(
                        getActivityIntent(ChoiceFriendsActivity.class, false),
                        RESERVE_CODE);
            }

            if (classesModel.getClasses().getType().equals(getString(R.string.pound_dance)) || classesModel.getClasses().getType().equals(getString(R.string.pound_music))) {
                if (countTicket > 0) {
                    reserveClassRequest(getReserveData());
                } else {
                    makePaymentRequest(cur_price.getPrice(), BRAINTREE_CODE);
                }
            }
        } else {
            if (countTicket > 0) {
                reserveClassRequest(getReserveData());
            } else {
                makePaymentRequest(cur_price.getPrice(), BRAINTREE_CODE);
            }
        }
    }

    private Intent getActivityIntent(Class<? extends BraintreeRequestActivity> foToClass,
                                     boolean isWithMember) {

        Intent intent = new Intent(this, foToClass);

        intent.putExtra("classModel", getIntent().getBundleExtra("classModel"));
        intent.putExtra("memberPrice", memberPrice);
        intent.putExtra("pricePosition", pricePosition);
        intent.putExtra("countTicket", countTicket);
        intent.putExtra("isWithMember", isWithMember);

        return intent;
    }

    private void logicCancel() {
        Log.e("logicCancel: ", String.valueOf(classesModel.getId()));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel Reservation")
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setNegativeButton("Cancel",
                        (dialog, id) -> dialog.cancel())

                .setPositiveButton("OK", (dialog, i) -> {
                    CancelReserveModel model = new CancelReserveModel();
                    model.setReserveId(idReserve);
                    if (Connection.isNetworkAvailable(getContext())) {
                        Request.getInstance().cancelReserve(model, this);
                    } else {
                        SimpleAlert.showNoConnection(getContext());
                    }
                });

        builder.show();
    }

    private void logicJoinWaitingList() {
        DayClassIdModel model = new DayClassIdModel();
        model.setId(classesModel.getId());
        if (Connection.isNetworkAvailable(getContext())) {
            Request.getInstance().joinWaitingClass(model, this);
        } else {
            SimpleAlert.showNoConnection(getContext());
        }
    }

    private void logicLeave() {
        DayClassIdModel model = new DayClassIdModel();
        model.setId(classesModel.getId());
        if (Connection.isNetworkAvailable(getContext())) {
            Request.getInstance().leaveWaitingClass(model, this);
        } else {
            SimpleAlert.showNoConnection(getContext());
        }
    }

    private void initButton() {

        binding.detailClassLayout.cardViewDetailClass.group.single.setText(String.valueOf(classesModel.getClasses().getPrices().get(0).getCount()));
        binding.detailClassLayout.cardViewDetailClass.group.pr1.setText(DOLLAR + String.valueOf(classesModel.getClasses().getPrices().get(0).getPrice()));
        binding.detailClassLayout.cardViewDetailClass.group.single.setOnClickListener(v -> {
            cur_price = classesModel.getClasses().getPrices().get(0);
            pricePosition = PRICE_POSITION_FIRST;

        });

        if (classesModel.getClasses().getType().equals(getString(R.string.pound_dance))) {
            setGroupVisible(View.GONE);
        } else {
            setGroupVisible(View.VISIBLE);
            binding.detailClassLayout.cardViewDetailClass.group.fifteen.setOnClickListener(v -> {
                if (userInfo.isIsMember()) {
                    cur_price = classesModel.getClasses().getPrices().get(1);
                    pricePosition = PRICE_POSITION_SECOND;
                } else {
                    cur_price = classesModel.getClasses().getPrices().get(1);
                    pricePosition = PRICE_POSITION_SECOND;
                    showAlertNotMember();
                }
            });
            binding.detailClassLayout.cardViewDetailClass.group.thirty.setOnClickListener(v -> {
                if (userInfo.isIsMember()) {
                    cur_price = classesModel.getClasses().getPrices().get(2);
                    pricePosition = PRICE_POSITION_THIRD;
                } else {
                    cur_price = classesModel.getClasses().getPrices().get(2);
                    pricePosition = PRICE_POSITION_THIRD;
                    showAlertNotMember();
                }
            });
            binding.detailClassLayout.cardViewDetailClass.group.gr4.setOnClickListener(v -> {
                if (userInfo.isIsMember()) {
                    cur_price = classesModel.getClasses().getPrices().get(3);
                    pricePosition = PRICE_POSITION_FOURTH;
                } else {
                    cur_price = classesModel.getClasses().getPrices().get(3);
                    pricePosition = PRICE_POSITION_FOURTH;
                    showAlertNotMember();
                }
            });

            binding.detailClassLayout.cardViewDetailClass.group.fifteen.setText(String.valueOf(classesModel.getClasses().getPrices().get(1).getCount()));
            binding.detailClassLayout.cardViewDetailClass.group.pr2.setText(DOLLAR + String.valueOf(classesModel.getClasses().getPrices().get(1).getPrice()));

            binding.detailClassLayout.cardViewDetailClass.group.thirty.setText(String.valueOf(classesModel.getClasses().getPrices().get(2).getCount()));
            binding.detailClassLayout.cardViewDetailClass.group.pr3.setText(DOLLAR + String.valueOf(classesModel.getClasses().getPrices().get(2).getPrice()));

            binding.detailClassLayout.cardViewDetailClass.group.gr4.setText(String.valueOf(classesModel.getClasses().getPrices().get(3).getCount()));
            binding.detailClassLayout.cardViewDetailClass.group.pr4.setText(DOLLAR + String.valueOf(classesModel.getClasses().getPrices().get(3).getPrice()));
        }

        for (int i = 0; i < userInfo.getUserReservations().size(); i++) {
            if (userInfo.getUserReservations().get(i).getDayClassId() == classesModel.getId()) {
                isReservedThisClass = true;
                idReserve = userInfo.getUserReservations().get(i).getId();
            }
        }

        for (int i = 0; i < userInfo.getClassWaitingList().length; i++) {
            if (userInfo.getClassWaitingList()[i] == classesModel.getId()) {
                isJoinWaitingList = true;
            }
        }

        if (isReservedThisClass) {
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setText(getResources().getString(R.string.cancel));
            setGroupVisible(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.single.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.pr1.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setOnClickListener(view -> logicCancel());

        } else if (classesModel.getReservedPositionsCount() == classesModel.getClasses().getMaxUsersCount() && !isJoinWaitingList) {
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setText(getResources().getString(R.string.join_waiting_list));
            setGroupVisible(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.single.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.pr1.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setOnClickListener(view -> logicJoinWaitingList());

        } else if (classesModel.getReservedPositionsCount() < classesModel.getClasses().getMaxUsersCount()) {
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setText(getResources().getString(R.string.reserve));
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setOnClickListener(view -> logicReserve());

        } else if (isJoinWaitingList) {
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setText(getResources().getString(R.string.leave_waiting));
            setGroupVisible(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.single.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.pr1.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.buttonDetailClassReserveCard.setOnClickListener(view -> logicLeave());
        }

    }

    private void setGroupVisible(int visible) {
        binding.detailClassLayout.cardViewDetailClass.group.fifteen.setVisibility(visible);
        binding.detailClassLayout.cardViewDetailClass.group.thirty.setVisibility(visible);
        binding.detailClassLayout.cardViewDetailClass.group.gr4.setVisibility(visible);
        binding.detailClassLayout.cardViewDetailClass.group.pr2.setVisibility(visible);
        binding.detailClassLayout.cardViewDetailClass.group.pr3.setVisibility(visible);
        binding.detailClassLayout.cardViewDetailClass.group.pr4.setVisibility(visible);
    }

    private void showAlertNotMember() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You not member");
        builder.setMessage("To buy a package will be added once the amount for the member")
                .setCancelable(false)
                .setNegativeButton("Cancel",
                        (dialog, id) -> {
                            binding.detailClassLayout.cardViewDetailClass.group.single.setChecked(true);
                            dialog.cancel();
                        })
                .setPositiveButton("OK", (dialog, i) -> {

                    Intent intent;
                    if (classesModel.getClasses().getType().equals(getString(R.string.pound_fit))) {
                        intent = getActivityIntent(ChoiceFriendsActivity.class, true);
                    } else {
                        intent = getActivityIntent(BounceActivity.class, true);
                    }
                    startActivityForResult(intent, RESERVE_CODE);

                });
        builder.show();
    }

    private void initAll() {
        binding.detailClassLayout.cardViewDetailClass.group.single.toggle();
        binding.detailClassLayout.textViewDetailClassName.setText(classesModel.getClasses().getName());
        binding.detailClassLayout.textViewDetailClassTeacher.setText(classesModel.getClasses().getTeacher().getName());
        binding.detailClassLayout.cardViewDetailClass.textViewDetailClassStartDate.setText(formatDate(classesModel.getDate()));
        binding.detailClassLayout.textViewDetailClassCancellationDate.setText(calculateDeadline(classesModel.getDate()) + ", " + classesModel.getStartTime().substring(0, 5));

        binding.detailClassLayout.cardViewDetailClass.textViewDetailClassStartTime.setText(classesModel.getStartTime().substring(0, 5) + " - " + classesModel.getEndTime().substring(0, 5));
        binding.detailClassLayout.ratingBarDetailClass.setStar(classesModel.getClasses().getLevelOfDifficulty());
        binding.detailClassLayout.textViewDetailClassAbout.setText(classesModel.getClasses().getInfo());

        binding.detailClassLayout.recyclerViewDetailClasses.setNestedScrollingEnabled(false);
        binding.detailClassLayout.recyclerViewDetailClasses.setLayoutManager(new LinearLayoutManager(this));
        binding.detailClassLayout.recyclerViewDetailClasses.setAdapter(new DetailClassAdapter(imageURLs));

        ItemClickSupport.addTo(binding.detailClassLayout.recyclerViewDetailClasses).setOnItemClickListener((recyclerView1, position, v) -> {
            Intent intent = new Intent(getApplicationContext(), FullScreenImage.class);
            intent.putExtra("Image URL", imageURLs.get(position));
            startActivity(intent);

//            String url = getString(R.string.host) + imageURLs.get(position);
//            openInGallery(url);

        });

        // устанавлливаем айди на кнопки и в зависимотси откуда мы переходим на это активити устанавливаем текст и логику на них.
        initButton();

        // загрузка картинки вверху
        loadImage();
    }

    private void loadImage() {
        Uri uri = Uri.parse(getString(R.string.host) + classesModel.getClasses().getClassPhotos().get(0).getImage());
        Log.e(TAG, "uri: " + uri);
        Glide.with(getContext()) //передаем контекст приложения
                .load(uri)
                .fitCenter()
                .thumbnail(0.5f)
                .priority(Priority.IMMEDIATE)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(binding.detailClassLayout.imageViewDetailClassTop); //ссылка на ImageView
    }

    @Override
    public ReserveStatus<String> getLoading() {
        return this;
    }

    @Override
    public AllDayClassModel getClassesModel() {
        return classesModel;
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
    public int getPriceId() {
        return cur_price.getId();
    }

    @Override
    protected Map<String, String> getReserveData() {
        ReserveSimpleClassModel model = new ReserveSimpleClassModel();
        model.setDayClassId(classesModel.getId());
        model.setUserId(userInfo.getId());

        List<ReserveSimpleClassModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(model);

        Map<String, String> data = new HashMap<>();
        data.put("data", new Gson().toJson(modelArrayList));

        return data;
    }

    //резервация прошла успешно
    @Override
    public void onReserveSuccess(String info) {
        goToReserveList(RESULT_OK);
    }

    //резервация провалилась
    @Override
    public void onReserveFailure(String message) {
        //TODO error message
        SimpleAlert.show(getContext(), "Error", message, "Ok");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //результат проверки на наличие билетов этого класса у юзера
    @Override
    public void onSuccess(List<ClassItemModel> info) {
        if (classesModel.getClasses().getType().equals(getString(R.string.pound_fit)) ||
                classesModel.getClasses().getType().equals(getString(R.string.bounce_fit))) {
            for (ClassItemModel item : info) {
                if (item.getClasses().getType().equals(getString(R.string.pound_fit)) ||
                        item.getClasses().getType().equals(getString(R.string.bounce_fit))) {
                    countTicket += item.getCount();
                }
            }
        } else {
            for (ClassItemModel item : info) {
                if (item.getClasses().getType().equals(classesModel.getClasses().getType())) {
                    countTicket += item.getCount();
                }
            }
        }
        if (countTicket == 0) {
            binding.detailClassLayout.cardViewDetailClass.group.tvCredits.setVisibility(View.GONE);
        } else {
            binding.detailClassLayout.cardViewDetailClass.group.tvCredits.setText("Credits: " + countTicket);
//            binding.detailClassLayout.cardViewDetailClass.group.content.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.single.setVisibility(View.GONE);
            binding.detailClassLayout.cardViewDetailClass.group.pr1.setVisibility(View.GONE);
            setGroupVisible(View.GONE);

            pricePosition = -1;
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onUpdateMemberSuccess(String info) {
        userInfo.setIsMember(true);
        MSharedPreferences.getInstance().setUserInfo(new Gson().toJson(userInfo));
        Request.getInstance().getUserInfo(this);
//        goToReserveList(MEMBER_OK);
    }

    @Override
    public void onUpdateMemberFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessCanceled(String info) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onFailureCanceled(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String calculateDeadline(String date) {
        String cancellationDeadline = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            sdf.applyPattern("dd-MM-yyyy, E");
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, -1);  // number of days to minus
        cancellationDeadline = sdf.format(c.getTime());

        return cancellationDeadline;
    }

    @Override
    public void onUserInfoSuccess(UserInfoModel user) {
        goToReserveList(MEMBER_OK);
    }

    @Override
    public void onUserInfoFailure(String message) {

    }

    @Override
    public void joinWaitingListSuccess() {
        Toast.makeText(this, "Added in Waiting List", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void joinWaitingListFailure() {
        Toast.makeText(this, "Not added in Waiting List", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void leaveWaitingListSuccess() {
        Toast.makeText(this, "Deleted Waiting List", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void leaveWaitingListFailure() {
        Toast.makeText(this, "Not deleted Waiting List", Toast.LENGTH_SHORT).show();
    }

    private void showSimpleAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, i) -> dialog.cancel());
        builder.show();
    }

//    public void openInGallery(String url) {
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(uri, "image/*");
//        startActivity(intent);
//    }
}