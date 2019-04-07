package com.example.nouran.taallam.UI.Main.Category_Fragment.Books;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Forum;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Book;
import com.example.nouran.taallam.Model.BookPosts;
import com.example.nouran.taallam.Model.Comments;
import com.example.nouran.taallam.Model.ForumBookPost;
import com.example.nouran.taallam.Model.HomePosts;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Main_Fragment.ClickListener;
import com.example.nouran.taallam.UI.Main.Main_Fragment.CommentsActivity;
import com.example.nouran.taallam.UI.Main.Main_Fragment.MainAdapter;
import com.example.nouran.taallam.UI.Main.MessageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookInteractionActivity extends AppCompatActivity {

    private RecyclerView mInteractionsRecyclerView;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_interaction);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.interactions);

        mInteractionsRecyclerView = findViewById(R.id.book_interactions_recyclerView);

        sharedPrefs = BookInteractionActivity.this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        userId = sharedPrefs.getString("UserID", null);

        int bookId = getIntent().getIntExtra("BOOK_ID", 0);

        getBookPost(userId, bookId);

    }

    private void getBookPost(String userId, int bookId) {
        Forum api = RetrofitClient.getClient(BookInteractionActivity.this).create(Forum.class);
        Call<ForumBookPost> call = api.getBookPosts(userId, bookId);
        call.enqueue(new Callback<ForumBookPost>() {
            @Override
            public void onResponse(Call<ForumBookPost> call, Response<ForumBookPost> response1) {
                if (response1.body() != null) {
                    if (response1.body().getIsSuccess()) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookInteractionActivity.this);
                        mInteractionsRecyclerView.setHasFixedSize(true);
                        mInteractionsRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mInteractionsRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mInteractionsRecyclerView.addItemDecoration(dividerItemDecoration);

                        mInteractionsRecyclerView.setAdapter(new MainAdapter(BookInteractionActivity.this, response1.body().getBookPosts(), new ClickListener() {
                            @Override
                            public void OnClick(String action, int postId, int position) {
                                if (action.equals("like")) {
                                    Forum api = RetrofitClient.getClient(BookInteractionActivity.this).create(Forum.class);
                                    Call<BaseResponse> call = api.likePost(userId, postId);
                                    call.enqueue(new Callback<BaseResponse>() {
                                        @Override
                                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                            if (response.body() != null) {
                                                if (response.body().getIsSuccess()) {

                                                } else
                                                    Toast.makeText(BookInteractionActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                                        }
                                    });

                                } else if (action.equals("comment")) {
                                    Forum api = RetrofitClient.getClient(BookInteractionActivity.this).create(Forum.class);
                                    Call<Comments> call = api.getPostComments(userId, postId);
                                    call.enqueue(new Callback<Comments>() {
                                        @Override
                                        public void onResponse(Call<Comments> call, Response<Comments> response) {
                                            if (response.body() != null) {
                                                if (response.body().getIsSuccess()) {
                                                    ArrayList<HomePosts> comment = new ArrayList<>(Arrays.asList(response1.body().getBookPosts()));
                                                    Intent commentIntent = new Intent(BookInteractionActivity.this, CommentsActivity.class);
                                                    commentIntent.putExtra("position", position);
                                                    commentIntent.putParcelableArrayListExtra("commentObj", comment);
                                                    commentIntent.putExtra("postID", postId);
                                                    commentIntent.putExtra("UserId", userId);
                                                    startActivity(commentIntent);
                                                } else
                                                    Toast.makeText(BookInteractionActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Comments> call, Throwable t) {

                                        }
                                    });
                                }
                            }

                        }));
                    }
                }
            }

            @Override
            public void onFailure(Call<ForumBookPost> call, Throwable t) {

            }
        });

    }

}
