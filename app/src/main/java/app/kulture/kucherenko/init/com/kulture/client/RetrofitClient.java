package app.kulture.kucherenko.init.com.kulture.client;

import java.util.concurrent.TimeUnit;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.ui.MApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    private static final String ROOT_URL = MApplication.getInstance().getMResources().getString(R.string.host);
    private static Retrofit retrofit;

    /**
     * @method readTimeout/writeTimeout is optional by server rules
     *         readTimeout/writeTimeout не обязательны для настройки - зависит от правил сервера
     * @return global Singleton class for client-server requests
     */
    private static Retrofit getRetrofitInstance() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(loggingInterceptor) //TODO remove inspector in realise
                .build();

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }

    static KultureAPI getAPI() {
        return getRetrofitInstance().create(KultureAPI.class);
    }
}
