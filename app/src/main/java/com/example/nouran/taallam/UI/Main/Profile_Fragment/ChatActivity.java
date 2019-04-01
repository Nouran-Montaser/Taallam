package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Messages;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.BookFollow;
import com.example.nouran.taallam.Model.Message;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BookDetailActivity;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends Activity {

    private String mChatUser;
    private Toolbar mToolbar;
    private String mChatUserName;
    private TextView mTitleView;
    private TextView mLastSeenView;
    private CircleImageView mProfileImg;
    private RecyclerView mMessagesist;
    private LinearLayoutManager mLinearLayoutManager;
    private ChatAdapter mAdapter;
    private EditText mChatMessageView;
    private Button mChatSendBtn;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mToolbar = findViewById(R.id.chat_app_bar);
        setActionBar(mToolbar);

        ActionBar actionBar = getActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = layoutInflater.inflate(R.layout.custom_chat_bar, null);
        actionBar.setCustomView(actionBarView);


        //---------- Custom Action Bar Items ----

        mTitleView = findViewById(R.id.custom_bar_title);
        mProfileImg = findViewById(R.id.customBarImg);


        mChatSendBtn = findViewById(R.id.ChatSendButton);
        mChatMessageView = findViewById(R.id.ChatMessage_edt);


        mMessagesist = findViewById(R.id.message_list);

        getActionBar().setTitle("Nouran");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mUserId = sharedPrefs.getString("UserID", null);

        String userId = getIntent().getStringExtra("userID");

        getAllMessages();

        mChatSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mChatMessageView.getText().equals("")) {
                    Messages api = RetrofitClient.getClient(ChatActivity.this).create(Messages.class);
                    Call<BaseResponse> call = api.sendMessage(mUserId, new Message(userId, mChatMessageView.getText().toString()));
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body() != null) {
                                if (response.body().getIsSuccess()) {
                                    mAdapter.notifyDataSetChanged();
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

    private void getAllMessages() {
        mLinearLayoutManager = new LinearLayoutManager(ChatActivity.this);
        mMessagesist.setHasFixedSize(true);
        mMessagesist.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ChatAdapter(ChatActivity.this);
        mMessagesist.setAdapter(mAdapter);
    }
}
