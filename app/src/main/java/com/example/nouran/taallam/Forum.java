package com.example.nouran.taallam;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.BookPosts;
import com.example.nouran.taallam.Model.Comments;
import com.example.nouran.taallam.Model.ForumBookPost;
import com.example.nouran.taallam.Model.UserHomeDetails;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Forum {

    @FormUrlEncoded
    @POST("Forum/DoLikePost")
    public Call<BaseResponse> likePost(@Field("UserID") String UserID , @Field("PostId") int PostId);

    @FormUrlEncoded
    @POST("Forum/DoGetPostComments")
    public Call<Comments> getPostComments(@Field("UserID") String UserID , @Field("PostId") int PostId);

    @FormUrlEncoded
    @POST("Forum/GetBookPosts")
    public Call<ForumBookPost> getBookPosts(@Field("UserID") String UserID , @Field("BookID") int BookID);

}
