package app.kulture.kucherenko.init.com.kulture.ui.activity.payments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.databinding.ActivityPaymentsHistoryBinding;
import app.kulture.kucherenko.init.com.kulture.interfaces.PaymentsHistory;
import app.kulture.kucherenko.init.com.kulture.models.payments.PaymentsHistoryModel;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.ItemClickSupport;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class PaymentsHistoryActivity extends AppCompatActivity implements PaymentsHistory, SwipeRefreshLayout.OnRefreshListener {

    private ActivityPaymentsHistoryBinding binding;

    private RecyclerAdapterPaymentsHistory adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_history);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payments_history);
        binding.swipePaymentsHistory.setOnRefreshListener(this);
        binding.swipePaymentsHistory.setRefreshing(true);

        if (Connection.isNetworkAvailable(this)) {
            Request.getInstance().getPaymentsHistory(this);
        } else {
            SimpleAlert.showNoConnection(this);
            binding.swipePaymentsHistory.setRefreshing(false);
        }

        RecyclerView recyclerView = findViewById(R.id.rv_payments_history);

        recyclerView.setNestedScrollingEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter = new RecyclerAdapterPaymentsHistory());
        scaleAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleAdapter);
        binding.toolbarPaymentsHistory.setNavigationOnClickListener(view -> onBackPressed());

    }

    @Override
    public void getData(List<PaymentsHistoryModel> info) {
        adapter.setHistoryModels(info, this);
        adapter.notifyDataSetChanged();
        binding.swipePaymentsHistory.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (Connection.isNetworkAvailable(this)) {
            Request.getInstance().getPaymentsHistory(this);
        } else {
            SimpleAlert.showNoConnection(this);
            binding.swipePaymentsHistory.setRefreshing(false);
        }
    }
}
