package reverb.smartstudy.teacher.ViewHolder;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import java.util.ArrayList;

import reverb.smartstudy.teacher.Activity.attendance.adapter.CustomStudentAttendanceAdapter;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.CourseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.responseModelClass.CourseStudentAttendanceModel;

/**
 * Created by mdjahirulislam on 07/01/18.
 */

public class AttendanceListViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = AttendanceListViewHolder.class.getSimpleName();


    private TextView courseSectionTV;
    private RecyclerView studentListRV;
    private Context context;
    private CustomStudentAttendanceAdapter studentAttendanceAdapter;

    public AttendanceListViewHolder(View itemView) {
        super( itemView );
        context = itemView.getContext();

        courseSectionTV = (TextView) itemView.findViewById( R.id.singleSectionNameTV );
        studentListRV = (RecyclerView) itemView.findViewById( R.id.singleStudentPresentRV );


    }

    public void setData(Cursor c) {

        Log.d( TAG, "setData: c" + c.getCount() );
        String sectionID = c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.SECTION_ID ) );
        String sectionName = c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.SECTION_NAME ) );


        courseSectionTV.setText( sectionName );
        Log.d( TAG, "setData: roll ---> " + c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_ROLL ) ) );
        Log.d( TAG, "setData: sectionID ---> " + c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems._ID ) ) );


        ContentResolver resolver = context.getContentResolver();
        Cursor studentCursor =
                resolver.query( RequestProvider.urlForAttendanceListItems( 0 ),
                        null, CourseStudentAttendanceTableItems.SECTION_ID + "=? ",
                        new String[]{sectionID}, null );
        studentCursor.moveToFirst();


        studentAttendanceAdapter = new CustomStudentAttendanceAdapter( context, studentCursor );

        studentListRV.setAdapter( studentAttendanceAdapter );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( context );
        studentListRV.setLayoutManager( layoutManager );
        studentListRV.setItemAnimator( new DefaultItemAnimator() );

        studentListRV.setAdapter( studentAttendanceAdapter );

    }
}

