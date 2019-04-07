package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Messages;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.BookFollow;
import com.example.nouran.taallam.Model.ConversationMessage;
import com.example.nouran.taallam.Model.Message;
import com.example.nouran.taallam.Model.MessagesContactList;
import com.example.nouran.taallam.Model.UserMessage;
import com.example.nouran.taallam.Model.UserProfileDetails;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BookDetailActivity;
import com.example.nouran.taallam.Users;
import com.squareup.picasso.Picasso;

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
    private String conversationUserID;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        conversationUserID = getIntent().getStringExtra("ConversationUserID");
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


        sharedPrefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mUserId = sharedPrefs.getString("UserID", null);

        userId = getIntent().getStringExtra("userID");

        getDetail();
        getConversationMessages(userId, 0, mUserId);

        mChatSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mChatMessageView.getText().equals("")) {
                    Messages api = RetrofitClient.getClient(ChatActivity.this).create(Messages.class);
                    Call<BaseResponse> call = api.sendMessage(new UserMessage(new Message(userId, mChatMessageView.getText().toString()), mUserId));
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.body() != null) {
                                if (response.body().getIsSuccess()) {
                                    Toast.makeText(ChatActivity.this, "Send ", Toast.LENGTH_SHORT).show();
                                    getConversationMessages(userId, 0, mUserId);
                                    if (mAdapter != null)
                                        mAdapter.notifyDataSetChanged();
                                    mChatMessageView.setText("");
                                } else
                                    Toast.makeText(ChatActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
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

    public void getDetail() {
        Users api = RetrofitClient.getClient(ChatActivity.this).create(Users.class);
        Call<UserProfileDetails> call = api.getUserProfileDetails(userId, userId);
        call.enqueue(new Callback<UserProfileDetails>() {
            @Override
            public void onResponse(Call<UserProfileDetails> call, Response<UserProfileDetails> response) {
                if (response.body() != null)
                {
                    if (response.body().getIsSuccess())
                    {
                        mMessagesist.setAdapter(mAdapter);
                        mTitleView.setText(response.body().getUserName());
                        getActionBar().setTitle(response.body().getUserName());
                        getActionBar().setDisplayHomeAsUpEnabled(true);
                        Picasso.get().load(response.body().getUserPictureURL()).placeholder(R.drawable.pp).
                                error(R.drawable.pp).into(mProfileImg);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileDetails> call, Throwable t) {

            }
        });
    }

    private void getConversationMessages(String userId, int i, String mUserId) {
        Messages api = RetrofitClient.getClient(ChatActivity.this).create(Messages.class);
        Call<ConversationMessage> call = api.conversationMessages(userId, i, mUserId);
        call.enqueue(new Callback<ConversationMessage>() {
            @Override
            public void onResponse(Call<ConversationMessage> call, Response<ConversationMessage> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {
                        Log.i("PLPLPLPL", userId + "   " + mUserId);
                        mLinearLayoutManager = new LinearLayoutManager(ChatActivity.this);
                        mLinearLayoutManager.setReverseLayout(true);
                        mLinearLayoutManager.setStackFromEnd(true);
                        mMessagesist.setHasFixedSize(true);
                        mMessagesist.setLayoutManager(mLinearLayoutManager);
                        mAdapter = new ChatAdapter(ChatActivity.this, response.body().getConversationMessages());
                        mMessagesist.setAdapter(mAdapter);
                        mTitleView.setText(response.body().getConversationUserName());
                        getActionBar().setTitle(response.body().getConversationUserName());
                        getActionBar().setDisplayHomeAsUpEnabled(true);
                        Picasso.get().load(response.body().getConversationUserImageUrl()).placeholder(R.drawable.pp).
                                error(R.drawable.pp).into(mProfileImg);


                    }
                }
            }

            @Override
            public void onFailure(Call<ConversationMessage> call, Throwable t) {
            }
        });
    }


}
