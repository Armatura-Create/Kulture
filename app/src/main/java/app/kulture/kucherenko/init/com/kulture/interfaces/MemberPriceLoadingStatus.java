package app.kulture.kucherenko.init.com.kulture.interfaces;

/**
 * Created by slava on 28.11.17.
 */

public interface MemberPriceLoadingStatus {

    void onMemberPriceSuccess(int price);

    void onMemberPriceFailure(String message);
}
