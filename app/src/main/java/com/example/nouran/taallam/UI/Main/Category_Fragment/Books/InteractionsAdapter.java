package com.example.nouran.taallam.UI.Main.Category_Fragment.Books;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nouran.taallam.Model.BookPosts;
import com.example.nouran.taallam.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class InteractionsAdapter extends RecyclerView.Adapter<InteractionsAdapter.myHolder> {

    private Context context;
    private BookPosts [] bookPosts ;

    public InteractionsAdapter(Context context , BookPosts [] bookPosts) {
        this.context = context;
        this.bookPosts = bookPosts;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interaction, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {
        Picasso.get().load(bookPosts[position].getTeacherPictureURL()).placeholder(R.drawable.pp).
                error(R.drawable.pp).into(holder.mProfileImage);

        holder.mMainDate.setText(bookPosts[position].getDatetime());
        holder.mMainTxt2.setText(bookPosts[position].getBody());
        holder.mMainComments.setText(bookPosts[position].getCommentsNumber()+"");
        holder.mMainLikes.setText(bookPosts[position].getLikesNumber()+"");

    }


    @Override
    public int getItemCount() {
        return bookPosts.length;
    }

    class myHolder extends RecyclerView.ViewHolder {

        TextView mMainTxt2;
        CircleImageView mProfileImage;
        TextView mMainDate;
        TextView mMainComments;
        TextView mMainLikes;
        TextView mMainTxt;
        LinearLayout mMainFContainer;

        public myHolder(final View itemView) {
            super(itemView);
            mMainTxt2 = itemView.findViewById(R.id.main_txt2);
            mProfileImage = itemView.findViewById(R.id.profile_image);
            mMainDate = itemView.findViewById(R.id.main_date);
            mMainComments = itemView.findViewById(R.id.main_comments);
            mMainLikes = itemView.findViewById(R.id.main_likes);
            mMainTxt = itemView.findViewById(R.id.main_txt);
            mMainFContainer = itemView.findViewById(R.id.main_FContainer);

        }
    }
}
