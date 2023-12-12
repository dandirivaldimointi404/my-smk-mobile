package com.example.my_smk_app.Retrofit;

import android.content.Context;

import com.example.my_smk_app.SessionManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        SessionManager sessionManager = new SessionManager(context);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor((Interceptor.Chain chain) -> {
                    Request originalRequest = chain.request();
                    String token = sessionManager.getAccessToken();
                    if (token != null && !token.isEmpty()) {
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + token);
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                    return chain.proceed(originalRequest);
                })
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Koneksi.BASE_URL_API)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
