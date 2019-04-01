package com.example.nouran.taallam.UI.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.nouran.taallam.R;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mSearchRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchRecyclerView = findViewById(R.id.search_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        mSearchRecyclerView.setHasFixedSize(true);
        mSearchRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mSearchRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mSearchRecyclerView.addItemDecoration(dividerItemDecoration);
        if (getIntent().getParcelableArrayListExtra("SEARCH_USERS") != null) {
        mSearchRecyclerView.setAdapter(new SearchAdapter(SearchActivity.this,
                getIntent().getParcelableArrayListExtra("SEARCH_USERS")));
        }

    }




}
