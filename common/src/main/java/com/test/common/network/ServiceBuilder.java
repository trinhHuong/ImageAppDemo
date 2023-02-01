package com.test.common.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceBuilder {
    private static final String TAG = "ServiceBuilder";
    private static final String BASE_URL = "https://api.pexels.com/v1/";
    private static final int TIMEOUT = 20;
    private static Retrofit sInstance;

    private static OTTVideoService sService;

    private synchronized static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY).setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    // User agent default
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        String userAgent = System.getProperty("http.agent");
                        Request.Builder builder = original.newBuilder();
                        builder.addHeader("User-Agent", userAgent);
                        builder.addHeader("Content-Type", "application/json");
                        builder.addHeader("Authorization", "p0771pZ7EsEogYBpX8ysV9Eqvy1EGMUNyrknwsYVgweUDrWjiSRQkWUl");
                        Request request = builder.method(original.method(), original.body()).build();
                        return chain.proceed(request);
                    }
                })
                .build();

        if (sInstance == null) {
            // User agent
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }

        return sInstance;
    }

    public synchronized static OTTVideoService getService() {
        if (sService == null) {
            sService = getRetrofit().create(OTTVideoService.class);
        }
        return sService;
    }

}
