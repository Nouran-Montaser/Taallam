package com.example.nouran.taallam.UI.Main.Category_Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nouran.taallam.Courses;
import com.example.nouran.taallam.Model.Course;
import com.example.nouran.taallam.Model.UserProfileDetails;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksActivity;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.UI.Main.Profile_Fragment.BooksAdapter;
import com.example.nouran.taallam.UI.Main.Profile_Fragment.FollowActivity;
import com.example.nouran.taallam.UI.Main.Profile_Fragment.FollowerAdapter;
import com.example.nouran.taallam.UI.Main.Profile_Fragment.FollowersClickListener;
import com.example.nouran.taallam.UI.Wellcome.WellcomeAdapter;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {


    private RecyclerView mCategoryRecyclerView;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mMainView = inflater.inflate(R.layout.fragment_category, container, false);
        mCategoryRecyclerView = mMainView.findViewById(R.id.category_recyclerView);

        getCourse();

        return mMainView;
    }

    private void getCourse() {

        Courses api = RetrofitClient.getClient(getActivity()).create(Courses.class);
        Call<Course> call = api.getCoursesList("");
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        mCategoryRecyclerView.setHasFixedSize(true);
                        mCategoryRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mCategoryRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mCategoryRecyclerView.addItemDecoration(dividerItemDecoration);
                        mCategoryRecyclerView.setAdapter(new WellcomeAdapter(getActivity(), response.body().getCoursesList()
                                , new CategoryClickListener() {
                            @Override
                            public void OnClick(int id ,int position) {
                                Intent categoryIntent = new Intent(getActivity(), BooksActivity.class);
                                categoryIntent.putExtra("CourseID", id);
                                startActivity(categoryIntent);

                            }
                        },"category"));
                    }
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {

            }
        });
    }

}
