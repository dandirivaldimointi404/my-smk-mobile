package com.example.my_smk_app.Retrofit;

import android.content.Context;

public class Koneksi {
    public static final String BASE_URL_API = "https://7aed-140-213-124-99.ngrok-free.app/api/";

    public static BaseApiServices getAPIService(Context context) {
        return RetrofitClient.getClient(context).create(BaseApiServices.class);
    }
}
