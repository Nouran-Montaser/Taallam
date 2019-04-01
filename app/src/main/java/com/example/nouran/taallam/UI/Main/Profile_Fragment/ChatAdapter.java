package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nouran.taallam.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter  extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;

    public ChatAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position == 1 ) {
            holder.relativeLayout.setGravity(Gravity.END);
            holder.imageView.setVisibility(View.GONE);
            holder.messageTextView.setBackgroundResource(R.drawable.receive_message_background);
            holder.messageTextView.setTextColor(Color.WHITE);
        } else {
            holder.relativeLayout.setGravity(Gravity.START);
            holder.imageView.setVisibility(View.VISIBLE);
            holder.messageTextView.setBackgroundResource(R.drawable.send_message_background);
            holder.messageTextView.setTextColor(Color.BLACK);
        }
        holder.messageTextView.setText("HI");
    }

    @Override
    public int getItemCount() {
        return 2;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        View item;
        TextView messageTextView;
        RelativeLayout relativeLayout;
        CircleImageView imageView;

        public ViewHolder(View view) {
            super(view);
            item = view;
            imageView = view.findViewById(R.id.single_message_image);
            relativeLayout = view.findViewById(R.id.single_layout);
            messageTextView = view.findViewById(R.id.single_message_text);
        }
    }
}