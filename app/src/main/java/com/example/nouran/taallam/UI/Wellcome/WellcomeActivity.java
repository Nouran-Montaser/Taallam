package com.example.nouran.taallam.UI.Wellcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nouran.taallam.Books;
import com.example.nouran.taallam.Courses;
import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.BooksList;
import com.example.nouran.taallam.Model.Course;
import com.example.nouran.taallam.Model.CoursesList;
import com.example.nouran.taallam.Model.SixFollowers;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksActivity;
import com.example.nouran.taallam.UI.Main.Category_Fragment.CategoryClickListener;
import com.example.nouran.taallam.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WellcomeActivity extends AppCompatActivity {

    private Button mNextButton;
    private RecyclerView mRecyclerView;
    private WellcomeAdapter wellcomeAdapter;
    private ArrayList<CoursesList> coursesLists;
    private CoursesList course;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private String userId;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        initializer();

        sharedPrefs = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        userId = sharedPrefs.getString("UserID", null);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wellcomeAdapter.getId() == -1) {
                    Toast.makeText(WellcomeActivity.this, R.string.welcome_msg, Toast.LENGTH_SHORT).show();
                } else {
                    courseId = wellcomeAdapter.getId();
                    Log.i("courseId", course + "");
//                getSelection(coursesLists);
//                Toast.makeText(WellcomeActivity.this, getSelection(coursesLists).getName(), Toast.LENGTH_SHORT).show();
                    Intent mWellcomeIntent = new Intent(WellcomeActivity.this, Wellcome2Activity.class);
                    mWellcomeIntent.putExtra("SelectedCourse", courseId);
                    startActivity(mWellcomeIntent);
                    finish();
                }
            }
        });

    }

    private void initializer() {

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
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

                    coursesLists = new ArrayList<>(Arrays.asList(response.body().getCoursesList()));

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                            linearLayoutManager.getOrientation());
                    mRecyclerView.addItemDecoration(dividerItemDecoration);
                    wellcomeAdapter = new WellcomeAdapter(WellcomeActivity.this, response.body().getCoursesList(),
                            null, "welcome");
                    mRecyclerView.setAdapter(wellcomeAdapter);

                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {

            }
        });
    }

//    public void setSelectedPosition(int position, CoursesList[] list) {
//        for (int i = 0; i < list.length; i++) {
//            list[i].setSelected(i == position);
//        }
////        wellcomeAdapter.notifyDataSetChanged();
//        Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
//    }
//
//    public void clearSelection(CoursesList[] list) {
//        for (int i = 0; i < list.length; i++) {
//            list[i].setSelected(false);
//        }
////        wellcomeAdapter.notifyDataSetChanged();
//        Toast.makeText(this, "UnSelected", Toast.LENGTH_SHORT).show();
//    }
//
//    public CoursesList getSelection(ArrayList<CoursesList> list) {
//        for (int i = 0; i < list.size(); i++) {
//            Log.i("Posssss",list.get(i).isSelected()+"");
//            if (list.get(i).isSelected())
//                return list.get(i);
//        }
//        return null;
//    }

}
