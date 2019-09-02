package reverb.smartstudy.teacher.ViewHolder;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.database.CourseStudentAttendanceTableItems;

/**
 * Created by mdjahirulislam on 07/01/18.
 */

public class AllStudentPresentViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = AllStudentPresentViewHolder.class.getSimpleName();


    private TextView singlePresentStudentNameTV;
    private TextView singleAllStudentRollTV;
    private TextView singleAllStudentLastPresentDateTV;
    private Button singleAllStudentTotalPresentTV;
    Context context;

    public AllStudentPresentViewHolder(View itemView) {
        super( itemView );
        context = itemView.getContext();

        singlePresentStudentNameTV = (TextView) itemView.findViewById( R.id.singleAllStudentNameTV );
        singleAllStudentRollTV = (TextView) itemView.findViewById( R.id.singleAllStudentRollTV );
        singleAllStudentLastPresentDateTV = (TextView) itemView.findViewById( R.id.singleAllStudentLastPresentDateTV );
        singleAllStudentTotalPresentTV = (Button) itemView.findViewById( R.id.singleAllStudentTotalPresentTV );


    }

    public void setData(Cursor c) {

        String studentName = c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_NAME ) );
        String studentRoll = c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_ROLL ) );
        String studentLastPresentDate = c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.LAST_PRESENT_DATE ) );
        String studentTotalPresent = c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.TOTAL_PRESENT ) );


        singlePresentStudentNameTV.setText( studentName );
        singleAllStudentRollTV.setText( studentRoll );
        singleAllStudentLastPresentDateTV.setText( studentLastPresentDate );
        singleAllStudentTotalPresentTV.setText( studentTotalPresent );
            Log.d( TAG, "setData: roll ---> " + c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_ROLL ) ) );
            Log.d( TAG, "setData: sectionID ---> " + c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.SECTION_ID ) ) );

    }
}
