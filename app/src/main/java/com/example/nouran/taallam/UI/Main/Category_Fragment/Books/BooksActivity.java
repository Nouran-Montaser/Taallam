package com.example.nouran.taallam.UI.Main.Category_Fragment.Books;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Courses;
import com.example.nouran.taallam.Model.Book;
import com.example.nouran.taallam.Model.Course;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Wellcome.Wellcome2Adapter;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksActivity extends AppCompatActivity {

    private RecyclerView mBooksRecyclerView;
    private TextView mSortType;
    private TextView mSortBy;
    private ImageView mSortPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        int courseId = getIntent().getIntExtra("CourseID", 0);
        if (courseId != 0)
            getBooks(courseId);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(R.string.wellcome);

        mBooksRecyclerView = findViewById(R.id.books_recyclerView);
        mSortType = findViewById(R.id.sortType);
        mSortBy = findViewById(R.id.sortBy);


        mSortPopup = findViewById(R.id.sort_popup);
        mSortPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortPopup(v);
            }
        });
    }

    private void showSortPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // Inflate the menu from xml
        popup.inflate(R.menu.popup_sort);
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_levels:
                        Toast.makeText(BooksActivity.this, "Levels", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_subscribers:
                        Toast.makeText(BooksActivity.this, getResources().getString(R.string.number_of_subscribers), Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_book_search, menu);

        menu.findItem(R.id.book_search).expandActionView(); // Expand the search menu item in order to show by default the query
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.book_search).getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(true);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(BooksActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void getBooks(int courseId) {

        Books api = RetrofitClient.getClient(BooksActivity.this).create(Books.class);
        Call<Book> call = api.getBooksList(courseId);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BooksActivity.this);
                        mBooksRecyclerView.setHasFixedSize(true);
                        mBooksRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mBooksRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mBooksRecyclerView.addItemDecoration(dividerItemDecoration);
                        mBooksRecyclerView.setAdapter(new Wellcome2Adapter(BooksActivity.this, response.body().getBooksList(), new BooksClickListener() {
                            @Override
                            public void OnClick(int Id) {
                                Intent categoryIntent = new Intent(BooksActivity.this, BookDetailActivity.class);
                                categoryIntent.putExtra("BookID", Id);
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
