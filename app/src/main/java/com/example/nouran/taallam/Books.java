package com.example.nouran.taallam;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Book;
import com.example.nouran.taallam.Model.BookData;
import com.example.nouran.taallam.Model.BookFollow;
import com.example.nouran.taallam.Model.Course;
import com.example.nouran.taallam.Model.UserBook;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Books {

    @FormUrlEncoded
    @POST("Books/GetBooksList")
    public Call<Book> getBooksList(@Field("CourseID") int CourseID);

    @FormUrlEncoded
    @POST("Books/GetBookDetails")
    public Call<BookData> getBookDetails(@Field("UserID") String UserID , @Field("BookID") int BookID);

    @FormUrlEncoded
    @POST("Books/SetBookFollower")
    public Call<BookFollow> setBookFollower(@Field("UserID") String UserID , @Field("BookID") int BookID);

    @FormUrlEncoded
    @POST("Books/UnSetBookFollower")
    public Call<BookFollow> unSetBookFollower(@Field("UserID") String UserID , @Field("BookID") int BookID);

    @POST("Books/SetUserBooks")
    public Call<BaseResponse> setUserBooks(@Body UserBook userBook);
//    public Call<BaseResponse> setUserBooks(@Field("UserID") String UserID , @Field("BookID") int[] BooksIDs);

    @FormUrlEncoded
    @POST("Books/UnSetUserBook")
    public Call<BaseResponse> unSetUserBook(@Field("UserID") String UserID , @Field("BookID") int[] BooksIDs);


}
