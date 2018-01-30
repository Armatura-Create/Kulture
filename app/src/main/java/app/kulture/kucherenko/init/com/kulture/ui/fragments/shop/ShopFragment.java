package app.kulture.kucherenko.init.com.kulture.ui.fragments.shop;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.databinding.FragmentShopBinding;
import app.kulture.kucherenko.init.com.kulture.utils.Connection;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;

public class ShopFragment extends android.support.v4.app.Fragment {

    private View view;
    private FragmentShopBinding binding;

    private WebView shop;
    private static final String style = "<style>\n" +
            "    body{\n" +
            "    background-color:black\n" +
            "    }\n" +
            "</style>";

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop, container, false);
        binding = DataBindingUtil.bind(view);

        shop = new WebView(view.getContext());

        String shopURL = "<html>" + style +
                "<body>" +
                "<iframe style=\\\"position:absolute; top:0; left:0\" width=\"100%\" height=\"100%\" src=\"http://kulture.lyzhov.pro/shop/\" frameborder=\"0\" allowfullscreen></iframe>" +
                "</body>" +
                "</html>";

//        shop = view.findViewById(R.id.web_view_shop);
        shop.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }
                return false;
            }
        });

        shop.getSettings().setJavaScriptEnabled(true);

        shop.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(view.getContext(), description, Toast.LENGTH_SHORT).show();
            }
        });
        shop.loadDataWithBaseURL(null, shopURL,"text/html", "utf-8", null);
        shop.requestFocus(1);
        shop.loadUrl(getResources().getString(R.string.host_shop));

//        if (!Connection.isNetworkAvailable(getContext()))
//                SimpleAlert.showNoConnection(getContext());

        return shop;
    }
}
