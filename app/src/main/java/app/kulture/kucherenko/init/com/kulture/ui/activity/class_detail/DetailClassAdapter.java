package app.kulture.kucherenko.init.com.kulture.ui.activity.class_detail;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;

/**
 * Created by dev on 27.10.17.
 */

public class DetailClassAdapter extends RecyclerView.Adapter<DetailClassAdapter.ViewHolder> {

    //Предоставляет ссылку на представления, используемые в RecyclerView
    private List<String> mImageURLs;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }

    public DetailClassAdapter(List<String> mImageURLs) {
        this.mImageURLs = mImageURLs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linear = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_detail_class, parent, false);
        return new ViewHolder(linear);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Заполнение заданного представления данными
        final LinearLayout linear = holder.mLinearLayout;

        ImageView imageViewItemPic = linear.findViewById(R.id.imageView_recycler_item_detail_classes);

        if(mImageURLs.size() != 0) {
            Uri uri = Uri.parse("http://174.138.54.52:8889" + mImageURLs.get(position));
            Glide.with(linear.getContext()) //передаем контекст приложения
                    .load(uri)
                    .fitCenter()
                    .thumbnail(0.5f)
                    .priority(Priority.IMMEDIATE)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageViewItemPic); //ссылка на ImageView
        }
    }

    @Override
    public int getItemCount() {
        return mImageURLs.size();
    }

}
