package app.kulture.kucherenko.init.com.kulture.ui.activity.list_past_activities;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;

import static app.kulture.kucherenko.init.com.kulture.utils.FormatDate.formatDate;

public class RecyclerAdapterListPast extends RecyclerView.Adapter<RecyclerAdapterListPast.ViewHolder> {
    //Предоставляет ссылку на представления, используемые в RecyclerView
    private List<AllDayClassModel> mAllClasses;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }

    public void setPastClasses(List<AllDayClassModel> allClasses) {
        mAllClasses = allClasses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_list_previous, parent, false);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LinearLayout linearLayout = holder.mLinearLayout;

        ImageView ivCardItem = linearLayout.findViewById(R.id.iv_card_item);
        TextView tvTrainerName = linearLayout.findViewById(R.id.tv_item_trainer_name);
        TextView tvDescription = linearLayout.findViewById(R.id.tv_item_description);
        TextView tvDay= linearLayout.findViewById(R.id.tv_item_day);
        TextView tvTimeStart = linearLayout.findViewById(R.id.tv_item_time_start);
        TextView tvTimeEnd = linearLayout.findViewById(R.id.tv_item_time_end);

        if(mAllClasses.get(position).getClasses().getClassPhotos().size() != 0) {
            Uri uri = Uri.parse("http://174.138.54.52:8889" +
                    mAllClasses.get(position).getClasses().getClassPhotos().get(0).getImage());
            Glide.with(linearLayout.getContext()) //передаем контекст приложения
                    .load(uri)
                    .fitCenter()
                    .thumbnail(0.5f)
                    .priority(Priority.IMMEDIATE)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(ivCardItem); //ссылка на ImageView
        }
        tvDay.setText(formatDate(mAllClasses.get(position).getDate()));
        tvTimeStart.setText(mAllClasses.get(position).getStartTime().substring(0,5));
        tvTimeEnd.setText(mAllClasses.get(position).getEndTime().substring(0,5));
        tvTrainerName.setText(mAllClasses.get(position).getClasses().getTeacher().getName());
        tvDescription.setText(mAllClasses.get(position).getClasses().getName());


    }

    @Override
    public int getItemCount() {
        return mAllClasses == null ? 0 : mAllClasses.size();
    }

}
