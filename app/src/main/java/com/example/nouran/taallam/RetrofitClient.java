package com.example.nouran.taallam;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";

    public static Retrofit getClient(Context context) {

        sharedPrefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mUserId = sharedPrefs.getString("UserID", null);

        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .serializeNulls()
                    .create();

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(chain -> {
                        Request request = chain.request();
                        Request newRequest;
                        request=request.newBuilder().addHeader("Content-Type","application/json")
                                .addHeader("Token",mUserId)
                                .addHeader("UserID",mUserId).build();

                        newRequest = request.newBuilder().build();
                        return chain.proceed(newRequest);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://yaken.cloudapp.net/Ta3llam/Api/")
                    .client(client)
                    .addConverterFactory(new NullOnEmptyConverterFactory())

                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .build();
        }
        return retrofit;
    }

     static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return (Converter<ResponseBody, Object>) body -> {
                String temp = body.string();
                ResponseBody responseBody2 = ResponseBody.create(body.contentType(), temp);

                if (body.contentLength() == 0)
                    return new Gson().fromJson("[]", type);
                else if (!ResponseBody.class.equals(type)) {
                    if (!(temp.startsWith("{") || temp.startsWith("["))) {
                        Log.i("FACTORY", "responseBodyConverter: " + temp);
                        if (List.class.equals(type))
                            return new Gson().fromJson("[]", type);
                        else
                            return new Gson().fromJson("{}", type);
//
                    }
                }

                return delegate.convert(responseBody2);
            };

        }

    }

}
