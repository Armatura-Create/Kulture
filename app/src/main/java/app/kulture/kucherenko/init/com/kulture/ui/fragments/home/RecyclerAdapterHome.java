package app.kulture.kucherenko.init.com.kulture.ui.fragments.home;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.kulture.kucherenko.init.com.kulture.R;

/**
 * Created by st18rai on 16.10.17.
 */

public class RecyclerAdapterHome extends RecyclerView.Adapter<RecyclerAdapterHome.ViewHolder> {

    //Предоставляет ссылку на представления, используемые в RecyclerView
    private String[] mDescription;
    private int[] mImageIds;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }

    public RecyclerAdapterHome(String[] mDescription, int[] mImageIds) {
        this.mDescription = mDescription;
        this.mImageIds = mImageIds;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linear = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_home, parent, false);
        return new ViewHolder(linear);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Заполнение заданного представления данными
        final LinearLayout linear = holder.mLinearLayout;

        ImageView imageViewItemPic = linear.findViewById(R.id.imageView_recycler_icon);
        TextView textViewItemDescription = linear.findViewById(R.id.textView_recycler_description);

        Drawable drawable = ResourcesCompat.getDrawable(linear.getResources(), mImageIds[position], null);
        imageViewItemPic.setImageDrawable(drawable);
        imageViewItemPic.setContentDescription(mDescription[position]);

        textViewItemDescription.setText(mDescription[position]);



    }


    @Override
    public int getItemCount() {
        return mDescription.length;
    }
}
