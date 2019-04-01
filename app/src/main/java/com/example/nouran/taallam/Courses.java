package com.example.nouran.taallam;

import com.example.nouran.taallam.Model.Course;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Courses {

    @FormUrlEncoded
    @POST("Courses/GetCoursesList")
    public Call<Course> getCoursesList(@Field("SearchQuery") String query);

}
