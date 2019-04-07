package com.example.nouran.taallam.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nouran.taallam.Date;
import com.example.nouran.taallam.Model.MessagesContactList;
import com.example.nouran.taallam.R;
import com.example.nouran.taallam.UI.Main.Profile_Fragment.ChatActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.myHolder> {

    private Context context;
    private MessagesContactList[] contacts;

    public MessageAdapter(Context context, MessagesContactList[] contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public MessageAdapter.myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_main, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(MessageAdapter.myHolder holder, final int position) {
        Picasso.get().load(contacts[position].getConversationUserImageUrl()).placeholder(R.drawable.pp).
                error(R.drawable.pp).into(holder.mMessagePImg);

        holder.mMessageDateTxt.setText(Date.formatDate(contacts[position].getLastMessageDate()));
        holder.mMessageContentTxt.setText(contacts[position].getLastMessage());
        holder.mMessageNameTxt.setText(contacts[position].getConversationUserName());
        holder.mMainMessageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("userID",contacts[position].getConversationUserID());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return contacts.length;
    }

    class myHolder extends RecyclerView.ViewHolder {


        private TextView mMessageDateTxt;
        private TextView mMessageNameTxt;
        private TextView mMessageContentTxt;
        private CircleImageView mMessagePImg;
        private CardView mMainMessageContainer;

        public myHolder(final View itemView) {
            super(itemView);

            mMessageDateTxt = itemView.findViewById(R.id.message_DateTxt);
            mMessageNameTxt = itemView.findViewById(R.id.message_NameTxt);
            mMessageContentTxt = itemView.findViewById(R.id.message_ContentTxt);
            mMessagePImg = itemView.findViewById(R.id.message_PImg);
            mMainMessageContainer = itemView.findViewById(R.id.main_message_Container);
        }
    }
}

