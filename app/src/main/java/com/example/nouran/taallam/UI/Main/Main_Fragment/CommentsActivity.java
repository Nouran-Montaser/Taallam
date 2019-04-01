package com.example.nouran.taallam.UI.Main.Main_Fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nouran.taallam.Forum;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Comments;
import com.example.nouran.taallam.Model.HomePosts;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {


    private TextView mMain2Comments;
    private RecyclerView mCommentsRecyclerView;
    private EditText mCommentEdt;
    private TextView mMain2Txt2;
    private TextView mMain2Likes;
    private Button mCommentBtn;
    private TextView mMain2Txt;
    private TextView mMain2Date;
    private LinearLayout mMain2FContainer;
    private CircleImageView mProfile2Image;
    private String userId;
    private int postId,position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout

        mMain2Comments = findViewById(R.id.main2_comments);
        mCommentsRecyclerView = findViewById(R.id.comments_recyclerView);
        mCommentEdt = findViewById(R.id.comment_edt);
        mMain2Txt2 = findViewById(R.id.main2_txt2);
        mMain2Likes = findViewById(R.id.main2_likes);
        mCommentBtn = findViewById(R.id.comment_btn);
        mMain2Txt = findViewById(R.id.main2_txt);
        mMain2Date = findViewById(R.id.main2_date);
        mMain2FContainer = findViewById(R.id.main2_FContainer);
        mProfile2Image = findViewById(R.id.profile2_image);

        userId = getIntent().getStringExtra("UserId");
        postId = getIntent().getIntExtra("postID",0);
        position = getIntent().getIntExtra("position",0);

        if (getIntent().getParcelableArrayListExtra("commentObj") != null) {
            ArrayList<HomePosts> homePosts = getIntent().getParcelableArrayListExtra("commentObj");
            Picasso.get().load(homePosts.get(position).getTeacherPictureURL()).placeholder(R.drawable.pp).
                    error(R.drawable.pp).into(mProfile2Image);
            mMain2Likes.setText(homePosts.get(position).getLikesNumber()+"");
            mMain2Txt.setText(homePosts.get(position).getTeacherName());
            mMain2Date.setText(homePosts.get(position).getDatetime());
            mMain2Txt2.setText(homePosts.get(position).getBody());
            mMain2Comments.setText(homePosts.get(position).getCommentsNumber()+"");
        }

        mCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCommentEdt.getText().equals(""))
                {

                }

            }
        });

        getComments(userId,postId);
    }

    private void getComments(String userId , int postId) {
        Forum api = RetrofitClient.getClient(CommentsActivity.this).create(Forum.class);
        Call<Comments> call = api.getPostComments(userId,postId);
        call.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if (response.body() != null)
                {
                    if (response.body().getIsSuccess())
                    {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentsActivity.this);
                        mCommentsRecyclerView.setHasFixedSize(true);
                        mCommentsRecyclerView.setLayoutManager(linearLayoutManager);

                        mCommentsRecyclerView.setAdapter(new CommentsAdapter(CommentsActivity.this , response.body().getPostComments()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {

            }
        });
    }


}
