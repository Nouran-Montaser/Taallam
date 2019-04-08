package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nouran.taallam.Forum;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Comments;
import com.example.nouran.taallam.Model.HomePosts;
import com.example.nouran.taallam.Model.UserHomeDetails;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Main_Fragment.ClickListener;
import com.example.nouran.taallam.UI.Main.Main_Fragment.CommentsActivity;
import com.example.nouran.taallam.UI.Main.Main_Fragment.MainAdapter;
import com.example.nouran.taallam.Users;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedActivity extends AppCompatActivity {

    private String userid;
    private RecyclerView mNewsFeedRecyclerView;
    private LinearLayout mNewsFeedEmptycontainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        if (getIntent().getStringExtra("followerObj") != null) {
            userid = getIntent().getStringExtra("followerObj");
        }

        mNewsFeedRecyclerView = findViewById(R.id.newsFeed_recyclerView);
        mNewsFeedEmptycontainer = findViewById(R.id.newsFeed_emptycontainer);

        getPosts();
    }

    private void getPosts() {

        Users api = RetrofitClient.getClient(NewsFeedActivity.this).create(Users.class);
        Call<UserHomeDetails> call = api.getUserHomeDetails(userid, 0);
        call.enqueue(new Callback<UserHomeDetails>() {
            @Override
            public void onResponse(Call<UserHomeDetails> call, Response<UserHomeDetails> response1) {
                if (response1.body() != null) {
                    if (response1.body().getIsSuccess()) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewsFeedActivity.this);

                        mNewsFeedRecyclerView.setHasFixedSize(true);
                        mNewsFeedRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mNewsFeedRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mNewsFeedRecyclerView.addItemDecoration(dividerItemDecoration);
                        if (response1.body().getHomePosts().length > 0) {
                            mNewsFeedRecyclerView.setVisibility(View.VISIBLE);
                            mNewsFeedEmptycontainer.setVisibility(View.GONE);
                            mNewsFeedRecyclerView.setAdapter(new MainAdapter(NewsFeedActivity.this, response1.body().getHomePosts(), new ClickListener() {
                                @Override
                                public void OnClick(String action, int postId, int position) {
                                    if (action.equals("like")) {
                                        Forum api = RetrofitClient.getClient(NewsFeedActivity.this).create(Forum.class);
                                        Call<BaseResponse> call = api.likePost(userid, postId);
                                        call.enqueue(new Callback<BaseResponse>() {
                                            @Override
                                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                                if (response.body() != null) {
                                                    if (!response.body().getIsSuccess())
                                                        Toast.makeText(NewsFeedActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<BaseResponse> call, Throwable t) {

                                            }
                                        });

                                    } else if (action.equals("comment")) {
                                        Forum api = RetrofitClient.getClient(NewsFeedActivity.this).create(Forum.class);
                                        Call<Comments> call = api.getPostComments(userid, postId);
                                        call.enqueue(new Callback<Comments>() {
                                            @Override
                                            public void onResponse(Call<Comments> call, Response<Comments> response) {
                                                if (response.body() != null) {
                                                    if (response.body().getIsSuccess()) {
                                                        ArrayList<HomePosts> comment = new ArrayList<>(Arrays.asList(response1.body().getHomePosts()));
                                                        Intent commentIntent = new Intent(NewsFeedActivity.this, CommentsActivity.class);
                                                        commentIntent.putExtra("position", position);
                                                        commentIntent.putParcelableArrayListExtra("commentObj", comment);
                                                        commentIntent.putExtra("postID", postId);
                                                        commentIntent.putExtra("UserId", userid);
                                                        startActivity(commentIntent);
                                                    } else
                                                        Toast.makeText(NewsFeedActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Comments> call, Throwable t) {

                                            }
                                        });
                                    }
                                }
                            }));


                        } else {
                            mNewsFeedRecyclerView.setVisibility(View.GONE);
                            mNewsFeedEmptycontainer.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserHomeDetails> call, Throwable t) {
                mNewsFeedRecyclerView.setVisibility(View.GONE);
                mNewsFeedEmptycontainer.setVisibility(View.VISIBLE);
            }
        });

    }

}
