package reverb.smartstudy.teacher.Activity.attendance.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Adapter.CursorRecyclerViewAdapter;
import reverb.smartstudy.teacher.ViewHolder.DateWiseAttendanceListViewHolder;

/**
 * Created by mdjahirulislam on 15/01/18.
 */

public class CustomDateWiseAttendanceListRecyclerViewAdapter extends CursorRecyclerViewAdapter {

    private static final String TAG = CustomDateWiseAttendanceListRecyclerViewAdapter.class.getSimpleName();


    public CustomDateWiseAttendanceListRecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate( R.layout.single_date_wise_section_with_students_attendance_list_design, parent, false);
        return new DateWiseAttendanceListViewHolder( v );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        DateWiseAttendanceListViewHolder holder = (DateWiseAttendanceListViewHolder) viewHolder;
        cursor.moveToPosition(cursor.getPosition());
        holder.setData(cursor);
        Log.d( TAG," --> "+String.valueOf( cursor.getPosition()) );
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
