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
import android.util.Log;
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
import com.example.nouran.taallam.Model.BooksList;
import com.example.nouran.taallam.Model.Course;
import com.example.nouran.taallam.Model.SixFollowers;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Wellcome.Wellcome2Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksActivity extends AppCompatActivity {

    private RecyclerView mBooksRecyclerView;
    private TextView mSortType;
    private TextView mSortBy;
    private ImageView mSortPopup;
    private int courseId;
    private ArrayList<BooksList> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        courseId = getIntent().getIntExtra("CourseID", 0);

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

    @Override
    protected void onResume() {
        super.onResume();
        if (courseId != 0) {
            books = getBooks("Levels");
//                setRecyclerView(books);
        }
    }

    private void showSortPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // Inflate the menu from xml
        popup.inflate(R.menu.popup_sort);
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_levels: {
                        mSortBy.setText(getString(R.string.sort_by_levels));
                        Toast.makeText(BooksActivity.this, "Levels", Toast.LENGTH_SHORT).show();
                        if (books != null) {
                            books = getBooks("Levels");
                            Collections.sort(books);

                        return true;
                        }
                    }
                    case R.id.menu_subscribers: {
                        mSortBy.setText(R.string.sort_by_subscribes);
                        if (books != null) {
                            books = getBooks("Subscriber");
                            Collections.sort(books);
                        }
                        return true;
                    }
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

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.book_search).getActionView();

        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setIconifiedByDefault(true);
        searchView.setIconified(true);

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

    public ArrayList<BooksList> getBooks(String s) {

        Books api = RetrofitClient.getClient(BooksActivity.this).create(Books.class);
        Call<Book> call = api.getBooksList(courseId);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.i("POPOPO", "HERE3");
                if (response.body() != null) {
                    Log.i("POPOPO", "HERE4");
                    if (response.body().getIsSuccess()) {
                        Log.i("POPOPO", "HERE5");
                        for (int i = 0; i < response.body().getBooksList().length; i++)
                            response.body().getBooksList()[i].setSortBy(s);
                        books = new ArrayList<>(Arrays.asList(response.body().getBooksList()));
                        setRecyclerView(books);

                    }
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
        return books;
    }

    public void setRecyclerView(ArrayList<BooksList> booksLists) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BooksActivity.this);
        mBooksRecyclerView.setHasFixedSize(true);
        mBooksRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mBooksRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mBooksRecyclerView.addItemDecoration(dividerItemDecoration);
        mBooksRecyclerView.setAdapter(new Wellcome2Adapter(BooksActivity.this, booksLists, new BooksClickListener() {
            @Override
            public void OnClick(int position, int Id) {
                Intent categoryIntent = new Intent(BooksActivity.this, BookDetailActivity.class);
                categoryIntent.putExtra("BookID", Id);
                startActivity(categoryIntent);
            }
        }, ""));

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }}
