package com.example.nouran.taallam.UI.Main.Category_Fragment.Books;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Forum;
import com.example.nouran.taallam.Model.Book;
import com.example.nouran.taallam.Model.BookPosts;
import com.example.nouran.taallam.Model.ForumBookPost;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookInteractionActivity extends AppCompatActivity {

    private RecyclerView mInteractionsRecyclerView;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_interaction);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.wellcome);

        mInteractionsRecyclerView = findViewById(R.id.book_interactions_recyclerView);

        sharedPrefs = BookInteractionActivity.this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String userId = sharedPrefs.getString("UserID", null);

        int bookId = getIntent().getIntExtra("BOOK_ID",0);

        getBookPost(userId, bookId);

    }

    private void getBookPost(String userId, int bookId) {
        Forum api = RetrofitClient.getClient(BookInteractionActivity.this).create(Forum.class);
        Call<ForumBookPost> call = api.getBookPosts(userId, bookId);
        call.enqueue(new Callback<ForumBookPost>() {
            @Override
            public void onResponse(Call<ForumBookPost> call, Response<ForumBookPost> response) {
                if (response.body() != null)
                {
                    if (response.body().getIsSuccess())
                    {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookInteractionActivity.this);
                        mInteractionsRecyclerView.setHasFixedSize(true);
                        mInteractionsRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mInteractionsRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mInteractionsRecyclerView.addItemDecoration(dividerItemDecoration);
                        mInteractionsRecyclerView.setAdapter(new InteractionsAdapter(BookInteractionActivity.this , response.body().getBookPosts()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ForumBookPost> call, Throwable t) {

            }
        });

    }

}
