package com.example.nouran.taallam.UI.Wellcome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nouran.taallam.Model.BooksList;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksClickListener;
import com.example.nouran.taallam.R;

public class Wellcome2Adapter extends RecyclerView.Adapter<Wellcome2Adapter.myHolder> {

    private Context context;
    private BooksClickListener booksClickListener;
    private BooksList [] booksLists;


    public Wellcome2Adapter(Context context , BooksList [] booksLists ,BooksClickListener booksClickListener) {
        this.context = context;
        this.booksClickListener = booksClickListener;
        this.booksLists = booksLists;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wellcome2_item, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {

        holder.mTxt1.setText(booksLists[position].getName());

        String level;
        if (booksLists[position].getLevelNumber() == 1)
            level = booksLists[position].getLevelNumber() + " level";
        else
            level = booksLists[position].getLevelNumber() + " levels";


        holder.mTxt2.setText(level + " - " + booksLists[position].getTeacherName());

        holder.mLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booksClickListener.OnClick(booksLists[position].getID());
            }
        });

    }


    @Override
    public int getItemCount() {
        return booksLists.length;
    }

    class myHolder extends RecyclerView.ViewHolder {

        TextView mTxt2;
        TextView mTxt1;
        LinearLayout mLayoutContainer;

        public myHolder(final View itemView) {
            super(itemView);

            mTxt2 = itemView.findViewById(R.id.wellcome2_item_txt2);
            mTxt1 = itemView.findViewById(R.id.wellcome2_item_txt1);
            mLayoutContainer = itemView.findViewById(R.id.wellcome2_layout_container);
        }
    }
}