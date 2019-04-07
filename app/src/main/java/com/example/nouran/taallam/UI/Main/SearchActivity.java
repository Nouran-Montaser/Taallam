package com.example.nouran.taallam.UI.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nouran.taallam.R;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mSearchRecyclerView;
    private TextView mEmptySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchRecyclerView = findViewById(R.id.search_recyclerView);
        mEmptySearch = findViewById(R.id.empty_search);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.search_title);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        mSearchRecyclerView.setHasFixedSize(true);
        mSearchRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mSearchRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mSearchRecyclerView.addItemDecoration(dividerItemDecoration);
        if (getIntent().getParcelableArrayListExtra("SEARCH_USERS") != null) {
            if (getIntent().getParcelableArrayListExtra("SEARCH_USERS").size() == 0) {
                Log.i("PLPLPLO", "HERE");
                mSearchRecyclerView.setVisibility(View.GONE);
                mEmptySearch.setVisibility(View.VISIBLE);
            } else {
                mSearchRecyclerView.setVisibility(View.VISIBLE);
                mSearchRecyclerView.setAdapter(new SearchAdapter(SearchActivity.this,
                        getIntent().getParcelableArrayListExtra("SEARCH_USERS")));
                mEmptySearch.setVisibility(View.GONE);
            }
        }


    }


}
