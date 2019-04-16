package com.example.nouran.taallam.UI.Main.Profile_Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nouran.taallam.Model.Follow.AllFollowersDetails;
import com.example.nouran.taallam.Model.FourBooks;
import com.example.nouran.taallam.R;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.squareup.picasso.Picasso;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.myHolder> {

    private Context context;
    private FourBooks[] fourBooks;



    public BooksAdapter(Context context,FourBooks[] fourBooks) {
        this.context = context;
        this.fourBooks = fourBooks;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {
        holder.mBookName.setText(fourBooks[position].getBookName());
        String s = String.format("%.0f", fourBooks[position].getPercentage());
        float number = Float.valueOf(s);
        Log.i("LOPLOP",number+"");

        holder.mDonutProgress.setProgress(number);
        holder.mAboutTxt.setText(fourBooks[position].getParticipantsNumber()+context.getString(R.string.students));
    }


    @Override
    public int getItemCount() {
        return fourBooks.length;
    }

    class myHolder extends RecyclerView.ViewHolder {


        private DonutProgress mDonutProgress;
        private TextView mAboutTxt;
        private TextView mBookName;

        public myHolder(final View itemView) {
            super(itemView);

            mDonutProgress = itemView.findViewById(R.id.donut_progress);
            mAboutTxt = itemView.findViewById(R.id.about_txt);
            mBookName = itemView.findViewById(R.id.book_name);
        }
    }
}