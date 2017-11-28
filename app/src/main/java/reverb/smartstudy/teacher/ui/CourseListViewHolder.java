package reverb.smartstudy.teacher.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.homeWork.Activity.HomeWorkListActivity;
import reverb.smartstudy.teacher.Activity.news.NewsDetails;

/**
 * Created by mdjahirulislam on 25/11/17.
 */

public class CourseListViewHolder extends RecyclerView.ViewHolder {

    private String courseName;
    private String courseSection;
    public TextView nameTV;
    private TextView sectionTV;
    Context context;


    public CourseListViewHolder(View itemView) {
        super( itemView );

        context=itemView.getContext();
        nameTV = (TextView) itemView.findViewById( R.id.tvCourseName);
        sectionTV = (TextView) itemView.findViewById(R.id.tvCourseSection);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hi", "Element " + getPosition() + " clicked. "+courseName);
                Intent myIntent = new Intent(context,HomeWorkListActivity.class);
                myIntent.putExtra("title",courseName);
                myIntent.putExtra("date", courseSection);
                context.startActivity(myIntent);
            }
        });
    }

    public void setData(Cursor c) {
        nameTV.setText(c.getString(c.getColumnIndex("name")));
        sectionTV.setText(c.getString(c.getColumnIndex("class")));
        courseName=c.getString(c.getColumnIndex("name"));
        courseSection=c.getString(c.getColumnIndex("class"));
    }
}
