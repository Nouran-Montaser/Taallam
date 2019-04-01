package com.example.nouran.taallam.UI.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nouran.taallam.R;

import java.util.Objects;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.messages);

    }



}
