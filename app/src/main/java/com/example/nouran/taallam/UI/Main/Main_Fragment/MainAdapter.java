package com.example.nouran.taallam.UI.Main.Main_Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nouran.taallam.Model.HomePosts;
import com.example.nouran.taallam.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.myHolder> {

    private Context context;
    private ClickListener clickListener;
    private HomePosts [] homePosts ;


    public MainAdapter(Context context,HomePosts[] homePosts, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        this.homePosts = homePosts;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {

        Picasso.get().load(homePosts[position].getTeacherPictureURL()).placeholder(R.drawable.pp).
                error(R.drawable.pp).into(holder.mProfileImage);
        holder.mMainLikes.setText(homePosts[position].getLikesNumber()+"");
        holder.mMainComments.setText(homePosts[position].getCommentsNumber()+"");

        holder.mMainTxt.setText(homePosts[position].getTeacherName());
        holder.mMainDate.setText(homePosts[position].getDatetime());
        holder.mMainTxt2.setText(homePosts[position].getBody());

        holder.mMainComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnClick("comment",homePosts[position].getPostID(),position);
            }
        });

        holder.mMainLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mMainLikes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hearto,0,0,0);
                holder.mMainLikes.setText((homePosts[position].getLikesNumber()+1)+"");
                clickListener.OnClick("like",homePosts[position].getPostID(),position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 20;
    }

    class myHolder extends RecyclerView.ViewHolder {

        private TextView mMainTxt2;
        private CircleImageView mProfileImage;
        private TextView mMainDate;
        private TextView mMainComments;
        private TextView mMainLikes;
        private TextView mMainTxt;

        public myHolder(final View itemView) {
            super(itemView);

            mMainTxt2 = itemView.findViewById(R.id.main_txt2);
            mProfileImage = itemView.findViewById(R.id.profile_image);
            mMainDate = itemView.findViewById(R.id.main_date);
            mMainComments = itemView.findViewById(R.id.main_comments);
            mMainLikes = itemView.findViewById(R.id.main_likes);
            mMainTxt = itemView.findViewById(R.id.main_txt);
        }
    }
}