package com.example.nouran.taallam;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.NotificationResponse;
import com.example.nouran.taallam.Model.UserNotifications;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Notifications {

    @FormUrlEncoded
    @POST("Notifications/GetAllUserNotifications")
    public Call<UserNotifications> getAllUserNotifications(@Field("UserID") String UserID);

    @FormUrlEncoded
    @POST("Notifications/UserUpdateSeenNotification")
    public Call<BaseResponse> updateSeenNotification(@Field("UserID") String UserID , @Field("NotificationID") int NotificationID);

    @FormUrlEncoded
    @POST("Notifications/GetNoitificationNumber")
    public Call<NotificationResponse> getNoitificationNumber(@Field("UserID") String UserID);
}
