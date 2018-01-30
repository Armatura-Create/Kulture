package app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hedgehog.ratingbar.RatingBar;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;

import static app.kulture.kucherenko.init.com.kulture.utils.FormatDate.formatDate;

public class RecyclerAdapterClasses extends RecyclerView.Adapter<RecyclerAdapterClasses.ViewHolder> {

    private List<AllDayClassModel> mAllDayClasses;

    public List<AllDayClassModel> getAllDayClasses() {
        return mAllDayClasses;
    }

    public void setAllDayClasses(List<AllDayClassModel> allDayClasses) {
        mAllDayClasses = allDayClasses;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewItemPic;
        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewTeacher;
        private TextView textViewDate;
        private TextView textViewPrice;
        private RatingBar ratingBar;


        public ViewHolder(View itemView) {
            super(itemView);

            imageViewItemPic = itemView.findViewById(R.id.imageView_classes_picture);
            textViewName = itemView.findViewById(R.id.textView_classes_name);
            textViewTime = itemView.findViewById(R.id.textView_classes_time);
            textViewTeacher = itemView.findViewById(R.id.textView_classes_coach);
            textViewDate = itemView.findViewById(R.id.textView_classes_date);
            textViewPrice = itemView.findViewById(R.id.textView_classes_price);
            ratingBar = itemView.findViewById(R.id.ratingBar_classes);
        }

        public void bind(AllDayClassModel allDayClasses) {

            textViewName.setText(allDayClasses.getClasses().getName()/* + " id = " + allDayClasses.getId()*/);
            textViewTime.setText(allDayClasses.getStartTime().substring(0, 5) + " - " + allDayClasses.getEndTime().substring(0, 5));
            textViewTeacher.setText(allDayClasses.getClasses().getTeacher().getName());
            textViewDate.setText(formatDate(allDayClasses.getDate()));
            textViewPrice.setText("S$ " + allDayClasses.getClasses().getPrices().get(0).getPrice());
            ratingBar.setStar(allDayClasses.getClasses().getLevelOfDifficulty());

            if (allDayClasses.getClasses().getClassPhotos().size() != 0) {
                Uri uri = Uri.parse("http://174.138.54.52:8889" + allDayClasses.getClasses().getClassPhotos().get(0).getImage());
                Glide.with(itemView.getContext()) //передаем контекст приложения
                        .load(uri)
                        .fitCenter()
                        .thumbnail(0.5f)
                        .priority(Priority.IMMEDIATE)
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageViewItemPic); //ссылка на ImageView
            }

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_classes, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(mAllDayClasses.get(position));

    }

    @Override
    public int getItemCount() {
        return mAllDayClasses == null ? 0 : mAllDayClasses.size();
    }

}
