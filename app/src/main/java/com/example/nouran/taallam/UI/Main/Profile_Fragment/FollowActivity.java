package com.example.nouran.taallam.UI.Main.Profile_Fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouran.taallam.Model.BaseResponse;
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
    private String mUserId = null;
    private String userid;
    private ArrayList<SixFollowers> sixfollowers;
    private boolean isFollow = false;
    private TextView mFnoBooksTxt;
    private TextView mFnoAboutTxt;
    private TextView mFaboutTxt;


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
        mFnoBooksTxt = findViewById(R.id.fno_books_txt);
        mFnoAboutTxt = findViewById(R.id.fno_about_txt);
        mFaboutTxt = findViewById(R.id.fabout_txt);

        sharedPrefs = FollowActivity.this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        mUserId = sharedPrefs.getString("UserID", null);

        if (getIntent().getStringExtra("followerObj") != null) {
            userid = getIntent().getStringExtra("followerObj");
        }
        getAlldetails(userid, mUserId);

        mMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mChatIntent = new Intent(FollowActivity.this, ChatActivity.class);
                mChatIntent.putExtra("userID", userid);
                startActivity(mChatIntent);
            }
        });

//        getFollowers();

        mFollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mFollowBtn.getText().toString().equals("UnFollow")) {
                    Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
                    Log.i("TSETLL", mUserId + "  " + userid);
                    Call<BaseResponse> call = api.setUserFollower(mUserId, userid);
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
                    Log.i("TSETLL", mUserId + "  " + userid);
                    Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
                    Call<BaseResponse> call = api.unfollowUser(mUserId, userid);
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body() != null) {
                                if (!response.body().getIsSuccess()) {
                                    Toast.makeText(FollowActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FollowActivity.this, "UnFollow", Toast.LENGTH_SHORT).show();
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

//    private void getFollowers() {
//        Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
//        Call<UserProfileDetails> call = api.getUserProfileDetails(mUserId, userid);
//        call.enqueue(new Callback<UserProfileDetails>() {
//            @Override
//            public void onResponse(Call<UserProfileDetails> call, Response<UserProfileDetails> response) {
//                if (response.body() != null) {
//                    if (response.body().getIsSuccess()) {
//                        for (int i=0;i<response.body().getFollowersNumber();i++)
//                        {
//                            if (response.body().getSixFollowers()[i].getUserID().equals(""))
//                            {
//                                isFollow = true;
//                            }
////                            isFollow = response.body().getIsFollowing();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserProfileDetails> call, Throwable t) {
//
//            }
//        });
//    }


    private void getAlldetails(String userid, String userID) {
        Users api = RetrofitClient.getClient(FollowActivity.this).create(Users.class);
        Log.i("PPPPPPPPPL", userID + "    " + userid);
        Call<UserProfileDetails> call = api.getUserProfileDetails(userid, userID);
        call.enqueue(new Callback<UserProfileDetails>() {
            @Override
            public void onResponse(Call<UserProfileDetails> call, Response<UserProfileDetails> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {
                        mFollowName.setText(response.body().getUserName());
                        Picasso.get().load(response.body().getUserPictureURL()).placeholder(R.drawable.pp).
                                error(R.drawable.pp).into(mFollowPic);


                        if (response.body().getAbout() != null) {
                            mFnoAboutTxt.setVisibility(View.GONE);
                            mFaboutTxt.setVisibility(View.VISIBLE);
                        }else {
                            mFnoAboutTxt.setVisibility(View.VISIBLE);
                            mFaboutTxt.setVisibility(View.GONE);
                        }

//                        isFollow = response.body().getIsFollowing();

                        if (response.body().getFollowersNumber() == 0) {
                            mFfollowersRecyclerView.setVisibility(View.GONE);
                            mFfollowersPtxt.setVisibility(View.VISIBLE);
                        }
                        if (response.body().getFollowersNumber() > 0) {
                            mFfollowersPtxt.setVisibility(View.VISIBLE);//                            for (int i =0 ; i<response.body().getFollowersNumber();i++) {
//                                if (userid.equals(response.body().getSixFollowers()[i].getUserID()))
//                                    isFollow = response.body().getIsFollowing();
//                            }
                            mFfollowersRecyclerView.setVisibility(View.VISIBLE);
                            LinearLayoutManager mFollowersLayout = new LinearLayoutManager(FollowActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            mFfollowersRecyclerView.setHasFixedSize(true);
                            mFfollowersRecyclerView.setLayoutManager(mFollowersLayout);
                            Log.i("getFollowersListLen", response.body().getSixFollowers().length + "");
                            mFfollowersRecyclerView.setAdapter(new FollowerAdapter(FollowActivity.this,
                                    response.body().getSixFollowers(), null));
                        }
                        Log.i("PLPLPL", response.body().getIsFollowing() + "  " + userid + "    " + mUserId);
                        if (response.body().getFourBooks().length == 0)
                        {
                            mFnoBooksTxt.setVisibility(View.GONE);
                            mStudyRecyclerView.setVisibility(View.VISIBLE);
                        }if (response.body().getFourBooks().length > 0) {
                            mFnoBooksTxt.setVisibility(View.GONE);
                            mStudyRecyclerView.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FollowActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            mStudyRecyclerView.setHasFixedSize(true);
                            mStudyRecyclerView.setLayoutManager(linearLayoutManager);

                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mStudyRecyclerView.getContext(),
                                    linearLayoutManager.getOrientation());
                            mStudyRecyclerView.addItemDecoration(dividerItemDecoration);
                            mStudyRecyclerView.setAdapter(new BooksAdapter(FollowActivity.this, response.body().getFourBooks()));

                        }
                        if (!isFollow)
                            mFollowBtn.setText(R.string.follow);
                        else
                            mFollowBtn.setText(R.string.unfollow);

                    } else
                        Log.i("LLLL", response.body().getErrorMessage());
                }
            }

            @Override
            public void onFailure(Call<UserProfileDetails> call, Throwable t) {

            }
        });
    }
}
