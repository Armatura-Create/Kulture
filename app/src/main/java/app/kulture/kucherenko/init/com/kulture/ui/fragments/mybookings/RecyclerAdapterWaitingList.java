package app.kulture.kucherenko.init.com.kulture.ui.fragments.mybookings;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.firebase.KultureNotificationManager;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;

import static app.kulture.kucherenko.init.com.kulture.utils.FormatDate.formatDate;

public class RecyclerAdapterWaitingList extends RecyclerView.Adapter<RecyclerAdapterWaitingList.ViewHolder>{
    //Предоставляет ссылку на представления, используемые в RecyclerView
    private List<AllDayClassModel> mClassWaiting;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }

    void setUserInfo(List<AllDayClassModel> userInfo) {
        mClassWaiting = userInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_my_bookings_waiting_list, parent, false);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final LinearLayout linearLayout = holder.mLinearLayout;

        TextView tvButton = linearLayout.findViewById(R.id.tv_button_detail_waiting);

        ImageView ivCardItem = linearLayout.findViewById(R.id.iv_card_item);
        TextView tvTrainerName = linearLayout.findViewById(R.id.tv_item_trainer_name);
        TextView tvDescription = linearLayout.findViewById(R.id.tv_item_description);
        TextView tvDay = linearLayout.findViewById(R.id.tv_item_day);
        TextView tvTimeStart = linearLayout.findViewById(R.id.tv_item_time_start);
        TextView tvTimeEnd = linearLayout.findViewById(R.id.tv_item_time_end);

        if (mClassWaiting.get(position).getClasses().getClassPhotos().size() != 0) {
            Uri uri = Uri.parse("http://174.138.54.52:8889" +
                    mClassWaiting.get(position).getClasses().getClassPhotos().get(0).getImage());
            Glide.with(linearLayout.getContext()) //передаем контекст приложения
                    .load(uri)
                    .fitCenter()
                    .thumbnail(0.5f)
                    .priority(Priority.IMMEDIATE)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(ivCardItem); //ссылка на ImageView
        }

        tvDay.setText(formatDate(mClassWaiting.get(position).getDate()));
        tvTimeStart.setText(mClassWaiting.get(position).getStartTime().substring(0,5));
        tvTimeEnd.setText(mClassWaiting.get(position).getEndTime().substring(0,5));
        tvTrainerName.setText(mClassWaiting.get(position).getClasses().getTeacher().getName());
        tvDescription.setText(mClassWaiting.get(position).getClasses().getName());

        int maxPosition = mClassWaiting.get(position).getClasses().getMaxUsersCount();
        int reservedPositions = mClassWaiting.get(position).getReservedPositionsCount();

        //
        if (reservedPositions < maxPosition) {
            tvButton.setText(R.string.book_now);
        } else {
            tvButton.setText("Details");
        }

        //слушатель на переход к деталям класса
        MaterialRippleLayout bookNow = linearLayout.findViewById(R.id.ripple_waiting_list);
        bookNow.setOnClickListener(view -> {
            KultureNotificationManager manager = new KultureNotificationManager(
                    view.getContext().getApplicationContext(), null);
            manager.updateClassInfo(mClassWaiting.get(position).getId(), false);
        });
    }

    @Override
    public int getItemCount() {
        return mClassWaiting == null ? 0 : mClassWaiting.size();
    }

}