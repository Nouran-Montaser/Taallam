package com.example.nouran.taallam.UI.Wellcome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nouran.taallam.Model.CoursesList;
import com.example.nouran.taallam.UI.Main.Category_Fragment.CategoryClickListener;
import com.example.nouran.taallam.R;

public class WellcomeAdapter extends RecyclerView.Adapter<WellcomeAdapter.myHolder> {

    private Context context;
    private CategoryClickListener categoryClickListener;
    private CoursesList [] coursesLists ;
    private int pos = -1;
    private String contextName;

    public WellcomeAdapter(Context context , CoursesList [] coursesLists, CategoryClickListener categoryClickListener, String contextName ) {
        this.context = context;
        this.categoryClickListener = categoryClickListener;
        this.coursesLists = coursesLists;
        this.contextName = contextName ;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wellcome_item, parent, false);
        return new myHolder(view);
    }


    @Override
    public void onBindViewHolder(myHolder holder, final int position) {

        holder.mTxt1.setText(coursesLists[position].getName());
        holder.mTxt2.setText(coursesLists[position].getDescription());
        String level , teacher,student ,question;
        if ( coursesLists[position].getTeachersNumber() == 1)
            teacher = coursesLists[position].getTeachersNumber() + " teacher";
        else
            teacher = coursesLists[position].getTeachersNumber() + " teachers";

        if (coursesLists[position].getQuestionsNumber() == 1)
            question = coursesLists[position].getQuestionsNumber() + " Question";
        else
            question = coursesLists[position].getQuestionsNumber() + " Questions";

        if (coursesLists[position].getStudentsNumber() == 1)
            student = coursesLists[position].getStudentsNumber() + " Student";
        else
            student = coursesLists[position].getStudentsNumber() + " Students";


        if (coursesLists[position].getLevelsNumber() == 1)
            level = coursesLists[position].getLevelsNumber() + " Level";
        else
            level = coursesLists[position].getLevelsNumber() + " Levels";




        holder.mTxt3.setText(level + " - " + teacher +" - " + student +" - "+ question);

        holder.mLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contextName.equals("welcome"))
                {

                    pos = coursesLists[position].getID();
                    notifyDataSetChanged();

                }else
                categoryClickListener.OnClick( coursesLists[position].getID(), position);

            }
        });
        if (contextName.equals("welcome")) {
            if (pos == coursesLists[position].getID()) {
                holder.check.setVisibility(View.VISIBLE);
            } else {
                holder.check.setVisibility(View.GONE);
            }
        }
    }

    public int getId ()
    {
        return pos;
    }

    @Override
    public int getItemCount() {
        return coursesLists.length;
    }

    class myHolder extends RecyclerView.ViewHolder {

        TextView mTxt3;
        TextView mTxt2;
        TextView mTxt1;
        LinearLayout mLayoutContainer;
        ImageView check;

        public myHolder(final View itemView) {
            super(itemView);
            mTxt3 = itemView.findViewById(R.id.wellcome_item_txt3);
            mTxt2 = itemView.findViewById(R.id.wellcome_item_txt2);
            mTxt1 = itemView.findViewById(R.id.wellcome_item_txt1);
            mLayoutContainer = itemView.findViewById(R.id.wellcome1_layout_container);
            check = itemView.findViewById(R.id.check_img);
        }
    }
}