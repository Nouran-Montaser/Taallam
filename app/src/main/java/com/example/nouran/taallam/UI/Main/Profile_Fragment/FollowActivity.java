package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Follow.AllFollowersDetails;
import com.example.nouran.taallam.Model.Follow.Followers;
import com.example.nouran.taallam.Model.HomePosts;
import com.example.nouran.taallam.Model.SixFollowers;
import com.example.nouran.taallam.Model.UserProfileDetails;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowActivity extends AppCompatActivity {

    private Button mMsgBtn;
    private RecyclerView mStudyRecyclerView;
    private Button mNewsFeedButton;
    private TextView mFfollowersLable;
    private RecyclerView mFfollowersRecyclerView;
    private Button mFollowBtn;
    private TextView mStudyNowLable;
    private TextView mFfollowersPtxt;
    private TextView mFollowName;
    private CircleImageView mFollowPic;
    private int position;
    private String userID;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        mMsgBtn = findViewById(R.id.msg_btn);
        mStudyRecyclerView = findViewById(R.id.study_recyclerView);
        mNewsFeedButton = findViewById(R.id.newsFeed_button);
        mFfollowersLable = findViewById(R.id.Ffollowers_lable);
        mFfollowersRecyclerView = findViewById(R.id.Ffollowers_recyclerView);
        mFollowBtn = findViewById(R.id.follow_btn);
        mStudyNowLable = findViewById(R.id.study_now_lable);
        mFfollowersPtxt = findViewById(R.id.Ffollowers_Ptxt);
        mFollowName = findViewById(R.id.follow_name);
        mFollowPic = findViewById(R.id.follow_pic);

        sharedPrefs = FollowActivity.this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mUserId = sharedPrefs.getString("UserID", null);

        position = getIntent().getIntExtra("position", 0);

        if (getIntent().getParcelableArrayListExtra("followerObj") != null) {
            ArrayList<SixFollowers> sixFollowers = getIntent().getParcelableArrayListExtra("followerObj");
            Picasso.get().load(sixFollowers.get(position).getUserPictureURL()).placeholder(R.drawable.pp).
                    error(R.drawable.pp).into(mFollowPic);
            userID = sixFollowers.get(position).getUserID();
            getAlldetails(sixFollowers.get(position).getUserID());
        }


        mMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mChatIntent = new Intent(FollowActivity.this, ChatActivity.class);
                mChatIntent.putExtra("userID", userID);
                startActivity(mChatIntent);
            }
        });

        mFollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
                Call<BaseResponse> call = api.setUserFollower(mUserId, userID);
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body() != null) {
                            if (!response.body().getIsSuccess()) {
                                Toast.makeText(FollowActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(FollowActivity.this, R.string.follow_msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                    }
                });
            }
        });

        mFollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mFollowBtn.getText().toString().equals("UnFollow")) {
                    Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
                    Call<BaseResponse> call = api.setUserFollower(mUserId, userID);
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body() != null) {
                                if (!response.body().getIsSuccess()) {
                                    Toast.makeText(FollowActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FollowActivity.this, R.string.follow_msg, Toast.LENGTH_SHORT).show();
                                    mFollowBtn.setText("UnFollow");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                        }
                    });
                } else {
                    Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
                    Call<BaseResponse> call = api.unfollowUser(mUserId, userID);
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body() != null) {
                                if (!response.body().getIsSuccess()) {
                                    Toast.makeText(FollowActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FollowActivity.this, R.string.follow_msg, Toast.LENGTH_SHORT).show();
                                    mFollowBtn.setText("Follow");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void getAlldetails(String userID) {
        Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
        Call<Followers> call = api.GetUserFollowers(userID);
        call.enqueue(new Callback<Followers>() {
            @Override
            public void onResponse(Call<Followers> call, Response<Followers> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {
                        for (int i = 0; i < response.body().getAllFollowersDetails().length; i++) {
                            if (response.body().getAllFollowersDetails()[i].equals(userID)) {
                                mFollowName.setText(response.body().getAllFollowersDetails()[i].getName());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Followers> call, Throwable t) {

            }
        });
    }
}
