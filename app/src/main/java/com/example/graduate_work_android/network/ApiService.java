package com.example.graduate_work_android.network;

import com.example.graduate_work_android.models.ResponseModel;
import com.example.graduate_work_android.models.ResponseUploadImage;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    Observable<ResponseUploadImage> uploadImage(@Part("mobile_phone") RequestBody mobile,
                                                @Part MultipartBody.Part file);


}
