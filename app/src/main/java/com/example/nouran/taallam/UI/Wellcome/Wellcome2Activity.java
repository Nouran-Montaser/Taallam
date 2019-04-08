package com.example.nouran.taallam.UI.Wellcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Book;
import com.example.nouran.taallam.Model.BooksList;
import com.example.nouran.taallam.Model.CoursesList;
import com.example.nouran.taallam.Model.UserBook;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BookDetailActivity;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksActivity;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksClickListener;
import com.example.nouran.taallam.UI.Main.MainActivity;
import com.example.nouran.taallam.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wellcome2Activity extends AppCompatActivity {

    private Button mFinishButton;
    private RecyclerView mRecyclerView;
    private LinearLayout mLayoutContainer;
    private Wellcome2Adapter wellcomeAdapter;
    private int courseID;
    private boolean check = false;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private String userId;
    private ArrayList<BooksList> booksLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome2);

        courseID = getIntent().getIntExtra("SelectedCourse", 0);
        sharedPrefs = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        userId = sharedPrefs.getString("UserID", null);
        Log.i("OOPP", userId);
        Log.i("OOPP", courseID + "");

        getBooks(courseID);

        initializer();


    }

    private void initializer() {

        int courseId = getIntent().getIntExtra("CourseID", 0);
        if (courseId != 0)
            getBooks(courseId);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.wellcome);

        mFinishButton = findViewById(R.id.wellcome2_finish_button);
        mRecyclerView = findViewById(R.id.welcome2_recyclerView);
        mLayoutContainer = findViewById(R.id.wellcome2_layout_container);

        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booksLists = wellcomeAdapter.getBooks();
                if (booksLists == null || booksLists.size() == 0) {
                    Toast.makeText(Wellcome2Activity.this, R.string.wellcome2_msg, Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences.Editor sharedPrefsEditor;
                    sharedPrefsEditor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    sharedPrefsEditor.putBoolean("IsFirstTime", false);
                    sharedPrefsEditor.apply();

                    Intent mMaimIntent = new Intent(Wellcome2Activity.this, MainActivity.class);
                    setUserBook(userId, booksLists);
                    startActivity(mMaimIntent);
                    finish();
                }
            }
        });
    }

    public void getBooks(int courseId) {


        Books api = RetrofitClient.getClient(Wellcome2Activity.this).create(Books.class);
        Call<Book> call = api.getBooksList(courseId);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Wellcome2Activity.this);
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mRecyclerView.addItemDecoration(dividerItemDecoration);
                        ArrayList<BooksList> books = new ArrayList<>(Arrays.asList(response.body().getBooksList()));
                        wellcomeAdapter = new Wellcome2Adapter(Wellcome2Activity.this, books,
                                null, "welcome");

                        mRecyclerView.setAdapter(wellcomeAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });

    }

    private void setUserBook(String userId, ArrayList<BooksList> list) {
        Books api = RetrofitClient.getClient(Wellcome2Activity.this).create(Books.class);
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Toast.makeText(this, list.get(i) + "", Toast.LENGTH_SHORT).show();
            list1.add(list.get(i).getID());
        }
        UserBook userBook = new UserBook("020bd359-65f1-4c87-9b4b-6e4b495a79bb", list1);
        Call<BaseResponse> call = api.setUserBooks(userBook);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body() != null) {
                    Log.i("OOPP", userId + "      " + response.body().getIsSuccess() + "");
                    if (!response.body().getIsSuccess()) {
                        Toast.makeText(Wellcome2Activity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Wellcome2Activity.this, "SET", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
//
//    private void unSetUserBook(String userId, int id) {
//        Books api2 = RetrofitClient.getClient(Wellcome2Activity.this).create(Books.class);
//        int [] arr = {id};
//        Call<BaseResponse> call = api2.unSetUserBook(userId, arr);
//        call.enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                if (response.body() != null) {
//                    if (!response.body().getIsSuccess()) {
//                        Toast.makeText(Wellcome2Activity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
//                    } else
//                        Toast.makeText(Wellcome2Activity.this, "UNSET", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//            }
//        });
//    }

}
