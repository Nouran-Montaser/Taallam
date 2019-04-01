package com.example.nouran.taallam.UI.Main.Notification_Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nouran.taallam.Model.AllNotificaions;
import com.example.nouran.taallam.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.myHolder> {

    private Context context;
    private AllNotificaions [] allNotificaions;

    public NotificationAdapter(Context context , AllNotificaions[] allNotificaions) {
        this.context = context;
        this.allNotificaions = allNotificaions ;
    }

    @Override
    public NotificationAdapter.myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(NotificationAdapter.myHolder holder, final int position) {
        Picasso.get().load(allNotificaions[position].getUserPictureURL()).placeholder(R.drawable.pp).
                error(R.drawable.pp).into(holder.mNotificationPImg);

        String content = allNotificaions[position].getNotificationBody();
        String[] notificationContent = content.split("\"Content\":\"");
        String[] notificationBody = notificationContent[1].split("\",\"PostID\"");
        Log.i("ppppppppppp",notificationBody[0] + "            "+notificationBody[1]);
        holder.mNotificationTxt.setText(notificationBody[0]);

    }


    @Override
    public int getItemCount() {
        return allNotificaions.length;
    }

    class myHolder extends RecyclerView.ViewHolder {

        private CircleImageView mNotificationPImg;
        private TextView mNotificationTxt;

        public myHolder(final View itemView) {
            super(itemView);

            mNotificationPImg = itemView.findViewById(R.id.notification_PImg);
            mNotificationTxt = itemView.findViewById(R.id.notification_Txt);
        }
    }
}
