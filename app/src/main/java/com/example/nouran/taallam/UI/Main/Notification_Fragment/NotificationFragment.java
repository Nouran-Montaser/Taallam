package com.example.nouran.taallam.UI.Main.Notification_Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nouran.taallam.Model.BaseResponse;
import com.example.nouran.taallam.Model.UserNotifications;
import com.example.nouran.taallam.Model.UserProfileDetails;
import com.example.nouran.taallam.Notifications;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.RetrofitClient;
import com.example.nouran.taallam.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class NotificationFragment extends Fragment {


    private TextView mNotificationsEmptyView;
    private RecyclerView mNotificationsRecyclerView;
    private LinearLayout mNotificationsEmptycontainer;
    private static SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
    private static final String MY_PREFS_NAME = "MyPrefsFile";

    public NotificationFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mMainView = inflater.inflate(R.layout.fragment_notification, container, false);

        mNotificationsEmptyView = mMainView.findViewById(R.id.notifications_emptyView);
        mNotificationsRecyclerView = mMainView.findViewById(R.id.notifications_recycler_view);
        mNotificationsEmptycontainer = mMainView.findViewById(R.id.notifications_emptycontainer);

        sharedPrefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String mUserId = sharedPrefs.getString("UserID", null);
        getNotifications(mUserId);

        return mMainView;
    }

    public void getNotifications(String mUserId) {

        Notifications api = RetrofitClient.getClient(getActivity()).create(Notifications.class);
        Call<UserNotifications> call = api.getAllUserNotifications(mUserId);
        call.enqueue(new Callback<UserNotifications>() {
            @Override
            public void onResponse(Call<UserNotifications> call, Response<UserNotifications> response) {
                if (response.body() != null) {
                    if (response.body().getIsSuccess()) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        mNotificationsRecyclerView.setHasFixedSize(true);
                        mNotificationsRecyclerView.setLayoutManager(linearLayoutManager);

                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mNotificationsRecyclerView.getContext(),
                                linearLayoutManager.getOrientation());
                        mNotificationsRecyclerView.addItemDecoration(dividerItemDecoration);
                        mNotificationsRecyclerView.setAdapter(new NotificationAdapter(getActivity(), response.body().getAllNotificaions()));

                        if (response.body().getAllNotificaions().length == 0) {
                            mNotificationsEmptycontainer.setVisibility(View.VISIBLE);
                            mNotificationsRecyclerView.setVisibility(View.GONE);
                        } else {
                            mNotificationsEmptycontainer.setVisibility(View.GONE);
                            mNotificationsRecyclerView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < response.body().getAllNotificaions().length; i++) {
                                if (!response.body().getAllNotificaions()[i].getIsSeen()) {
                                    Notifications api2 = RetrofitClient.getClient(getActivity()).create(Notifications.class);
                                    Call<BaseResponse> call2 = api2.updateSeenNotification(mUserId,
                                            response.body().getAllNotificaions()[i].getID());
                                    call2.enqueue(new Callback<BaseResponse>() {
                                        @Override
                                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                            if (response.body() != null) {
                                                if (response.body().getIsSuccess()) {
                                                    Log.i("Success", response.body().getIsSuccess() + "");
                                                } else
                                                    Log.i("UnSuccess", response.body().getErrorMessage() + "");
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                                        }
                                    });
                                }
                            }
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<UserNotifications> call, Throwable t) {

            }
        });


    }


}
