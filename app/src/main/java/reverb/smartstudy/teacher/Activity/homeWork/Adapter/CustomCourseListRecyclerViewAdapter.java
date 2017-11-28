package reverb.smartstudy.teacher.Activity.homeWork.Adapter;

/**
 * Created by HP on 9/5/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Adapter.CursorRecyclerViewAdapter;
import reverb.smartstudy.teacher.ui.CourseListViewHolder;


/**
 * Created by deadfish on 2016-01-28.
 */
public class CustomCourseListRecyclerViewAdapter extends CursorRecyclerViewAdapter {

    public CustomCourseListRecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.single_course_list_design, parent, false);
        return new CourseListViewHolder( v );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        CourseListViewHolder holder = (CourseListViewHolder) viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setData(cursor);
        Log.d( "courseAdapter ",String.valueOf( cursor.getPosition()) );
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
