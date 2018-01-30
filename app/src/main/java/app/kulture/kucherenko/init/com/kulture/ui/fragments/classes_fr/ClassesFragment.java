package app.kulture.kucherenko.init.com.kulture.ui.fragments.classes_fr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.events.BounceFitEvent;
import app.kulture.kucherenko.init.com.kulture.events.DanceEvent;
import app.kulture.kucherenko.init.com.kulture.events.PoundFitEvent;
import app.kulture.kucherenko.init.com.kulture.events.TodayEvent;
import app.kulture.kucherenko.init.com.kulture.events.TomorrowEvent;
import app.kulture.kucherenko.init.com.kulture.events.WeekEvent;
import app.kulture.kucherenko.init.com.kulture.firebase.KultureNotificationManager;
import app.kulture.kucherenko.init.com.kulture.interfaces.AllClasses;
import app.kulture.kucherenko.init.com.kulture.interfaces.TeachersLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.ApplicationData;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.Teacher;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.ItemClickSupport;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class ClassesFragment extends android.support.v4.app.Fragment implements AllClasses, SwipeRefreshLayout.OnRefreshListener, UserInfoLoadingStatus, TeachersLoadingStatus {

    private String TAG = "st18rai";

    // если listID = 1 -> mAllDayClasses, listID = 2 -> dayClasses, listID = 3 -> daysClasses,
    // listID = 4 -> typeClasses, listID = 5 -> teachersClasses
    private int listID;
    private View view;

    private List<AllDayClassModel> mAllDayClasses; // здесь хранится все, что пришло с сервера
    private List<AllDayClassModel> dayClasses; // здесь хранятся данные за выбранный день
    private List<AllDayClassModel> daysClasses; // здесь хранятся данные за выбранные дни
    private List<AllDayClassModel> typeClasses; // здесь хранятся данные по выбранным в фильтре типам
    private List<AllDayClassModel> teachersClasses; // здесь хранятся данные по выбранным в фильтре инструкторам
    private ArrayList<String> instructors; // имена инструкторов

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapterClasses adapter;
    private MaterialCalendarView materialCalendarView;
    private MultiSelectSpinner spinnerFilterTypes;
    private MultiSelectSpinner spinnerFilterInstructors;
    private Spinner spinnerSort;

    private ProgressBar progressBar;
    private ImageView ivProgressBar;

    private static final String SHARED_PACKAGE = "classes_pref";

    private SharedPreferences preferences;
    private SharedPreferences.Editor edt;

    private TextView textViewActivitiesCount;

    private ScaleInAnimationAdapter scaleAdapter;

    public static ClassesFragment newInstance() {
        return new ClassesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mAllDayClasses = new ArrayList<>();
        dayClasses = new ArrayList<>();
        daysClasses = new ArrayList<>();
        typeClasses = new ArrayList<>();
        teachersClasses = new ArrayList<>();
        instructors = new ArrayList<>();

        preferences = MApplication.getInstance().getApplicationContext().getSharedPreferences(SHARED_PACKAGE, Context.MODE_PRIVATE);

        view = inflater.inflate(R.layout.fragment_classes, container, false);
        Log.d(TAG, "onCreateView: ClassesFragment");

        textViewActivitiesCount = view.findViewById(R.id.textView_classes_showing_activies);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_classes);
        swipeRefreshLayout.setOnRefreshListener(this);

        progressBar = view.findViewById(R.id.pb_classes);
        ivProgressBar = view.findViewById(R.id.iv_background_pb);
        ivProgressBar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        setCalendar();
        setRecycler();

        if (MApplication.getInstance().getFutureClasses().isEmpty()) {
            if (Connection.isNetworkAvailable(getContext())) {
                Request.getInstance().allFutureDayClasses(this);
            } else {
                SimpleAlert.showNoConnection(getContext());
            }

            Log.e(TAG, "onCreateView: from request");
        } else {
            mAllDayClasses = MApplication.getInstance().getFutureClasses();
            adapter.setAllDayClasses(mAllDayClasses);
            adapter.notifyDataSetChanged();

            Log.e(TAG, "onCreateView: from MApplication");
        }

        setSpinner();

        return view;
    }

    @Override
    public void onResume() {
        progressBar.setVisibility(View.INVISIBLE);
        ivProgressBar.setVisibility(View.INVISIBLE);

        if (Connection.isNetworkAvailable(getContext())) {
            Request.getInstance().getUserInfo(this);
        } else {
            SimpleAlert.showNoConnection(getContext());
            swipeRefreshLayout.setRefreshing(false);
        }

        EventBus.getDefault().unregister(this);
        EventBus.getDefault().register(this);

        Log.e(TAG, "onResume: today = " + CalendarDay.today());
        Log.e(TAG, "onResume: mAllDaysClasses size = " + mAllDayClasses.size());

        restoreFilterSettings();

        Log.d(TAG, "onResume: ClassesFragment");
        super.onResume();
    }

    @Override
    public void onPause() {
        typeClasses.clear();
        daysClasses.clear();
        dayClasses.clear();
        teachersClasses.clear();
        materialCalendarView.clearSelection();

        for (int i = 0; i < spinnerFilterInstructors.getSelected().length; i++) {
            spinnerFilterInstructors.selectItem(i, true);
        }

        //  edt.putInt("spinner sort", spinnerSort.getSelectedItemPosition());
        Log.d(TAG, "onPause: ClassesFragment");

        super.onPause();
    }

    @Override
    public void onStop() {

        EventBus.getDefault().unregister(this);

        super.onStop();
    }

    // устанавливаем календарь
    private void setCalendar() {

        // на месяц вперед
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);

        materialCalendarView = view.findViewById(R.id.calendarView_classes);

        materialCalendarView.addDecorator(new DayDecorator());
        materialCalendarView.setTopbarVisible(false); // отображение названия месяца
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.today())
                .setMaximumDate(CalendarDay.from(cal))
                .setCalendarDisplayMode(CalendarMode.WEEKS)
                .commit();

        materialCalendarView.setCurrentDate(CalendarDay.today());

        // обработка нажатий по календарю
        materialCalendarView.setOnDateChangedListener((widget, date, selected) -> {
            // отображение занятий соответственно выбранному дню/дням
            if (materialCalendarView.getSelectedDates().size() > 1) {
                setClassesByManyDates(materialCalendarView.getSelectedDates());
            }
            if (materialCalendarView.getSelectedDates().size() == 1) {
                setClassByDate(materialCalendarView.getSelectedDate());

            }
            if (materialCalendarView.getSelectedDates().size() == 0) {
                restoreFilterSettings();
            }

            showSnackbar();

            for (int i = 0; i < spinnerFilterInstructors.getSelected().length; i++) {
                spinnerFilterInstructors.selectItem(i, true);
            }
        });

    }

    // устанавливаем recyclerView
    private void setRecycler() {

        recyclerView = view.findViewById(R.id.recyclerView_classes);

        recyclerView.setNestedScrollingEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        scaleAdapter = new ScaleInAnimationAdapter(adapter = new RecyclerAdapterClasses());
        scaleAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleAdapter);
        // recyclerView.setAdapter(scaleAdapter);

        // обработка нажатий по элементу списка
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {

            //переход в DetailActivity
            if (!swipeRefreshLayout.isRefreshing())
                goToDetailClass(listID, position);
        });
    }

    // устанавливаем спиннеры
    private void setSpinner() {

        spinnerSort = view.findViewById(R.id.spinner_classes_sort);
        ArrayAdapter sortAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sort_classes));

        spinnerSort.setAdapter(sortAdapter);

        // по нажатию список сортируется по цене/дате
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                    sortClassesDate();
                else
                    sortClassesPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // данные для фильтра
        ArrayList<String> optionsFilterTypes = new ArrayList<>();
        optionsFilterTypes.add(getString(R.string.filter_bounce));
        optionsFilterTypes.add(getString(R.string.filter_pounds));
        optionsFilterTypes.add(getString(R.string.filter_dance));
        //optionsFilterTypes.add(getString(R.string.filter_music));

        spinnerFilterTypes = view.findViewById(R.id.multiselectSpinner_classes_filter_types);

        spinnerFilterTypes.setItems(optionsFilterTypes)
                .setListener(selected -> {

                    // фильтр по типу
                    if (materialCalendarView.getSelectedDates().size() == 0) {
                        filterClassesByTypes(selected, mAllDayClasses);
                        materialCalendarView.clearSelection();

                    } else {

                        if (!dayClasses.isEmpty()) {
                            filterClassesByTypes(selected, dayClasses);

                        }

                        if (!daysClasses.isEmpty()) {
                            filterClassesByTypes(selected, daysClasses);

                        }
                    }

                    showSnackbar();

                    for (int i = 0; i < spinnerFilterInstructors.getSelected().length; i++) {
                        spinnerFilterInstructors.selectItem(i, true);
                    }

                    saveFilterSettings(selected);
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("Filter is OFF")
                .setSelectAll(true);

//        ArrayList<String> optionsFilterInstructors = new ArrayList<>();
//        optionsFilterInstructors.add("Farah");
//        optionsFilterInstructors.add("Jeremy");
//        optionsFilterInstructors.add("John Smith");
//        optionsFilterInstructors.add("Tiffany");

        spinnerFilterInstructors = view.findViewById(R.id.multiselectSpinner_classes_filter_instructors);

        if (instructors.isEmpty()) {
            for (int i = 0; i < MApplication.getInstance().getTeachers().size(); i++) {
                instructors.add(MApplication.getInstance().getTeachers().get(i).getName());
            }
        }
        Log.e(TAG, "setSpinner: instructors size " + instructors.size());
        // if (instructors.isEmpty())
        spinnerFilterInstructors.setItems(instructors);
//        else
//            spinnerFilterInstructors.setItems(instructors);


        spinnerFilterInstructors.setListener(selected -> {

            materialCalendarView.clearSelection();
            for (int i = 0; i < 3; i++) {
                spinnerFilterTypes.selectItem(i, true);
            }
            filterClassesByInstructors(selected, mAllDayClasses);

            showSnackbar();

            saveFilterSettings(spinnerFilterTypes.getSelected());

        })
                .setAllCheckedText("All instructors")
                .setAllUncheckedText("Filter is OFF")
                .setSelectAll(true);
    }

    // метод сортировки по цене
    private void sortClassesPrice() {

        Collections.sort(mAllDayClasses, AllDayClassModel.COMPARE_BY_PRICE);

        if (!dayClasses.isEmpty())
            Collections.sort(dayClasses, AllDayClassModel.COMPARE_BY_PRICE);

        if (!daysClasses.isEmpty())
            Collections.sort(daysClasses, AllDayClassModel.COMPARE_BY_PRICE);

        if (!typeClasses.isEmpty())
            Collections.sort(typeClasses, AllDayClassModel.COMPARE_BY_PRICE);

        if (!teachersClasses.isEmpty())
            Collections.sort(teachersClasses, AllDayClassModel.COMPARE_BY_PRICE);

        adapter.notifyDataSetChanged();
    }

    // метод сортировки по дате
    private void sortClassesDate() {

        Collections.sort(mAllDayClasses, AllDayClassModel.COMPARE_BY_DATE);

        if (!dayClasses.isEmpty())
            Collections.sort(dayClasses, AllDayClassModel.COMPARE_BY_DATE);

        if (!daysClasses.isEmpty())
            Collections.sort(daysClasses, AllDayClassModel.COMPARE_BY_DATE);

        if (!typeClasses.isEmpty())
            Collections.sort(typeClasses, AllDayClassModel.COMPARE_BY_DATE);

        if (!teachersClasses.isEmpty())
            Collections.sort(teachersClasses, AllDayClassModel.COMPARE_BY_DATE);

        adapter.notifyDataSetChanged();
    }

    // отображение занятий в выбранный день
    private void setClassByDate(CalendarDay day) {

        dayClasses.clear();

        String formatDayCalendar = "";
        int year = day.getYear();
        int month = day.getMonth();
        int currentDay = day.getDay();

        //Log.e(TAG, "setClassByDate: month = " + month);
        month = month + 1; // отсчет месяцев в календаре  с 0

        if (month == 13)
            month = 01;

        formatDayCalendar = year + "-" + month + "-" + currentDay;

//        Log.e(TAG, "setClassByDate: yy mm dd " + year + month + currentDay);
//        Log.e(TAG, "setClassByDate: mAllDayClasses = " + mAllDayClasses.size());

        for (int i = 0; i < mAllDayClasses.size(); i++) {

            String formatDayClasses = mAllDayClasses.get(i).getDate().substring(0, 10);

        /*  В календаре дата в формате: 2017-8-1 (месяц и день без ведущего нуля)
        *   а сервер присылает в формате: 2017-08-01
        *   если 5 или 8 символ 0 - удаляем его
        */

            if (formatDayClasses.charAt(5) == '0') {
                //        Log.e(TAG, "setClassByDate: char at 5 " + formatDayClasses.charAt(5));
                formatDayClasses = deleteCharacters(formatDayClasses, 5, 6);
                //        Log.e(TAG, "setClassByDate: after deleting formatdayclasses " + formatDayClasses);
            }

            if (formatDayClasses.charAt(8) == '0') {
                //        Log.e(TAG, "setClassByDate: char at 8 " + formatDayClasses.charAt(8));
                formatDayClasses = deleteCharacters(formatDayClasses, 8, 9);
                //        Log.e(TAG, "setClassByDate: after deleting formatdayclasses " + formatDayClasses);
            }

            if (formatDayClasses.charAt(7) == '0') {
                //        Log.e(TAG, "setClassByDate: char at 7 " + formatDayClasses.charAt(7));
                formatDayClasses = deleteCharacters(formatDayClasses, 7, 8);
                //        Log.e(TAG, "setClassByDate: after deleting formatdayclasses " + formatDayClasses);
            }


            Log.e(TAG, "dayClasses: formatDayClasses= " + formatDayClasses + " formatDayCalendar= " + formatDayCalendar);
            if (formatDayClasses.equals(formatDayCalendar)) {
                dayClasses.add(mAllDayClasses.get(i));
                //       Log.e(TAG, "dayClasses: formatDayClasses= " + formatDayClasses + " formatDayCalendar= " + formatDayCalendar);

            }
        }

        // Log.e(TAG, "dayClasses size: " + dayClasses.size());

//        for (int i = 0; i < 4; i++)
//            spinnerFilterTypes.selectItem(i, true);

        adapter.setAllDayClasses(dayClasses);
        scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleAdapter);
        listID = 2;
        showActivitiesCount(listID);


        filterClassesByTypes(spinnerFilterTypes.getSelected(), dayClasses);

        boolean[] newSelected = spinnerFilterTypes.getSelected();
        saveFilterSettings(newSelected);
    }

    // отображение занятий в выбранные дни
    private void setClassesByManyDates(List<CalendarDay> calendarDays) {

        daysClasses.clear();

        for (int i = 0; i < mAllDayClasses.size(); i++) {
            for (int j = 0; j < calendarDays.size(); j++) {

                String formatDayClasses = "";
                String formatDayCalendar = "";

                formatDayClasses = mAllDayClasses.get(i).getDate().substring(0, 10);

                int year = calendarDays.get(j).getYear();
                int month = calendarDays.get(j).getMonth();
                int currentDay = calendarDays.get(j).getDay();

                month = month + 1;

                if (month == 13)
                    month = 01;

                formatDayCalendar = year + "-" + month + "-" + currentDay;

//                Log.e(TAG, "setClassByManyDate: yy mm dd " + year + month + currentDay);
//                Log.e(TAG, "setClassByManyDate: mAllDayClasses = " + mAllDayClasses.size());


                if (formatDayClasses.charAt(5) == '0') {
                    //          Log.e(TAG, "setClassByManyDate: char at 5 " + formatDayClasses.charAt(5));
                    formatDayClasses = deleteCharacters(formatDayClasses, 5, 6);
                    //         Log.e(TAG, "setClassByManyDate: after deleting formatdayclasses " + formatDayClasses);
                }

                if (formatDayClasses.charAt(8) == '0') {
                    //         Log.e(TAG, "setClassByManyDate: char at 8 " + formatDayClasses.charAt(8));
                    formatDayClasses = deleteCharacters(formatDayClasses, 8, 9);
                    //         Log.e(TAG, "setClassByManyDate: after deleting formatdayclasses " + formatDayClasses);
                }

                if (formatDayClasses.charAt(7) == '0') {
                    //        Log.e(TAG, "setClassByDate: char at 7 " + formatDayClasses.charAt(7));
                    formatDayClasses = deleteCharacters(formatDayClasses, 7, 8);
                    //        Log.e(TAG, "setClassByDate: after deleting formatdayclasses " + formatDayClasses);
                }

                //   Log.e(TAG, "daysClasses: formatDayClasses= " + formatDayClasses + " formatDayCalendar= " + formatDayCalendar);

                if (formatDayClasses.equals(formatDayCalendar)) {
                    daysClasses.add(mAllDayClasses.get(i));
                }
            }
        }

//        for (int i = 0; i < 4; i++)
//            spinnerFilterTypes.selectItem(i, true);

        adapter.setAllDayClasses(daysClasses);
        scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleAdapter);
        listID = 3;
        showActivitiesCount(listID);

        filterClassesByTypes(spinnerFilterTypes.getSelected(), daysClasses);

        boolean[] newSelected = spinnerFilterTypes.getSelected();
        saveFilterSettings(newSelected);
    }

    // счетчик классов
    private void showActivitiesCount(int listID) {

        switch (listID) {
            case 1:
                textViewActivitiesCount.setText("Showing " + mAllDayClasses.size() + " of " + mAllDayClasses.size());
                break;

            case 2:
                textViewActivitiesCount.setText("Showing " + dayClasses.size() + " of " + mAllDayClasses.size());
                break;

            case 3:
                textViewActivitiesCount.setText("Showing " + daysClasses.size() + " of " + mAllDayClasses.size());
                break;

            case 4:
                textViewActivitiesCount.setText("Showing " + typeClasses.size() + " of " + mAllDayClasses.size());
                break;

            case 5:
                textViewActivitiesCount.setText("Showing " + teachersClasses.size() + " of " + mAllDayClasses.size());
                break;
        }
    }

    private void showSnackbar() {
        if (recyclerView.getAdapter().getItemCount() == 0)
            Snackbar.make(recyclerView, R.string.snackbar_text, Snackbar.LENGTH_SHORT).show();
    }

    // фильтр по типу занятий
    private void filterClassesByTypes(boolean[] selected, List<AllDayClassModel> classesList) {

        typeClasses.clear();

        if (selected[0]) {
            for (int i = 0; i < classesList.size(); i++) {
                if (classesList.get(i).getClasses().getType().equals(getString(R.string.bounce_fit))) {
                    typeClasses.add(classesList.get(i));
                }
            }
        }

        if (selected[1]) {
            for (int i = 0; i < classesList.size(); i++) {
                if (classesList.get(i).getClasses().getType().equals(getString(R.string.pound_fit))) {
                    typeClasses.add(classesList.get(i));
                }
            }
        }

        if (selected[2]) {
            for (int i = 0; i < classesList.size(); i++) {
                if (classesList.get(i).getClasses().getType().equals(getString(R.string.pound_dance))) {
                    typeClasses.add(classesList.get(i));
                }
            }
        }

//        if (selected[3]) {
//            for (int i = 0; i < classesList.size(); i++) {
//                if (classesList.get(i).getClasses().getType().equals(getString(R.string.pound_music))) {
//                    typeClasses.add(classesList.get(i));
//                }
//            }
//        }

        adapter.setAllDayClasses(typeClasses);
        scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleAdapter);
        listID = 4;
        showActivitiesCount(listID);

    }

    // фильтр по инструктору
    private void filterClassesByInstructors(boolean[] selected, List<AllDayClassModel> classesList) {

        teachersClasses.clear();

        try {
            for (int i = 0; i < instructors.size(); i++) {
                if (selected[i]) {
                    for (int j = 0; j < classesList.size(); j++) {
                        if (classesList.get(j).getClasses().getTeacher().getName().equals(instructors.get(i))) {
                            teachersClasses.add(classesList.get(j));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter.setAllDayClasses(teachersClasses);
        scaleAdapter = new ScaleInAnimationAdapter(adapter);
        scaleAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleAdapter);
        listID = 5;
        showActivitiesCount(listID);

    }

    // сохранение выбранных фильтров
    private void saveFilterSettings(boolean[] newSelected) {
        edt = preferences.edit();
        edt.putBoolean("BounceFit", newSelected[0]);
        edt.putBoolean("PoundFit", newSelected[1]);
        edt.putBoolean("Dance", newSelected[2]);
        // edt.putBoolean("Music", newSelected[3]);
        edt.apply();
    }

    // метод для восстановки настроек фильтра
    private void restoreFilterSettings() {

        // spinnerSort.setSelection(preferences.getInt("spinner sort", 0));

        boolean[] oldSelected = {
                preferences.getBoolean("BounceFit", true),
                preferences.getBoolean("PoundFit", true),
                preferences.getBoolean("Dance", true),
                //  preferences.getBoolean("Music", true),
        };

        filterClassesByTypes(oldSelected, mAllDayClasses);

        if (oldSelected[0])
            spinnerFilterTypes.selectItem(0, true);
        else
            spinnerFilterTypes.selectItem(0, false);

        if (oldSelected[1])
            spinnerFilterTypes.selectItem(1, true);
        else
            spinnerFilterTypes.selectItem(1, false);

        if (oldSelected[2])
            spinnerFilterTypes.selectItem(2, true);
        else
            spinnerFilterTypes.selectItem(2, false);

//        if (oldSelected[3])
//            spinnerFilterTypes.selectItem(3, true);
//        else
//            spinnerFilterTypes.selectItem(3, false);

        Log.e(TAG, "restoreFilterSettings: typeClasses size = " + typeClasses.size());

    }

    // Передаем данные через интент и переходим в DetailClass
    private void goToDetailClass(int listID, int position) {
        KultureNotificationManager manager = new KultureNotificationManager(getContext(), null);
        switch (listID) {

            case 1:
                progressBar.setVisibility(View.VISIBLE);
                ivProgressBar.setVisibility(View.VISIBLE);
                manager.updateClassInfo(mAllDayClasses.get(position).getId(), false);
                break;

            case 2:
                progressBar.setVisibility(View.VISIBLE);
                ivProgressBar.setVisibility(View.VISIBLE);
                manager.updateClassInfo(dayClasses.get(position).getId(), false);
                break;

            case 3:
                progressBar.setVisibility(View.VISIBLE);
                ivProgressBar.setVisibility(View.VISIBLE);
                manager.updateClassInfo(daysClasses.get(position).getId(), false);
                break;

            case 4:
                progressBar.setVisibility(View.VISIBLE);
                ivProgressBar.setVisibility(View.VISIBLE);
                manager.updateClassInfo(typeClasses.get(position).getId(), false);
                break;

            case 5:
                progressBar.setVisibility(View.VISIBLE);
                ivProgressBar.setVisibility(View.VISIBLE);
                manager.updateClassInfo(teachersClasses.get(position).getId(), false);
                break;
        }
    }

    // удаление символа в строке
    String deleteCharacters(String str, int from, int to) {
        return str.substring(0, from) + str.substring(to);
    }

    //метод для удаления дубликатов с помощью хешсет
//    public ArrayList<String> deleteDublicates(ArrayList<String> array) {
//        ArrayList<String> result = new ArrayList<String>(new HashSet<String>(array));
//        Collections.sort(result);
//        return result;
//    }

    //Вывод занятий от сегоднешнего дня
    public static List<AllDayClassModel> addClassFromToday(List<AllDayClassModel> info) {
        List<AllDayClassModel> result = new ArrayList<>();

        final Calendar c = Calendar.getInstance();

        for (int i = 0; i < info.size(); i++) {
            if ((Integer.valueOf(info.get(i).getDate().substring(5, 7)) == c.get(Calendar.MONTH) + 1 &&
                    Integer.valueOf(info.get(i).getDate().substring(8, 10)) >= c.get(Calendar.DAY_OF_MONTH)) ||
                    Integer.valueOf(info.get(i).getDate().substring(5, 7)) > c.get(Calendar.MONTH) + 1 &&
                            Integer.valueOf(info.get(i).getDate().substring(1, 4)) == c.get(Calendar.YEAR)) {

                result.add(info.get(i));

            }
        }
        return result;
    }

    //Метод определиния випа и ограничения по дате резервации
    public static boolean isAvailableForStatusMember(AllDayClassModel info) {

        UserInfoModel userInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);

        final Calendar c = Calendar.getInstance();
        final Calendar notVIP = Calendar.getInstance();
        final Calendar VIP = Calendar.getInstance();

        notVIP.add(Calendar.DATE, (new Gson().fromJson(MSharedPreferences.getInstance().getAppData(), ApplicationData.class)).getMemberTimeReservation());
        VIP.add(Calendar.DATE, (new Gson().fromJson(MSharedPreferences.getInstance().getAppData(), ApplicationData.class)).getVipTimeReservation());

        //Если вип по таким датам
        if (userInfo.getVipFinishDate() != null) {
//        if (((Integer.valueOf(finishDateVip.substring(0, 4)) >= c.get(Calendar.YEAR) &&
//                Integer.valueOf(finishDateVip.substring(5, 7)) > c.get(Calendar.MONTH) + 1) ||
//                (Integer.valueOf(finishDateVip.substring(5, 7)) == c.get(Calendar.MONTH) + 1 &&
//                        Integer.valueOf(finishDateVip.substring(8, 10)) > c.get(Calendar.DAY_OF_MONTH)))) {

            if (Integer.valueOf(info.getDate().substring(0, 4)) > VIP.get(Calendar.YEAR) ||
                    Integer.valueOf(info.getDate().substring(5, 7)) > VIP.get(Calendar.MONTH) + 1) {
                return false;
            }

            if (Integer.valueOf(info.getDate().substring(5, 7)) == VIP.get(Calendar.MONTH) + 1 &&
                    Integer.valueOf(info.getDate().substring(8, 10)) >= VIP.get(Calendar.DAY_OF_MONTH)) {
                return false;
            }
//        }
            // если просто мембер или немебер
        } else {
            if (Integer.valueOf(info.getDate().substring(0, 4)) > notVIP.get(Calendar.YEAR) ||
                    Integer.valueOf(info.getDate().substring(5, 7)) > notVIP.get(Calendar.MONTH) + 1) {
                return false;
            }

            if (Integer.valueOf(info.getDate().substring(5, 7)) == notVIP.get(Calendar.MONTH) + 1 &&
                    Integer.valueOf(info.getDate().substring(8, 10)) >= notVIP.get(Calendar.DAY_OF_MONTH)) {
                return false;
            }
        }

        return true;
    }

    // получение данных с сервера
    @Override
    public void getData(List<AllDayClassModel> info) {

        mAllDayClasses = info;
        adapter.setAllDayClasses(mAllDayClasses);
        adapter.notifyDataSetChanged();
        showActivitiesCount(listID);
        restoreFilterSettings();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (Connection.isNetworkAvailable(getContext())) {
            Request.getInstance().getStudioTeachers(this, "1");
            Request.getInstance().allFutureDayClasses(this);

            instructors.clear();
            for (int i = 0; i < MApplication.getInstance().getTeachers().size(); i++) {
                instructors.add(MApplication.getInstance().getTeachers().get(i).getName());
            }
            materialCalendarView.clearSelection();
        } else {
            SimpleAlert.showNoConnection(getContext());
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    // ниже события EventBus (вызываются по нажатию фильтра в HomeFragment)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTodayEvent(TodayEvent event) {
        materialCalendarView.clearSelection();

        for (int i = 0; i < 3; i++) {
            spinnerFilterTypes.selectItem(i, true);
        }

        filterClassesByTypes(spinnerFilterTypes.getSelected(), mAllDayClasses);

        materialCalendarView.setDateSelected(CalendarDay.today(), true);
        setClassByDate(CalendarDay.today());
        showSnackbar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTomorrowEvent(TomorrowEvent event) {

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        CalendarDay tom = CalendarDay.from(tomorrow);

        materialCalendarView.clearSelection();

        for (int i = 0; i < 3; i++) {
            spinnerFilterTypes.selectItem(i, true);
        }

        filterClassesByTypes(spinnerFilterTypes.getSelected(), mAllDayClasses);

        materialCalendarView.setDateSelected(tomorrow, true);
        setClassByDate(tom);
        showSnackbar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeekEvent(WeekEvent event) {

        Calendar week = Calendar.getInstance();

        int dayOfWeek = week.get(Calendar.DAY_OF_WEEK);
        int daysUntilNext = Calendar.SATURDAY + 1 - dayOfWeek;

        week.add(Calendar.DATE, daysUntilNext);
        CalendarDay lastDay = CalendarDay.from(week);

        Log.e(TAG, "onWeekEvent: " + daysUntilNext + lastDay);
        materialCalendarView.clearSelection();

        for (int i = 0; i < 3; i++) {
            spinnerFilterTypes.selectItem(i, true);
        }

        filterClassesByTypes(spinnerFilterTypes.getSelected(), mAllDayClasses);

        materialCalendarView.selectRange(CalendarDay.today(), lastDay);
        setClassesByManyDates(materialCalendarView.getSelectedDates());
        showSnackbar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBouncefitEvent(BounceFitEvent event) {

        materialCalendarView.clearSelection();

        spinnerFilterTypes.selectItem(0, true);
        spinnerFilterTypes.selectItem(1, false);
        spinnerFilterTypes.selectItem(2, false);
        // spinnerFilterTypes.selectItem(3, false);

        boolean[] newSelected = {true, false, false, false};
        filterClassesByTypes(newSelected, mAllDayClasses);

        saveFilterSettings(newSelected);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPoundfitEvent(PoundFitEvent event) {

        materialCalendarView.clearSelection();

        spinnerFilterTypes.selectItem(0, false);
        spinnerFilterTypes.selectItem(1, true);
        spinnerFilterTypes.selectItem(2, false);
        // spinnerFilterTypes.selectItem(3, false);

        boolean[] newSelected = {false, true, false, false};
        filterClassesByTypes(newSelected, mAllDayClasses);

        saveFilterSettings(newSelected);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDanceEvent(DanceEvent event) {

        materialCalendarView.clearSelection();

        spinnerFilterTypes.selectItem(0, false);
        spinnerFilterTypes.selectItem(1, false);
        spinnerFilterTypes.selectItem(2, true);
        // spinnerFilterTypes.selectItem(3, false);

        boolean[] newSelected = {false, false, true, false};
        filterClassesByTypes(newSelected, mAllDayClasses);

        saveFilterSettings(newSelected);
    }

    @Override
    public void onUserInfoSuccess(UserInfoModel user) {

    }

    @Override
    public void onUserInfoFailure(String message) {

    }

    @Override
    public void onTeachersSuccess(String message) {

    }

    @Override
    public void onTeachersFailure(String message) {

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMusicEvent(MusicEvent event) {
//
//        materialCalendarView.clearSelection();
//
//        spinnerFilterTypes.selectItem(0, false);
//        spinnerFilterTypes.selectItem(1, false);
//        spinnerFilterTypes.selectItem(2, false);
//        spinnerFilterTypes.selectItem(3, true);
//
//        boolean[] newSelected = {false, false, false, true};
//        filterClassesByTypes(newSelected, mAllDayClasses);
//
//        saveFilterSettings(newSelected);
//    }
}
