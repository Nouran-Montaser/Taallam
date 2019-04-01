package com.example.nouran.taallam.UI.Wellcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.nouran.taallam.Courses;
import com.example.nouran.taallam.Model.Course;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksActivity;
import com.example.nouran.taallam.UI.Main.Category_Fragment.CategoryClickListener;
import com.example.nouran.taallam.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WellcomeActivity extends AppCompatActivity {

    private Button mNextButton;
    private RecyclerView mRecyclerView;
    private WellcomeAdapter wellcomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        initializer();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mWellcomeIntent = new Intent(WellcomeActivity.this,Wellcome2Activity.class);
                startActivity(mWellcomeIntent);
            }
        });

    }

    private void initializer() {

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.wellcome);

        mNextButton = findViewById(R.id.wellcome1_next_button);
        mRecyclerView = findViewById(R.id.welcome1_recyclerView);
        getCourse();

    }

    private void getCourse() {

        Courses api = RetrofitClient.getClient(WellcomeActivity.this).create(Courses.class);
        Call<Course> call = api.getCoursesList("");
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {

                if (response.isSuccessful()) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WellcomeActivity.this);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                            linearLayoutManager.getOrientation());
                    mRecyclerView.addItemDecoration(dividerItemDecoration);
                    mRecyclerView.setAdapter(new WellcomeAdapter(WellcomeActivity.this, response.body().getCoursesList()
                            , new CategoryClickListener() {
                        @Override
                        public void OnClick(int position) {
                            Intent categoryIntent = new Intent(WellcomeActivity.this , BooksActivity.class);
                            categoryIntent.putExtra("CourseID", position);
                            startActivity(categoryIntent);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {

            }
        });
    }

}
