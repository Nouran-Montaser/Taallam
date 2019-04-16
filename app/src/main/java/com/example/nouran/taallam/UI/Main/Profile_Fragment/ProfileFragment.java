package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.SixFollowers;
import com.example.nouran.taallam.Model.UserProfileDetails;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Login.LoginActivity;
import com.example.nouran.taallam.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {


    private TextView mProfileName;
    private TextView mLearnNowLable;
    private RecyclerView mBooksRecyclerView;
    private TextView mFollowersPtxt;
    private TextView mAboutTxt;
    private CircleImageView mProfilePic;
    private FloatingActionButton mEditPfab;
    private TextView mFollowersPlable;
    private TextView mAboutLable;
    private Button mLogoutButton;
    private RecyclerView mFollowersRecyclerView;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private String mUserId;
    private String user_name, about;
    private TextView mNoFollowersTxt;
    private TextView mNoAboutTxt;
    private TextView mNoBooksTxt;


    public ProfileFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mMainView = inflater.inflate(R.layout.fragment_profile, container, false);

        mProfileName = mMainView.findViewById(R.id.profile_name);
        mLearnNowLable = mMainView.findViewById(R.id.learn_now_lable);
        mBooksRecyclerView = mMainView.findViewById(R.id.books_recyclerView);
        mFollowersPtxt = mMainView.findViewById(R.id.followers_Ptxt);
        mAboutTxt = mMainView.findViewById(R.id.about_txt);
        mProfilePic = mMainView.findViewById(R.id.profile_pic);
        mEditPfab = mMainView.findViewById(R.id.edit_Pfab);
        mFollowersPlable = mMainView.findViewById(R.id.followers_Plable);
        mAboutLable = mMainView.findViewById(R.id.about_lable);
        mLogoutButton = mMainView.findViewById(R.id.logout_button);
        mFollowersRecyclerView = mMainView.findViewById(R.id.followers_recyclerView);
        mNoFollowersTxt = mMainView.findViewById(R.id.no_followers_txt);
        mNoAboutTxt = mMainView.findViewById(R.id.no_about_txt);
        mNoBooksTxt = mMainView.findViewById(R.id.no_books_txt);

        sharedPrefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        mUserId = sharedPrefs.getString("UserID", null);
//        getFollowers(mUserId);

        getBooks(mUserId);

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users api = RetrofitClient.getClient(getActivity()).create(Users.class);
                Call<BaseResponse> call = api.logOut(mUserId,"123456789");
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body().getIsSuccess()) {
                            SharedPreferences.Editor sharedPrefsEditor;
                            sharedPrefsEditor = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            sharedPrefsEditor.putString("UserID", null);
                            sharedPrefsEditor.apply();
                            Intent logOutIntent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(logOutIntent);
                            getActivity().finish();//when we are don't need to go back (no back button)
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
            }
        });

        mEditPfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getActivity(), EditActivity.class);
                editIntent.putExtra("USER_ID", mUserId);
                editIntent.putExtra("Name", user_name);
                editIntent.putExtra("About", about);
                startActivity(editIntent);
            }
        });
        return mMainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBooks(mUserId);
    }

    private void getBooks(String mUserId) {

        Users api = RetrofitClient.getClient(getActivity()).create(Users.class);
        Call<UserProfileDetails> call = api.getUserProfileDetails(mUserId, mUserId);
        call.enqueue(new Callback<UserProfileDetails>() {
            @Override
            public void onResponse(Call<UserProfileDetails> call, Response<UserProfileDetails> response) {
                Log.i("ERROE LL", "HERE 1");

                if (response.body() != null) {
                    Log.i("ERROE LL", "HERE 2");
                    if (response.body().getIsSuccess()) {
                        Log.i("ERROE LL", "HERE 3");

                        user_name = response.body().getUserName();
                        mProfileName.setText(response.body().getUserName());

                        Picasso.get().load(response.body().getUserPictureURL()).placeholder(R.drawable.pp).
                                error(R.drawable.pp).into(mProfilePic);

                        if (response.body().getAbout() != null) {
                            about = response.body().getAbout();
                            mAboutTxt.setVisibility(View.VISIBLE);
                            mNoAboutTxt.setVisibility(View.GONE);
                            mAboutTxt.setText(response.body().getAbout());
                        } else if (response.body().getAbout() == null) {
                            mAboutTxt.setVisibility(View.GONE);
                            mNoAboutTxt.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getFollowersNumber() == 0) {
                            mNoFollowersTxt.setVisibility(View.VISIBLE);
                            mFollowersRecyclerView.setVisibility(View.GONE);
                        }
                        if (response.body().getFollowersNumber() > 0) {
                            mNoFollowersTxt.setVisibility(View.GONE);
                            mFollowersRecyclerView.setVisibility(View.VISIBLE);
                            LinearLayoutManager mFollowersLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            mFollowersRecyclerView.setHasFixedSize(true);
                            mFollowersRecyclerView.setLayoutManager(mFollowersLayout);
                            Log.i("getFollowersListLen", response.body().getSixFollowers().length + "");
                            ArrayList<SixFollowers> mSixFollowers = new ArrayList<>();
                            for (int i =0 ; i < response.body().getSixFollowers().length ; i++)
                            {
                                if (!response.body().getSixFollowers()[i].getUserID().equals(mUserId))
                                {
                                    mSixFollowers.add(response.body().getSixFollowers()[i]);
                                }
                            }
                            mFollowersRecyclerView.setAdapter(new FollowerAdapter(getActivity(), mSixFollowers,
                                    new FollowersClickListener() {
                                        @Override
                                        public void OnClick(int position) {
                                            ArrayList<SixFollowers> sixfollowers = new ArrayList<>(Arrays.asList(response.body().getSixFollowers()));
                                            Intent mFollowIntent = new Intent(getActivity(), FollowActivity.class);
                                            mFollowIntent.putExtra("position", position);
                                            mFollowIntent.putExtra("followerObj", sixfollowers.get(position).getUserID());

                                            startActivity(mFollowIntent);
                                        }
                                    }));

                        }
                        if (response.body().getFourBooks().length > 0) {
                            mBooksRecyclerView.setVisibility(View.VISIBLE);
                            mNoBooksTxt.setVisibility(View.GONE);
                            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2, LinearLayoutManager.HORIZONTAL,false);
                            mBooksRecyclerView.setHasFixedSize(true);
                            mBooksRecyclerView.setLayoutManager(layoutManager);

                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mBooksRecyclerView.getContext(),
                                    layoutManager.getOrientation());
                            mBooksRecyclerView.addItemDecoration(dividerItemDecoration);
                            mBooksRecyclerView.setAdapter(new BooksAdapter(getActivity(), response.body().getFourBooks()));

                        } else {
                            mBooksRecyclerView.setVisibility(View.GONE);
                            mNoBooksTxt.setVisibility(View.VISIBLE);
                        }
                    } else
                        Log.i("LLLL", response.body().getErrorMessage());
                }
            }

            @Override
            public void onFailure(Call<UserProfileDetails> call, Throwable t) {
                Log.i("ERROR LL", t.getMessage());
            }
        });
    }

