package com.example.nouran.taallam.UI.Main.Main_Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nouran.taallam.Forum;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.Comments;
import com.example.nouran.taallam.Model.HomePosts;
import com.example.nouran.taallam.Model.UserHomeDetails;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.Users;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class MainFragment extends Fragment {


    private RecyclerView mMainRecyclerView;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private String mUserId;
    private LinearLayout mMainEmptycontainer;


    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mMainView = inflater.inflate(R.layout.fragment_main, container, false);

        mMainRecyclerView = mMainView.findViewById(R.id.main_recyclerView);
        mMainEmptycontainer = mMainView.findViewById(R.id.main_emptycontainer);

        sharedPrefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        mUserId = sharedPrefs.getString("UserID", null);


        getHomeDetails(mUserId);

        return mMainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getHomeDetails(mUserId);
    }

    public void getHomeDetails(String mUserId) {

        Users api = RetrofitClient.getClient(getActivity()).create(Users.class);
        Call<UserHomeDetails> call = api.getUserHomeDetails(mUserId, 0);
        call.enqueue(new Callback<UserHomeDetails>() {
            @Override
            public void onResponse(Call<UserHomeDetails> call, Response<UserHomeDetails> response1) {
                if (response1.body() != null) {
                    if (response1.body().getIsSuccess()) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

                        mMainRecyclerView.setHasFixedSize(true);
                        mMainRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mMainRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mMainRecyclerView.addItemDecoration(dividerItemDecoration);
                        if (response1.body().getHomePosts().length >0) {
                            mMainRecyclerView.setVisibility(View.VISIBLE);
                            mMainEmptycontainer.setVisibility(View.GONE);
                            mMainRecyclerView.setAdapter(new MainAdapter(getActivity(), response1.body().getHomePosts(), new ClickListener() {
                                @Override
                                public void OnClick(String action, int postId, int position) {
                                    if (action.equals("like")) {
                                        Forum api = RetrofitClient.getClient(getActivity()).create(Forum.class);
                                        Call<BaseResponse> call = api.likePost(mUserId, postId);
                                        call.enqueue(new Callback<BaseResponse>() {
                                            @Override
                                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                                if (response.body() != null) {
                                                    if (!response.body().getIsSuccess())
                                                        Toast.makeText(getActivity(), response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<BaseResponse> call, Throwable t) {

                                            }
                                        });

                                    } else if (action.equals("comment")) {
                                        Forum api = RetrofitClient.getClient(getActivity()).create(Forum.class);
                                        Call<Comments> call = api.getPostComments(mUserId, postId);
                                        call.enqueue(new Callback<Comments>() {
                                            @Override
                                            public void onResponse(Call<Comments> call, Response<Comments> response) {
                                                if (response.body() != null) {
                                                    if (response.body().getIsSuccess()) {
                                                        ArrayList<HomePosts> comment = new ArrayList<>(Arrays.asList(response1.body().getHomePosts()));
                                                        Intent commentIntent = new Intent(getActivity(), CommentsActivity.class);
                                                        commentIntent.putExtra("position", position);
                                                        commentIntent.putParcelableArrayListExtra("commentObj", comment);
                                                        commentIntent.putExtra("postID", postId);
                                                        commentIntent.putExtra("UserId", mUserId);
                                                        startActivity(commentIntent);
                                                    } else
                                                        Toast.makeText(getActivity(), response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Comments> call, Throwable t) {

                                            }
                                        });
                                    }
                                }
                            }));


                        } else {
                            mMainRecyclerView.setVisibility(View.GONE);
                            mMainEmptycontainer.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserHomeDetails> call, Throwable t) {
                mMainRecyclerView.setVisibility(View.GONE);
                mMainEmptycontainer.setVisibility(View.VISIBLE);
            }
        });

    }


}
