package app.kulture.kucherenko.init.com.kulture.interfaces;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.models.payments.PaymentsHistoryModel;

/**
 * Created by alex on 09.11.17.
 */

public interface PaymentsHistory {

    void getData(List<PaymentsHistoryModel> info);

}
