package app.kulture.kucherenko.init.com.kulture.ui.fragments.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.events.BounceFitEvent;
import app.kulture.kucherenko.init.com.kulture.events.DanceEvent;
import app.kulture.kucherenko.init.com.kulture.events.PoundFitEvent;
import app.kulture.kucherenko.init.com.kulture.events.TodayEvent;
import app.kulture.kucherenko.init.com.kulture.events.TomorrowEvent;
import app.kulture.kucherenko.init.com.kulture.events.WeekEvent;
import app.kulture.kucherenko.init.com.kulture.interfaces.GetBannerCode;
import app.kulture.kucherenko.init.com.kulture.models.HomeModel;
import app.kulture.kucherenko.init.com.kulture.ui.activity.main.IFragmentListener;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.ItemClickSupport;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import app.kulture.kucherenko.init.com.kulture.utils.StaticValues;

public class HomeFragment extends Fragment implements View.OnClickListener, GetBannerCode {

    private static final String style = "<style>\n" +
            "    body{\n" +
            "    background-color:black\n" +
            "    }\n" +
            "</style>";

    private static String defaultVideoCode = "HdTfYOAeWT0";
    private String frameVideo;

    private View view;

    private Button buttonToday;
    private Button buttonTomorrow;
    private Button buttonWeek;

    WebView videoView;

    HomeModel homeModel = new HomeModel();

    private String[] mDescription = homeModel.getmDescription();
    private int[] mImageIds = homeModel.getmImageIds();


    private IFragmentListener listener;

    public static HomeFragment newInstance(IFragmentListener listener) {
        HomeFragment fragment = new HomeFragment();
        fragment.listener = listener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonToday = view.findViewById(R.id.button_home_today);
        buttonTomorrow = view.findViewById(R.id.button_home_tomorrow);
        buttonWeek = view.findViewById(R.id.button_home_week);

        buttonToday.setOnClickListener(this);
        buttonTomorrow.setOnClickListener(this);
        buttonWeek.setOnClickListener(this);

        // getAllStudios modell = new getAllStudios();
        //modell.setId(classesModel.getId());
        // Request.getInstance().getAllStudios(model);
        // defaultVideoCode = Request.videoCode;
        // Toast.makeText(view.getContext(), defaultVideoCode, Toast.LENGTH_SHORT).show();

//        getAllStudios = new ArrayList();
//        getBannerCode(getAllStudios);
//
//        defaultVideoCode = getAllStudios.get(0).getBanner();
//        Log.i("videoCode", defaultVideoCode);

        Request.getInstance().getAllStudios(this);

//        Toast.makeText(view.getContext(), defaultVideoCode, Toast.LENGTH_SHORT).show();

        videoView = view.findViewById(R.id.web_view_for_youtube);
        videoView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.loadData(addFrame(defaultVideoCode), "text/html", "utf-8");

        if (!Connection.isNetworkAvailable(getContext()))
            SimpleAlert.showNoConnection(getContext());

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        view = getView();

        if (view != null) {
            final RecyclerView recyclerView = view.findViewById(R.id.recyclerView_home_activities);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            final RecyclerAdapterHome adapter = new RecyclerAdapterHome(mDescription, mImageIds);
            recyclerView.setAdapter(adapter);

            ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {

                switch (position) {

                    case 0:
                        EventBus.getDefault().post(new BounceFitEvent());
                        break;

                    case 1:
                        EventBus.getDefault().post(new PoundFitEvent());
                        break;

                    case 2:
                        EventBus.getDefault().post(new DanceEvent());
                        break;

//                    case 3:
//                        EventBus.getDefault().post(new MusicEvent());
//                        break;
                }

                if (listener != null) listener.replaceFragment(StaticValues.FRAGMENT_CLASSES);

            });

        }
    }

    @Override
    public void onClick(View view) {
        // todo: transfer some data to classes

        switch (view.getId()) {

            case R.id.button_home_today:
                EventBus.getDefault().post(new TodayEvent());
                break;

            case R.id.button_home_tomorrow:
                EventBus.getDefault().post(new TomorrowEvent());
                break;

            case R.id.button_home_week:
                EventBus.getDefault().post(new WeekEvent());
                break;

        }

        if (listener != null) listener.replaceFragment(StaticValues.FRAGMENT_CLASSES);

    }

    @Override
    public void onResume() {
        super.onResume();
        Request.getInstance().getAllStudios(this);
    }

    private String addFrame(String info) {
        frameVideo = "<html>" + style +
                "<body>" +
                "<iframe style=\\\"position:absolute; top:0; left:0\" width=\"100%\" height=\"90%\" src=\"https://www.youtube.com/embed/" +
                info + "\" frameborder=\"0\" allowfullscreen></iframe>" +
                "</body>" +
                "</html>";
        return frameVideo;
    }

    @Override
    public void getBannerCode(String codeVideoBanner) {
        if (!defaultVideoCode.equals(codeVideoBanner)) {
            defaultVideoCode = codeVideoBanner;
            videoView.loadData(addFrame(codeVideoBanner), "text/html", "utf-8");
        }
    }
}
