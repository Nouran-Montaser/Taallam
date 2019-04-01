package com.example.nouran.taallam.UI.Main.Main_Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nouran.taallam.Model.PostComments;
import com.example.nouran.taallam.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.myHolder> {

    private Context context;
    private PostComments [] postComments;

    public CommentsAdapter(Context context , PostComments [] postComments) {
        this.context = context;
        this.postComments = postComments;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_comment, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {

        holder.mCommentContentTxt.setText(postComments[position].getBody());
        holder.mCommentDateTxt.setText(postComments[position].getDateTime());
        holder.mCommentNameTxt.setText(postComments[position].getUserName());
        Picasso.get().load(postComments[position].getUserPictureURL()).placeholder(R.drawable.pp)
                .error(R.drawable.pp).into(holder.mCommentPImg);

    }


    @Override
    public int getItemCount() {
        return postComments.length;
    }

    class myHolder extends RecyclerView.ViewHolder {

        CircleImageView mCommentPImg;
        TextView mCommentNameTxt;
        TextView mCommentContentTxt;
        TextView mCommentDateTxt;

        public myHolder(final View itemView) {
            super(itemView);

            mCommentPImg = itemView.findViewById(R.id.comment_PImg);
            mCommentNameTxt = itemView.findViewById(R.id.comment_NameTxt);
            mCommentContentTxt = itemView.findViewById(R.id.comment_ContentTxt);
            mCommentDateTxt = itemView.findViewById(R.id.comment_DateTxt);
        }
    }
}