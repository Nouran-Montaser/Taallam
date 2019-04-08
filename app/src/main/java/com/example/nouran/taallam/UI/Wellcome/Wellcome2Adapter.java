package com.example.nouran.taallam.UI.Wellcome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nouran.taallam.Model.BooksList;
import com.example.nouran.taallam.UI.Main.Category_Fragment.Books.BooksClickListener;
import com.example.nouran.taallam.R;

import java.util.ArrayList;

public class Wellcome2Adapter extends RecyclerView.Adapter<Wellcome2Adapter.myHolder> {

    private Context context;
    private BooksClickListener booksClickListener;
    private String contextName;

    //    private BooksList [] booksLists;
private ArrayList<BooksList> booksLists;

    public Wellcome2Adapter(Context context , ArrayList<BooksList> booksLists ,BooksClickListener booksClickListener,String contextName) {
        this.context = context;
        this.booksClickListener = booksClickListener;
        this.booksLists = booksLists;
        this.contextName = contextName;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wellcome2_item, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {

        holder.mTxt1.setText(booksLists.get(position).getName());

        String level;
        if (booksLists.get(position).getLevelNumber() == 1)
            level = booksLists.get(position).getLevelNumber() + context.getString(R.string.level);
        else
            level = booksLists.get(position).getLevelNumber() + context.getString(R.string.levels);


        holder.mTxt2.setText(level + " - " + booksLists.get(position).getTeacherName());

        holder.mLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contextName.equals("welcome"))
                {
                    booksLists.get(position).setSelected(!booksLists.get(position).isSelected());
                    notifyDataSetChanged();

                }else

                    booksClickListener.OnClick(position , booksLists.get(position).getID());
            }
        });
        if (contextName.equals("welcome")) {
            if (booksLists.get(position).isSelected()) {
                holder.check.setVisibility(View.VISIBLE);
            } else {
                holder.check.setVisibility(View.GONE);
            }
        }

    }

    public ArrayList<BooksList> getBooks()
    {
        return booksLists;
    }

    @Override
    public int getItemCount() {
        return booksLists.size();
    }

    class myHolder extends RecyclerView.ViewHolder {

        TextView mTxt2;
        TextView mTxt1;
        LinearLayout mLayoutContainer;
        ImageView check ;

        public myHolder(final View itemView) {
            super(itemView);

            mTxt2 = itemView.findViewById(R.id.wellcome2_item_txt2);
            mTxt1 = itemView.findViewById(R.id.wellcome2_item_txt1);
            mLayoutContainer = itemView.findViewById(R.id.wellcome2_layout_container);
            check = itemView.findViewById(R.id.check_img);
        }
    }
}