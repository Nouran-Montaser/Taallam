package com.example.nouran.taallam.UI.Main.Category_Fragment.Books;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Date;
import com.example.nouran.taallam.Model.BookData;
import com.example.nouran.taallam.Model.BookFollow;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.squareup.picasso.Picasso;


import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private TextView mTeacherLable;
    private TextView mBookDName;
    private TextView mTeacherName;
    private TextView mBookDLevel;
    private TextView mParticipantsLable;
    private TextView mDurationTxt;
    private TextView mFollowerTxt;
    private TextView mTeacherTxt2;
    private TextView mFollowersLable;
    private TextView mDurationLable;
    private TextView mAboutBookTxt;
    private CircleImageView mTeacherImage;
    private TextView mAboutBookLable;
    private TextView mParticipantsTxt;
    private String bookUrl, bookName;
    private int bookId;
    private Button mUnfollowBtn;
    private Button mFollowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        mBookDName = findViewById(R.id.Book_DName);
        mTeacherName = findViewById(R.id.teacher_name);
        mBookDLevel = findViewById(R.id.Book_DLevel);
        mDurationTxt = findViewById(R.id.duration_txt);
        mFollowerTxt = findViewById(R.id.follower_txt);
        mTeacherTxt2 = findViewById(R.id.teacher_txt2);
        mAboutBookTxt = findViewById(R.id.aboutBook_txt);
        mTeacherImage = findViewById(R.id.teacher_image);
        mParticipantsTxt = findViewById(R.id.Participants_txt);
        mUnfollowBtn = findViewById(R.id.unfollow_btn);
        mFollowBtn = findViewById(R.id.follow_btn);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.book_detail);
        bookId = getIntent().getIntExtra("BookID", 0);

        sharedPrefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mUserId = sharedPrefs.getString("UserID", null);

        getBookDetail(bookId, mUserId);

        mFollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow(mUserId, bookId);
            }
        });

        mUnfollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unFollow(mUserId, bookId);
            }
        });

    }

    private void unFollow(String mUserId, int bookId) {
        Books api = RetrofitClient.getClient(BookDetailActivity.this).create(Books.class);
        Call<BookFollow> call = api.unSetBookFollower(mUserId, bookId);
        call.enqueue(new Callback<BookFollow>() {
            @Override
            public void onResponse(Call<BookFollow> call, Response<BookFollow> response) {
                if (response.body().getIsSuccess()) {
                    Toast.makeText(BookDetailActivity.this, getString(R.string.unfollow_msg) + bookName, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(BookDetailActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BookFollow> call, Throwable t) {

            }
        });
    }

    private void follow(String userId, int bookId) {
        Books api = RetrofitClient.getClient(BookDetailActivity.this).create(Books.class);
        Call<BookFollow> call = api.setBookFollower(userId, bookId);
        Log.i("PPLLPPLLPP",userId+"    "+bookId);
        call.enqueue(new Callback<BookFollow>() {
            @Override
            public void onResponse(Call<BookFollow> call, Response<BookFollow> response) {
                Log.i("FOLLOWWW", response.body() + "");
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {
                        Toast.makeText(BookDetailActivity.this, getString(R.string.follow_msg) + bookName, Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(BookDetailActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookFollow> call, Throwable t) {
                Toast.makeText(BookDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getBookDetail(int bookId, String mUserId) {
        Books api = RetrofitClient.getClient(BookDetailActivity.this).create(Books.class);
        Call<BookData> call = api.getBookDetails(mUserId, bookId);
        call.enqueue(new Callback<BookData>() {
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {
                        mTeacherName.setText(response.body().getBookDetails().getTeacherName());
                        mBookDName.setText(response.body().getBookDetails().getName());
                        mBookDLevel.setText(getString(R.string.levels) + response.body().getBookDetails().getLevelNumber());

                        if (response.body().getBookDetails().getFromDate() != null) {
                            String duration = Date.format2Date(response.body().getBookDetails().getFromDate()) + "   -   " +
                                    Date.format2Date(response.body().getBookDetails().getToDate());
                            mDurationTxt.setText(duration);
                        }

                        if (response.body().getBookDetails().getParticipantsNumber() == 1)
                            mParticipantsTxt.setText(response.body().getBookDetails().getParticipantsNumber() + getString(R.string.participants));
                        else
                            mParticipantsTxt.setText(response.body().getBookDetails().getParticipantsNumber() + getString(R.string.participants));

                        if (response.body().getBookDetails().getFollowersNumber() == 1)
                            mFollowerTxt.setText(response.body().getBookDetails().getFollowersNumber() + getString(R.string.followers));
                        else
                            mFollowerTxt.setText(response.body().getBookDetails().getFollowersNumber() + getString(R.string.followers));

                        mAboutBookTxt.setText(response.body().getBookDetails().getAbout());

                        mTeacherName.setText(response.body().getBookDetails().getTeacherName());
                        mTeacherTxt2.setText(response.body().getBookDetails().getTeacherTitle());

                        Picasso.get().load(response.body().getBookDetails().getTeacherPicture()).placeholder(R.drawable.pp).
                                error(R.drawable.pp).into(mTeacherImage);

                        bookUrl = response.body().getBookDetails().getLink();
                        bookName = response.body().getBookDetails().getName();
                    }
                }

            }

            @Override
            public void onFailure(Call<BookData> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.read_book: {
                Intent mReadBookIntent = new Intent(BookDetailActivity.this, ReadBookActivity.class);
                mReadBookIntent.putExtra("BOOK_Name", bookName);
                mReadBookIntent.putExtra("BOOK_URL", bookUrl);
                startActivity(mReadBookIntent);
                break;
            }
            case R.id.interactions: {
                Intent mInteractionBookIntent = new Intent(BookDetailActivity.this, BookInteractionActivity.class);
                mInteractionBookIntent.putExtra("BOOK_ID", bookId);
                startActivity(mInteractionBookIntent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}