package reverb.smartstudy.teacher.ViewHolder;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.attendance.adapter.CustomStudentAttendanceAdapter;
import reverb.smartstudy.teacher.database.AttendanceClassScheduleTableItems;
import reverb.smartstudy.teacher.database.CourseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.thread.CourseWiseStudentAttendanceThread;
import reverb.smartstudy.teacher.thread.DateWiseStudentAttendanceThread;

/**
 * Created by mdjahirulislam on 14/01/18.
 */

public class AttendanceClassScheduleViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = AttendanceClassScheduleViewHolder.class.getSimpleName();


    private TextView dayNameTV;
    private TextView dateTV;
    private TextView timeTV;
    private TextView totalPresentTV;
    private Context context;

    private String courseScheduleDate;


    public AttendanceClassScheduleViewHolder(View itemView) {
        super( itemView );
        context=itemView.getContext();

        dayNameTV = (TextView) itemView.findViewById( R.id.attendanceClassScheduleDayNameTV );
        dateTV = (TextView) itemView.findViewById( R.id.attendanceClassScheduleDateTV );
        timeTV = (TextView) itemView.findViewById( R.id.attendanceClassScheduleTimeTV );
        totalPresentTV = (TextView) itemView.findViewById( R.id.attendanceClassScheduleTotalPresentNumberTV );


        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateWiseStudentAttendanceThread dateWiseStudentAttendanceThread = new DateWiseStudentAttendanceThread( context , courseScheduleDate);
                dateWiseStudentAttendanceThread.start();
                dateWiseStudentAttendanceThread.progressDialog.setMessage( "Updating....." );
                dateWiseStudentAttendanceThread.showDialog();
            }
        } );

    }

    public void setData(Cursor c) {

        dayNameTV.setText( c.getString( c.getColumnIndex( AttendanceClassScheduleTableItems.CLASS_DAY ) ) );
        dateTV.setText( c.getString( c.getColumnIndex( AttendanceClassScheduleTableItems.CLASS_SCHEDULE_DATE ) ) );
        timeTV.setText( c.getString( c.getColumnIndex( AttendanceClassScheduleTableItems.CLASS_TIME ) ) );
        totalPresentTV.setText( c.getString( c.getColumnIndex( AttendanceClassScheduleTableItems.TOTAL_PRESENT_STUDENT_NUMBER ) ) );

        courseScheduleDate = c.getString( c.getColumnIndex( AttendanceClassScheduleTableItems.CLASS_SCHEDULE_DATE ) );
    }
}