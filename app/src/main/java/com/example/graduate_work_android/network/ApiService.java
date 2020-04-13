package com.example.graduate_work_android.network;

import com.example.graduate_work_android.models.Login;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @Multipart
    @POST("login")
    Observable<Login> login(@Part("mobile") RequestBody mobile, @Part("password") RequestBody password);
}
