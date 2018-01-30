package app.kulture.kucherenko.init.com.kulture.ui.fragments.mybookings;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

import static app.kulture.kucherenko.init.com.kulture.utils.FormatDate.formatDate;

public class RecyclerAdapterReservation extends RecyclerView.Adapter<RecyclerAdapterReservation.ViewHolder> {

    //Предоставляет ссылку на представления, используемые в RecyclerView
    private List<AllDayClassModel> userInfoReservation;
    private UserInfoModel mUserInfo;

    private MaterialRippleLayout add_calendar;
    private MaterialRippleLayout call;
    private ReservationsFragment mReservationsFragment;

    public void setUserInfo(UserInfoModel userInfo) {
        mUserInfo = userInfo;
    }

    interface CancelClassesStatus {

        void cancelReserve(int id, int position);
    }

    public RecyclerAdapterReservation(ReservationsFragment reservationsFragment) {
        mReservationsFragment = reservationsFragment;
    }

    public boolean delReservation(AllDayClassModel classModel) {
        return userInfoReservation.remove(classModel);
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_my_bookings_reservations, parent, false);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final LinearLayout linearLayout = holder.mLinearLayout;

        ImageView ivCardItem = linearLayout.findViewById(R.id.iv_card_item);
        TextView tvTrainerName = linearLayout.findViewById(R.id.tv_item_trainer_name);
        TextView tvDescription = linearLayout.findViewById(R.id.tv_item_description);
        TextView tvDay = linearLayout.findViewById(R.id.tv_item_day);
        TextView tvTimeStart = linearLayout.findViewById(R.id.tv_item_time_start);
        TextView tvTimeEnd = linearLayout.findViewById(R.id.tv_item_time_end);

