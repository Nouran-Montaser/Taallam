package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nouran.taallam.Model.Follow.AllFollowersDetails;
import com.example.nouran.taallam.Model.SixFollowers;
import com.example.nouran.taallam.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.myHolder> {

    private Context context;
    private FollowersClickListener clickListener;
    private ArrayList<SixFollowers> sixFollowers;


    public FollowerAdapter(Context context, ArrayList<SixFollowers> sixFollowers , FollowersClickListener clickListener) {
        this.context = context;
        this.sixFollowers = sixFollowers;
        this.clickListener = clickListener;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {

        Picasso.get().load(sixFollowers.get(position).getUserPictureURL()).placeholder(R.drawable.pp).
                error(R.drawable.pp).into(holder.mFollowerPic);
        holder.mFollowerPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                clickListener.OnClick(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return sixFollowers.size();
    }

    class myHolder extends RecyclerView.ViewHolder {


        private CircleImageView mFollowerPic;

        public myHolder(final View itemView) {
            super(itemView);

            mFollowerPic = itemView.findViewById(R.id.follower_pic);
        }
    }
}
