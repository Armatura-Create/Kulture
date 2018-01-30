package app.kulture.kucherenko.init.com.kulture.ui.activity.choice_friends;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.MacSpi;

import app.kulture.kucherenko.init.com.kulture.models.ReserveSimpleClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;

/**
 * Created by user on 20.11.17.
 */

public class ChoiceFriendsPresenter implements ChoiceFriendsContract.EventListener {

    private ChoiceFriendsContract.View view;
    private UserInfoModel userInfo;

    public ChoiceFriendsPresenter(ChoiceFriendsContract.View view) {
        userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);
        this.view = view;
    }


    @Override
    public ArrayList<String> initArrayFriends() {
        ArrayList<String> friends = new ArrayList<>();
        if (userInfo.getFriends().size() != 0) {
            for (int i = 0; i < userInfo.getFriends().size(); i++) {
                friends.add(String.valueOf(userInfo.getFriends().get(i).getFirstName()));
            }
        }
        return friends;
    }

    @Override
    public void onClickContinue(List<ReserveSimpleClassModel> model) {
        view.reservePositions();
    }
}

