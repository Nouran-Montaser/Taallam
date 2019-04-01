package com.example.nouran.taallam;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.ForumBookPost;
import com.example.nouran.taallam.Model.Message;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Messages {

    @FormUrlEncoded
    @POST("Messages/SendMessage")
    public Call<BaseResponse> sendMessage(@Field("UserID") String UserID , @Field("Message") Message Message);

}