        if (userInfoReservation.get(position).getClasses().getClassPhotos().size() != 0) {
            Uri uri = Uri.parse("http://174.138.54.52:8889" +
                    userInfoReservation.get(position).getClasses().getClassPhotos().get(0).getImage());
            Glide.with(linearLayout.getContext()) //передаем контекст приложения
                    .load(uri)
                    .fitCenter()
                    .thumbnail(0.5f)
                    .priority(Priority.IMMEDIATE)
                    .placeholder(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(ivCardItem); //ссылка на ImageView
        }

        tvDay.setText(formatDate(userInfoReservation.get(position).getDate()));
        tvTimeStart.setText(userInfoReservation.get(position).getStartTime().substring(0, 5));
        tvTimeEnd.setText(userInfoReservation.get(position).getEndTime().substring(0, 5));
        tvTrainerName.setText(userInfoReservation.get(position).getClasses().getTeacher().getName());
        tvDescription.setText(userInfoReservation.get(position).getClasses().getName());

        //слушатель на добавление элемента в нативный календарь
        // TODO: 02.12.17 check if event is already added
        add_calendar = linearLayout.findViewById(R.id.ripple_add_calendar);
        add_calendar.setOnClickListener(view -> {

            AlertDialog warningAlert = new AlertDialog.Builder(add_calendar.getContext()).create();
            warningAlert.setCancelable(false);
            warningAlert.setTitle("Adding of event to calendar");
            warningAlert.setMessage("add to calendar?");
            warningAlert.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    long eventId;
                    String eventsTitle = userInfoReservation.get(position).getClasses().getName();
                    Log.e("0x008800", eventsTitle);
                    String eventsLocation = "kulture.com.sg";
                    String eventsDescription = userInfoReservation.get(position).getClasses().getName();

                    int years = Integer.parseInt(userInfoReservation.get(position).getDate().substring(0, 4));
                    int months = Integer.parseInt(userInfoReservation.get(position).getDate().substring(5, 7));
                    int days = Integer.parseInt(userInfoReservation.get(position).getDate().substring(8, 10));
                    int hours = Integer.parseInt(userInfoReservation.get(position).getStartTime().substring(0, 2));
                    int mins = Integer.parseInt(userInfoReservation.get(position).getStartTime().substring(3, 5));

                    GregorianCalendar calDate = new GregorianCalendar(years, months - 1, days, hours, mins);

                    String[] proj =
                            new String[]{
                                    CalendarContract.Instances._ID,
                                    CalendarContract.Instances.BEGIN,
                                    CalendarContract.Instances.END,
                                    CalendarContract.Instances.EVENT_ID,
                                    CalendarContract.Instances.TITLE
                                    //  CalendarContract.Instances.DESCRIPTION
                            };
                    Cursor cursor =
                            CalendarContract.Instances.query(add_calendar.getContext().getContentResolver(),
                                    proj, calDate.getTimeInMillis(), calDate.getTimeInMillis() + 60 * 60 * 1000
                            );
                    Log.e("0x008800", "onBindViewHolder: " + cursor.getCount());
                    if (cursor.getCount() > 0) {
                        // deal with conflict
                        SimpleAlert.show(add_calendar.getContext(), "Attention!", "You added this event to calendar already!", "Ok");
                        return;
                    }


                    ContentResolver cr = add_calendar.getContext().getContentResolver();
                    ContentValues values = new ContentValues();
                    values.put(CalendarContract.Events.DTSTART, calDate.getTimeInMillis());
                    values.put(CalendarContract.Events.DTEND, calDate.getTimeInMillis() + 60 * 60 * 1000);
                    values.put(CalendarContract.Events.TITLE, eventsTitle);
                    values.put(CalendarContract.Events.DESCRIPTION, eventsDescription);
                    values.put(CalendarContract.Events.EVENT_LOCATION, eventsLocation);
                    values.put(CalendarContract.Events.CALENDAR_ID, 1);
                    values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance()
                            .getTimeZone().getID());
                    System.out.println(Calendar.getInstance().getTimeZone().getID());

                    if (ActivityCompat.checkSelfPermission(add_calendar.getContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

                    eventId = Long.parseLong(uri.getLastPathSegment());

                    setReminder(cr, eventId, 1440 - (720 - hours * 60));//it will be called at noon everytime
                    dialogInterface.dismiss();
                }
            });
            warningAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    return;
                }
            });
            warningAlert.show();


        });

        //слушатель на звонок по номеру
        call = linearLayout.findViewById(R.id.ripple_call);
        call.setOnClickListener(view -> {
//                Toast.makeText(view.getContext().getApplicationContext(), "call" +getItemId(position) ,Toast.LENGTH_SHORT).show();

            String phoneNumber = "*160"; // call.getPhoneNumber() ???
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber + Uri.encode("#"))); //check current time
            if (ActivityCompat.checkSelfPermission(call.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            call.getContext().startActivity(callIntent);


        });


        //слушатель на удаление элемента
        MaterialRippleLayout del = linearLayout.findViewById(R.id.ripple_cancel);
        del.setOnClickListener(view -> {

//            Log.e("IdReserve ", String.valueOf(mUserInfo.getUserReservations().get(position).getId()));
            mReservationsFragment.mCancelClassesStatus.cancelReserve(mUserInfo.getUserReservations().get(position).getId(), position);

        });

    }

    public void remove(int position) {
        notifyItemRemoved(position);
    }

    public List<AllDayClassModel> getClasses() {
        return userInfoReservation;
    }

    public void setClassesReservations(List<AllDayClassModel> userInfoReservation) {
        this.userInfoReservation = userInfoReservation;
    }

    @Override
    public int getItemCount() {
        return userInfoReservation == null ? 0 : userInfoReservation.size();
    }

    public void setReminder(ContentResolver cr, long eventID, int timeBefore) {
        try {
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Reminders.MINUTES, timeBefore);
            values.put(CalendarContract.Reminders.EVENT_ID, eventID);
            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            if (ActivityCompat.checkSelfPermission(add_calendar.getContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Uri uri = cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
            Cursor c = CalendarContract.Reminders.query(cr, eventID,
                    new String[]{CalendarContract.Reminders.MINUTES});
            if (c.moveToFirst()) {
                System.out.println("calendar"
                        + c.getInt(c.getColumnIndex(CalendarContract.Reminders.MINUTES)));
            }
            c.close();
            SimpleAlert.show(add_calendar.getContext(), "Success!", "Event added to calendar!", "Ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}