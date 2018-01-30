package app.kulture.kucherenko.init.com.kulture.ui.activity.choice_friends;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.models.ReserveSimpleClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;


public class RecyclerAdapterChoiceFriends extends RecyclerView.Adapter<RecyclerAdapterChoiceFriends.ViewHolder> {
    //Предоставляет ссылку на представления, используемые в RecyclerView
    private UserInfoModel userInfo;
    private int maxFriends;
    private List<ReserveSimpleClassModel> mReserveClassModels;
    private AllDayClassModel mClasses;
    private boolean[] checked;

    public List<ReserveSimpleClassModel> getmReserveClassModels() {
        return mReserveClassModels;
    }

    private int count = 0;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_choice_friends, parent, false);

        return new ViewHolder(linearLayout);
    }

    RecyclerAdapterChoiceFriends(UserInfoModel userInfo, int maxFriends, AllDayClassModel classesModel, List<ReserveSimpleClassModel> models) {
        this.userInfo = userInfo;
        this.maxFriends = maxFriends;
        mClasses = classesModel;
        mReserveClassModels = models;
        checked = new boolean[userInfo.getFriends().size()];
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
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final LinearLayout linearLayout = holder.mLinearLayout;

        ImageView ivCardItem = linearLayout.findViewById(R.id.iv_recycler_user_image);
        TextView tvFirstName = linearLayout.findViewById(R.id.textView_recycler_first_name);
        TextView tvSecondName = linearLayout.findViewById(R.id.textView_recycler_second_name);
        CheckBox sw_button = linearLayout.findViewById(R.id.sw_choice_friends);

        if (userInfo.getFriends().get(position).getImage() != null) {
            Uri uri = Uri.parse(userInfo.getFriends().get(position).getImage());
            Glide.with(linearLayout.getContext()) //передаем контекст приложения
                    .load(uri)
                    .placeholder(R.drawable.test_image)
                    .into(ivCardItem); //ссылка на ImageView
        }
        if (userInfo.getFriends().get(position).getFirstName().length() > 0) {
            tvFirstName.setText(userInfo.getFriends().get(position).getFirstName());
            tvSecondName.setText(userInfo.getFriends().get(position).getLastName());
        } else {
            tvFirstName.setText(userInfo.getFriends().get(position).getUsername());
            tvSecondName.setVisibility(View.GONE);
        }

        sw_button.setChecked(checked[position]);

        sw_button.setOnClickListener(view -> {
            ReserveSimpleClassModel model = new ReserveSimpleClassModel();
            if (!checked[position] && count < maxFriends) {
                count++;
                checked[position] = true;

                //заполнение id друга
                model.setUserId(userInfo.getFriends().get(position).getId());
                model.setDayClassId(mClasses.getId());
                mReserveClassModels.add(model);
                for (int i = 0; i < mClasses.getClassReservations().size(); i++) {
                    if (mClasses.getClassReservations().get(i).getUser().getId() ==
                            userInfo.getFriends().get(position).getId()) {
                        mReserveClassModels.remove(count);
                        sw_button.setChecked(false);
                        count--;
                        checked[position] = false;
                        Toast.makeText(view.getContext(), "This friend has already reserved a place", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            } else if (checked[position]) {
                mReserveClassModels.remove(count);
                count--;
                checked[position] = false;
            } else {
                Toast.makeText(view.getContext(), "You can select a maximum of " + maxFriends + " friend", Toast.LENGTH_SHORT).show();
                sw_button.setChecked(false);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userInfo.getFriends() == null ? 0 : userInfo.getFriends().size();
    }
}
