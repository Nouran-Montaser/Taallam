package com.example.nouran.taallam.UI.Wellcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Model.Book;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BookDetailActivity;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksActivity;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksClickListener;
import com.example.nouran.taallam.UI.Main.MainActivity;
import com.example.nouran.taallam.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wellcome2Activity extends AppCompatActivity {

    private Button mFinishButton;
    private RecyclerView mRecyclerView;
    private LinearLayout mLayoutContainer;
    private Wellcome2Adapter wellcomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome2);


        initializer();


    }

    private void initializer() {

        int courseId = getIntent().getIntExtra("CourseID",0);
        if (courseId != 0)
            getBooks(courseId);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.wellcome);

        mFinishButton = findViewById(R.id.wellcome2_finish_button);
        mRecyclerView = findViewById(R.id.welcome2_recyclerView);
        mLayoutContainer = findViewById(R.id.wellcome2_layout_container);

        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mMaimIntent = new Intent(Wellcome2Activity.this , MainActivity.class);
                startActivity(mMaimIntent);
            }
        });
    }
    public void getBooks(int courseId) {


        Books api = RetrofitClient.getClient(Wellcome2Activity.this).create(Books.class);
        Call<Book> call = api.getBooksList(courseId);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Wellcome2Activity.this);
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mRecyclerView.addItemDecoration(dividerItemDecoration);
                        mRecyclerView.setAdapter(new Wellcome2Adapter(Wellcome2Activity.this,response.body().getBooksList(),
                                new BooksClickListener() {
                            @Override
                            public void OnClick(int Id) {
                                    Intent categoryIntent = new Intent(Wellcome2Activity.this, BookDetailActivity.class);
                                    categoryIntent.putExtra("BookID",Id);
                                    startActivity(categoryIntent);
                            }
                        }));

                    }
                }

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });

    }

}
