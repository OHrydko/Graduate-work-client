package com.example.graduate_work_android.network;

import com.example.graduate_work_android.models.ResponseAllergic;
import com.example.graduate_work_android.models.ResponseHistory;
import com.example.graduate_work_android.models.ResponseModel;
import com.example.graduate_work_android.models.ResponseSupplement;
import com.example.graduate_work_android.models.ResponseUploadImage;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    @Multipart
    @POST("login")
    Observable<ResponseModel> login(@Part("mobile") RequestBody mobile,
                                    @Part("password") RequestBody password);

    @Multipart
    @POST("registration")
    Observable<ResponseModel> registration(@Part("mobile") RequestBody mobile,
                                           @Part("password") RequestBody password,
                                           @Part("name") RequestBody name,
                                           @Part("last_name") RequestBody lastName,
                                           @Part("birthday") RequestBody birthday,
                                           @Part("user_name") RequestBody userName);

    @Multipart
    @POST("upload")
    Observable<ResponseUploadImage> uploadImage(@Part("name") RequestBody name,
                                                @Part("mobile_phone") RequestBody mobile,
                                                @Part MultipartBody.Part file);

    @Multipart
    @POST("product")
    Observable<ResponseModel> product(@Part("name") RequestBody name,
                                      @Part("mobile_phone") RequestBody mobile,
                                      @Part("type") RequestBody type,
                                      @Part("ingredient") RequestBody ingredient,
                                      @Part MultipartBody.Part file);

    @GET("history")
    Observable<ResponseHistory> history(@Query("mobile_phone") String mobilePhone);

    @GET("allergic_user")
    Observable<ResponseAllergic> getAllergic(@Query("mobile") String mobilePhone);

    @GET("supplement")
    Observable<ResponseSupplement> getSupplement();

    @Multipart
    @POST("allergic")
    Observable<ResponseModel> allergic(@Part("mobile_phone") RequestBody mobile,
                                       @Part("name") RequestBody name);


}
