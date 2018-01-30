package app.kulture.kucherenko.init.com.kulture.ui.activity.payments;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.gson.Gson;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.models.ApplicationData;
import app.kulture.kucherenko.init.com.kulture.models.payments.PaymentsHistoryModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;

public class RecyclerAdapterPaymentsHistory extends RecyclerView.Adapter<RecyclerAdapterPaymentsHistory.ViewHolder> {

    private List<PaymentsHistoryModel> mHistoryModels;
    private ApplicationData applicationData;
    private PaymentsHistoryActivity paymentsHistoryActivity;

    public void setHistoryModels(List<PaymentsHistoryModel> historyModels, PaymentsHistoryActivity paymentsHistoryActivity) {
        this.paymentsHistoryActivity = paymentsHistoryActivity;
        mHistoryModels = historyModels;
        applicationData = new Gson().fromJson(MSharedPreferences.getInstance().getAppData(), ApplicationData.class);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_payments_history, parent, false);
        return new ViewHolder(linearLayout);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mHistoryModels != null) {

            final LinearLayout linearLayout = holder.mLinearLayout;

            TextView tvDateCreated = linearLayout.findViewById(R.id.tv_item_created);
            TextView tvPrice = linearLayout.findViewById(R.id.tv_item_price);

            tvPrice.setText(price(position));
            tvDateCreated.setText(mHistoryModels.get(position).getCreateDate().substring(0, 10));

            MaterialRippleLayout recyclerLinear = linearLayout.findViewById(R.id.ripper_pay_history);

            recyclerLinear.setOnClickListener(v -> {
                getDetailHistory(position, v);
            });
        }
    }

    private void getDetailHistory(int position, View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Detail Payment")
                .setMessage(stringBuilder(position))
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, i) -> {
                    dialog.cancel();
                });
        builder.show();
    }

    private String price(int p) {
        if (mHistoryModels.get(p).getPaymentType().equals(paymentsHistoryActivity.getResources().getString(R.string.payment_type_vip)))
            return String.format("S$ %d", applicationData.getVipPrice());
        else if (mHistoryModels.get(p).getPaymentType().equals(paymentsHistoryActivity.getResources().getString(R.string.payment_type_membership)))
            return String.format("S$ %d", applicationData.getMemberPrice());
        else
            return String.format("S$ %d", mHistoryModels.get(p).getPrice().getPrice());
    }

    private String stringBuilder(int p) {
        String result;

        result = "Payment date - " + mHistoryModels.get(p).getCreateDate().substring(0, 10) + "\n";
        result += "Product - ";
        if (mHistoryModels.get(p).getPaymentType().equals(paymentsHistoryActivity.getResources().getString(R.string.payment_type_vip)))
            result += "Unlimited member\n";
        else if (mHistoryModels.get(p).getPaymentType().equals(paymentsHistoryActivity.getResources().getString(R.string.payment_type_membership)))
            result += "Member\n";
        else
            result += mHistoryModels.get(p).getPrice().getClassesPrices().get(0).getName() + "\n";

        if (mHistoryModels.get(p).getPrice().getClassesPrices().size() > 0)
            result += "Count credit - " + mHistoryModels.get(p).getPrice().getCount() + "\n";

        if (mHistoryModels.get(p).getPaymentType().equals(paymentsHistoryActivity.getResources().getString(R.string.payment_type_vip)))
            result += "Price - " + String.format("S$ %d", applicationData.getVipPrice());
        else if (mHistoryModels.get(p).getPaymentType().equals(paymentsHistoryActivity.getResources().getString(R.string.payment_type_membership)))
            result += "Price - " + String.format("S$ %d", applicationData.getMemberPrice());
        else
            result += "Price - " + String.format("S$ %d", mHistoryModels.get(p).getPrice().getPrice());

        return result;
    }


    @Override
    public int getItemCount() {
        return mHistoryModels == null ? 0 : mHistoryModels.size();
    }
}
