package com.example.my_smk_app.Retrofit;

import com.example.my_smk_app.Responses.MateriApiResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BaseApiServices {

    @GET("materi")
    Call<MateriApiResponse>getMateris();

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("logout")
    Call<ResponseBody> logout(@Header("Authorization") String accessToken);
}
