package com.example.nouran.taallam;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Contacts;
import com.example.nouran.taallam.Model.ConversationMessage;
import com.example.nouran.taallam.Model.ForumBookPost;
import com.example.nouran.taallam.Model.Message;
import com.example.nouran.taallam.Model.UnReadMessages;
import com.example.nouran.taallam.Model.UserMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Messages {

    @POST("Messages/SendMessage")
    public Call<BaseResponse> sendMessage(@Body UserMessage Message);

    @FormUrlEncoded
    @POST("Messages/GetMessagesContacts")
    public Call<Contacts> getMessagesContacts(@Field("UserID") String UserID);

    @FormUrlEncoded
    @POST("Messages/ConversationMessages")
    public Call<ConversationMessage> conversationMessages(@Field("ConversationUserID") String ConversationUserID,
                                                          @Field("LastMessageID") int LastMessageID, @Field("UserID") String UserID);


    @FormUrlEncoded
    @POST("Messages/UserUnreadMessagesNumber")
    public Call<UnReadMessages> userUnreadMessagesNumber(@Field("UserID") String UserID);

}