//    private void getFollowers(final String mUserId) {
//
//        User api = RetrofitClient.getClient(getActivity()).create(User.class);
//        Call<Followers> call = api.GetUserFollowers(mUserId);
//        call.enqueue(new Callback<Followers>() {
//            @Override
//            public void onResponse(Call<Followers> call, Response<Followers> response) {
//                try {
//                    Log.i("LLLL", response.body().getIsSuccess()+"");
//                    Toast.makeText(getActivity(), response.body().getIsSuccess()+"", Toast.LENGTH_SHORT).show();
//
//                    if (response.body().getIsSuccess().equals("true")) {
//                        LinearLayoutManager mFollowersLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//                        mFollowersRecyclerView.setHasFixedSize(true);
//                        mFollowersRecyclerView.setLayoutManager(mFollowersLayout);
//                        Log.i("getFollowersListLen",response.body().getAllFollowersDetails().length+"");
//                        mFollowersRecyclerView.setAdapter(new FollowerAdapter(getActivity(),response.body().getAllFollowersDetails(),
//                                new FollowersClickListener() {
//                            @Override
//                            public void OnClick() {
//                                Intent mFollowIntent = new Intent(getActivity(),FollowActivity.class);
//                                startActivity(mFollowIntent);
//                            }
//                        }));
//
//                    } else
//                        Log.i("LLLL", response.body().getErrorMessage());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Followers> call, Throwable t) {
//                Log.i("Followers Error : ",t.getMessage());
//            }
//        });
//
//    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        //inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.language_menu: {
//                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("lang", "en").apply();
//                getActivity().finish();
//                startActivity(new Intent(getActivity(), getClass()));
            }
        }
        return super.onOptionsItemSelected(item);
    }


}