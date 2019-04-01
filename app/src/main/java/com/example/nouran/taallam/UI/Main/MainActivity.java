package com.example.nouran.taallam.UI.Main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nouran.taallam.Model.SearchUsers;
import com.example.nouran.taallam.Model.User;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.CategoryFragment;
import com.example.nouran.taallam.UI.Main.Main_Fragment.MainFragment;
import com.example.nouran.taallam.UI.Main.Notification_Fragment.NotificationFragment;
import com.example.nouran.taallam.UI.Main.Profile_Fragment.ProfileFragment;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.Users;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your fragments here
        final MainFragment mMainFragment = new MainFragment();
        final NotificationFragment mNotificationFragment = new NotificationFragment();
        final CategoryFragment mCategoryFragment = new CategoryFragment();
        final ProfileFragment mProfileFragment = new ProfileFragment();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        // handle navigation selection
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = mMainFragment;
                        switch (item.getItemId()) {
                            case R.id.main_item:
                                fragment = mMainFragment;
                                break;
                            case R.id.categories_item:
                                fragment = mCategoryFragment;
                                break;
                            case R.id.profile_item:
                                fragment = mProfileFragment;
                                break;
                            case R.id.notifications_item:
                                fragment = mNotificationFragment;
                            default:
                                break;
                        }
                        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
                        return true;
                    }
                });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.main_item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("OOOOOO",query);

                Users api = RetrofitClient.getClient(MainActivity.this).create(Users.class);
                Call<User> call = api.searchUser(query);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body().getIsSuccess())
                        {
                            ArrayList<SearchUsers> searchUsers = new ArrayList<>(Arrays.asList(response.body().getSearchUsers()));
                            Intent searchIntent = new Intent(MainActivity.this , SearchActivity.class);
                            searchIntent.putParcelableArrayListExtra("SEARCH_USERS",searchUsers);
                            Log.i("PPPPPPPPPPPP",searchUsers.size()+"");
                            startActivity(searchIntent);
                            // "UserID": "f9a6dfc8-03ea-416c-afbb-d98a01ccd093",
                            // "UserName": "test12345",
                            // "UserPictureURL": "http://www.adweek.com/socialtimes/wp-content/uploads/sites/2/2011/10/twitter-default-avatar-egg.jpg",

                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

                return true;
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
        switch (item.getItemId()) {
            case R.id.messages: {
                Intent mMessagesIntent = new Intent(MainActivity.this,MessagesActivity.class);
                startActivity(mMessagesIntent);
            }

        }
        return super.onOptionsItemSelected(item);
    }

}

