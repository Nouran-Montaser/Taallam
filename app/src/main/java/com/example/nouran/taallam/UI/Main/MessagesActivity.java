package com.example.nouran.taallam.UI.Main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nouran.taallam.Messages;
import com.example.nouran.taallam.Model.Contacts;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Notification_Fragment.NotificationAdapter;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {

    private TextView mMessagesEmptyView;
    private FloatingActionButton mMessageFab;
    private LinearLayout mMessagesEmptycontainer;
    private RecyclerView mMsgRecyclerView;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.messages);

        mMessagesEmptyView = findViewById(R.id.messages_emptyView);
        mMessageFab = findViewById(R.id.message_fab);
        mMessagesEmptycontainer = findViewById(R.id.messages_emptycontainer);
        mMsgRecyclerView = findViewById(R.id.msg_recycler_view);

        sharedPrefs = MessagesActivity.this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mUserId = sharedPrefs.getString("UserID", null);
        getContacts(mUserId);


    }

    public void getContacts(String mUserId)
    {
        Messages api = RetrofitClient.getClient(MessagesActivity.this).create(Messages.class);
        Call<Contacts> call = api.getMessagesContacts(mUserId);
        call.enqueue(new Callback<Contacts>() {
            @Override
            public void onResponse(Call<Contacts> call, Response<Contacts> response) {
                if (response.body() != null)
                {
                    if (response.body().getIsSuccess())
                    {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MessagesActivity.this);
                        mMsgRecyclerView.setHasFixedSize(true);
                        mMsgRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mMsgRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mMsgRecyclerView.addItemDecoration(dividerItemDecoration);
                        mMsgRecyclerView.setAdapter(new MessageAdapter(MessagesActivity.this,response.body().getMessagesContactList()));


                    }
                }
            }

            @Override
            public void onFailure(Call<Contacts> call, Throwable t) {

            }
        });
    }


}
