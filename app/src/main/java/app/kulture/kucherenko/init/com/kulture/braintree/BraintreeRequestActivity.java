package app.kulture.kucherenko.init.com.kulture.braintree;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.braintreepayments.api.BraintreePaymentActivity;
import com.braintreepayments.api.PaymentRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;

import java.util.List;
import java.util.Map;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.interfaces.PaymentClassWithMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.PaymentLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.ReserveStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToMemberStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UpToUnlimitedMemberStatus;
import app.kulture.kucherenko.init.com.kulture.models.ClassItemModel;
import app.kulture.kucherenko.init.com.kulture.models.PaymentModel;
import app.kulture.kucherenko.init.com.kulture.models.UpToMemberModel;
import app.kulture.kucherenko.init.com.kulture.models.UpToUnlimitedMemberModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;

/**
 * Created by_ slava on 21.11.17.
 */

public abstract class BraintreeRequestActivity extends AppCompatActivity implements PaymentLoadingStatus<String>, PaymentClassWithMemberStatus<String> {
    protected static final int BRAINTREE_CODE = Menu.FIRST;
    protected static final int RESERVE_CODE = 2;
    protected static final int MEMBER_CODE = 3;
    protected static final int DOUBLE_REQUEST_CODE = 4;
    protected static final int MEMBER_UNLIMITED_CODE = 5;

    private String clientToken;
    protected AllDayClassModel classesModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classesModel = getClassesModel();
        Request.getInstance().getBraintreeToken(MSharedPreferences.getInstance().getKey(), this);
    }

    protected abstract Map<String, String> getReserveData();

    protected abstract ReserveStatus<String> getLoading();

    protected abstract void goToReserveList(int code);

    protected abstract AllDayClassModel getClassesModel();

    protected abstract UpToMemberStatus getMemberStatus();

    protected abstract UpToUnlimitedMemberStatus getUnlimitedMemberStatus();

    protected abstract int getPriceId();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BRAINTREE_CODE || requestCode == DOUBLE_REQUEST_CODE) {
            if (resultCode == BraintreePaymentActivity.RESULT_OK) {
                PaymentMethodNonce paymentMethodNonce = data.getParcelableExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);

                PaymentModel paymentModel = new PaymentModel();
                paymentModel.setPaymentMethodNonce(paymentMethodNonce.getNonce());
                paymentModel.setPriceId(getPriceId());
                Log.e("nonce", paymentModel.getPaymentMethodNonce());
                Log.e("paymentModel", paymentModel.toString());

                if (requestCode == BRAINTREE_CODE)
                    Request.getInstance().createTransaction(paymentModel, this);
                else
                    Request.getInstance().createTransactionWithMembership(paymentModel, this);
            }
        }

        if (requestCode == MEMBER_CODE) {
            if (resultCode == BraintreePaymentActivity.RESULT_OK) {
                PaymentMethodNonce paymentMethodNonce = data.getParcelableExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);

                UpToMemberModel memberModel = new UpToMemberModel();
                memberModel.setMemberNonce(paymentMethodNonce.getNonce());

                Request.getInstance().upToMember(memberModel, getMemberStatus());
            }
        }

        if (requestCode == MEMBER_UNLIMITED_CODE) {
            if (resultCode == BraintreePaymentActivity.RESULT_OK) {
                PaymentMethodNonce paymentMethodNonce = data.getParcelableExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);

                UpToUnlimitedMemberModel unlimitedMemberModel = new UpToUnlimitedMemberModel();
                unlimitedMemberModel.setUnlimitedMemberNonce(paymentMethodNonce.getNonce());

                Request.getInstance().upToUnlimitedMember(unlimitedMemberModel, getUnlimitedMemberStatus());
            }
        }

        // если оплата была не в DetailClass
        if (requestCode == RESERVE_CODE) {
            if (resultCode == BraintreePaymentActivity.RESULT_OK) {
                goToReserveList(RESULT_OK);
            }
        }
    }

    //запрос на резервацию
    protected void reserveClassRequest(Map<String, String> data) {
        Request.getInstance().reserveClass(data, getLoading());
    }

    // оплата
    public void makePaymentRequest(int price, int code) {
        @SuppressLint("DefaultLocale") String priceAmount = String.format("$%d.00", price);

        PaymentRequest paymentRequest = new PaymentRequest()
                .clientToken(clientToken)
                .amount(priceAmount)
                .primaryDescription("Awesome payment")
                .secondaryDescription("Using the Client SDK")
                .submitButtonText("Pay");

        this.startActivityForResult(paymentRequest.getIntent(this), code);
    }

    public int getTicketCount(List<ClassItemModel> info) {
        int countTicket = 0;
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
        return countTicket;
    }

    protected void makeRequest(boolean isWithMembership, int pricePosition, int memberPrice) {
        if (!isWithMembership) {
            makePaymentRequest(classesModel.getClasses().getPrices().get(pricePosition).getPrice(),
                    BRAINTREE_CODE);
        } else {
            makePaymentRequest(
                    classesModel.getClasses().getPrices().get(pricePosition).getPrice() + memberPrice,
                    DOUBLE_REQUEST_CODE);
        }
    }

    @Override
    public void onBTokenSuccess(String token) {
        clientToken = token;
    }

    @Override
    public void onBTokenFailure(String message) {
        Log.e("BTokenFailure", message);
    }

    @Override
    public void onPaymentSuccess(String code) {
        Log.e("Payment", "OK");


        Map<String, String> data = getReserveData();
        Log.e("dataValue", data.get("data"));

        reserveClassRequest(data);
    }

    @Override
    public void onPaymentFailure(String message) {
        Log.e("DetailsClassPaymentFail", message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessWithMembership(String code) {
        Map<String, String> data = getReserveData();
        Log.e("dataValue", data.get("data"));

        reserveClassRequest(data);
    }

    @Override
    public void onFailureWithMembership(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
