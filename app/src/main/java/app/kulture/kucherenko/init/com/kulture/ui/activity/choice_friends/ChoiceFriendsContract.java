package app.kulture.kucherenko.init.com.kulture.ui.activity.choice_friends;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.models.ReserveSimpleClassModel;

/**
 * Created by user on 20.11.17.
 */

public interface ChoiceFriendsContract {

    interface View {
        Context getContext();

        void reservePositions();
    }

    interface EventListener{

        ArrayList<String> initArrayFriends();

        void onClickContinue(List<ReserveSimpleClassModel> model);
    }
}
