//package com.example.nouran.taallam;
//
//import android.content.Context;
//import android.support.annotation.IdRes;
//import android.support.design.internal.BottomNavigationItemView;
//import android.support.design.widget.BottomNavigationView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//public class BottomMenuHelper {
//    public static void showBadge(Context context, BottomNavigationView
//            bottomNavigationView, @IdRes int itemId, String value) {
//        removeBadge(bottomNavigationView, itemId);
//        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
//        View badge = LayoutInflater.from(context).inflate(R.layout.layout_notifications_badge, bottomNavigationView, false);
//
//        TextView text = badge.findViewById(R.id.badge_text_view);
//        text.setText(value);
//        itemView.addView(badge);
//    }
//
//    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
//        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
//        if (itemView.getChildCount() == 3) {
//            itemView.removeViewAt(2);
//        }
//    }
//}
