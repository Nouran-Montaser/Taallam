package com.example.nouran.taallam;

import com.example.nouran.taallam.Model.Follow.Followers;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Login;
import com.example.nouran.taallam.Model.Register;
import com.example.nouran.taallam.Model.SearchUsers;
import com.example.nouran.taallam.Model.User;
import com.example.nouran.taallam.Model.UserHomeDetails;
import com.example.nouran.taallam.Model.UserProfileDetails;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Users {

    @FormUrlEncoded
    @POST("User/Register")
    public Call<Register> register(@Field("Email") String mEmail, @Field("Name") String mName, @Field("Password") String Password
            , @Field("ConfirmPassword") String ConfirmPassword, @Field("FacebookID") String FacebookID, @Field("IsFB") boolean IsFB);

    @FormUrlEncoded
    @POST("User/Login")
    public Call<Login> login(@Field("Email") String mEmail, @Field("Password") String Password, @Field("DeviceToken") String DeviceToken,
                             @Field("FacebookID") String FacebookID, @Field("IsFB") boolean IsFB);

    @FormUrlEncoded
    @POST("User/GetUserFollowers")
    public Call<Followers> GetUserFollowers(@Field("SelectedUserID") String SelectedUserID);

    @FormUrlEncoded
    @POST("User/ForgetPassword")
    public Call<BaseResponse> ForgetPassword(@Field("Email") String Email);

    @FormUrlEncoded
    @POST("User/VerifyPassword")
    public Call<BaseResponse> verifyPassword(@Field("Email") String email, @Field("VerifyCode") String VerifyCode, @Field("Password") String Password,
                                             @Field("ConfirmPassword") String ConfirmPassword);


    @FormUrlEncoded
    @POST("User/GetUserProfileDetails")
    public Call<UserProfileDetails> getUserProfileDetails(@Field("SelectedUserID") String SelectedUserID, @Field("UserID") String UserID);

    @FormUrlEncoded
    @POST("User/SearchUser")
    public Call<User> searchUser(@Field("SearchQuery") String SearchQuery);

    @FormUrlEncoded
    @POST("User/LogOut")
    public Call<BaseResponse> logOut(@Field("UserID") String UserID , @Field("DeviceToken") String DeviceToken);

    @FormUrlEncoded
    @POST("User/DoGetUserHomeDetails")
    public Call<UserHomeDetails> getUserHomeDetails(@Field("UserID") String UserID , @Field("PostId") int PostId);

    @FormUrlEncoded
    @POST("User/SetUserFollower")
    public Call<BaseResponse> setUserFollower(@Field("UserID") String UserID , @Field("SelectedUserID") String SelectedUserID);

    @FormUrlEncoded
    @POST("User/UnfollowUser")
    public Call<BaseResponse> unfollowUser(@Field("UserID") String UserID , @Field("SelectedUserID") String SelectedUserID);

    @Multipart
    @FormUrlEncoded
    @POST("User/EditProfilePictureDetails/?{UserID}")
    public Call<BaseResponse> editProfilePictureDetails(@Path("UserID") String UserID , @Part MultipartBody.Part test);

//    @Multipart
//    @POST("/upload")
//    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file, @Part("name") RequestBody requestBody);

    @FormUrlEncoded
    @POST("User/EditProfileDetails")
    public Call<BaseResponse> editProfileDetails(@Field("UserID") String UserID , @Field("Name") String Name , @Field("About") String About);

}