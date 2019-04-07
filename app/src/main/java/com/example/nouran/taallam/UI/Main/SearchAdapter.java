package com.example.nouran.taallam.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nouran.taallam.Model.SearchUsers;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.UI.Main.Profile_Fragment.FollowActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SearchUsers> searchUsers;

    public SearchAdapter(Context context, ArrayList<SearchUsers> searchUsers) {
        this.context = context;
        this.searchUsers = searchUsers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mUserName.setText(searchUsers.get(position).getUserName());
        Picasso.get().load(searchUsers.get(position).getUserPictureURL()).placeholder(R.drawable.pp)
                .error(R.drawable.pp).into(holder.mUserImage);

        holder.mSearchContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followIntent = new Intent(context, FollowActivity.class);
                followIntent.putExtra("followerObj",searchUsers.get(position).getUserID());
                context.startActivity(followIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchUsers.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        private CircleImageView mUserImage;
        private TextView mUserName;
        private LinearLayout mSearchContainer;

        public ViewHolder(View view) {
            super(view);
            mUserImage = view.findViewById(R.id.user_image);
            mUserName = view.findViewById(R.id.user_name);
            mSearchContainer = view.findViewById(R.id.search_container);
        }
    }
}