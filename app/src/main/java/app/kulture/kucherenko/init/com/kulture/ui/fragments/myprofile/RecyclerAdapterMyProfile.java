package app.kulture.kucherenko.init.com.kulture.ui.fragments.myprofile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.ui.activity.contact.ContactActivity;
import app.kulture.kucherenko.init.com.kulture.ui.activity.payments.PaymentsHistoryActivity;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class RecyclerAdapterMyProfile extends RecyclerView.Adapter<RecyclerAdapterMyProfile.ViewHolder> {

    //Предоставляет ссылку на представления, используемые в RecyclerView
    private String[] mCategories;
    private String[] mDescription;
    private int[] mImageIds;
    private View view;
    private MyProfileFragment myProfileFragment;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }
    }

    RecyclerAdapterMyProfile(String[] mCategories, String[] mDescription, int[] mImageIds, View view, MyProfileFragment myProfileFragment) {
        this.mCategories = mCategories;
        this.mDescription = mDescription;
        this.mImageIds = mImageIds;
        this.view = view;
        this.myProfileFragment = myProfileFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linear = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_my_profile, parent, false);
        return new ViewHolder(linear);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Заполнение заданного представления данными
        final LinearLayout linear = holder.mLinearLayout;

        ImageView imageViewItemPic = linear.findViewById(R.id.imageView_recycler_item);
        TextView textViewCategories = linear.findViewById(R.id.textView_recycler_item_category);
        TextView textViewItemDescription = linear.findViewById(R.id.textView_recycler_item_description);

        Drawable drawable = ResourcesCompat.getDrawable(linear.getResources(), mImageIds[position], null);
        imageViewItemPic.setImageDrawable(drawable);
        imageViewItemPic.setContentDescription(mCategories[position]);

        textViewCategories.setText(mCategories[position]);
        textViewItemDescription.setText(mDescription[position]);

        MaterialRippleLayout recyclerLinear = linear.findViewById(R.id.ripple_my_profile);

        recyclerLinear.setOnClickListener(view -> goToInfo(position));

    }

    private void goToInfo(int position) {
        switch (position) {
            case 0:
                view.getContext().startActivity(new Intent(view.getContext(), PaymentsHistoryActivity.class));
                break;
            case 1:
                if (Connection.isNetworkAvailable(view.getContext())) {
                    Request.getInstance().getAllItems(myProfileFragment);
                } else {
                    SimpleAlert.showNoConnection(view.getContext());
                }
                break;
            case 2:
                // TODO: Подписка
                myProfileFragment.buyMembership();
                break;
            case 3:
                myProfileFragment.buyUnlimited();
                break;
//            case :
//                view.getContext().startActivity(new Intent(view.getContext(), HowToMyProfileActivity.class));
//                break;
            case 4:
                //переход на активити контакты
                view.getContext().startActivity(new Intent(view.getContext(), ContactActivity.class));
                break;
            case 5:
                //Поделиться ссылкой на приложение
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "#thisisKulture");
                    String sAux = "\nLet me recommend you this site\n\n";
                    sAux = sAux + "http://kulture.com.sg/ \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    view.getContext().startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }

                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mCategories.length;
    }

}
