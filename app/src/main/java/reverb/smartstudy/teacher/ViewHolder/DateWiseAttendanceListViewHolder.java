package reverb.smartstudy.teacher.ViewHolder;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.attendance.adapter.CustomDateWiseStudentAttendanceAdapter;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.DateWiseStudentAttendanceTableItems;

/**
 * Created by mdjahirulislam on 15/01/18.
 */

public class DateWiseAttendanceListViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = DateWiseAttendanceListViewHolder.class.getSimpleName();


    private TextView courseSectionTV;
    private RecyclerView studentListRV;
    private Context context;
    private CustomDateWiseStudentAttendanceAdapter studentAttendanceAdapter;

    public DateWiseAttendanceListViewHolder(View itemView) {
        super( itemView );
        context = itemView.getContext();

        courseSectionTV = (TextView) itemView.findViewById( R.id.singleDateWiseSectionNameTV );
        studentListRV = (RecyclerView) itemView.findViewById( R.id.singleDateWiseStudentPresentRV );


    }

    public void setData(Cursor c) {

//        Log.d( TAG, "setData: c" + c.getCount() );
        String sectionID = c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.SECTION_ID ) );
        String sectionName = c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.SECTION_NAME ) );


        courseSectionTV.setText( sectionName );
//        Log.d( TAG, "setData: roll ---> " + c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_ROLL ) ) );
//        Log.d( TAG, "setData: sectionID ---> " + c.getString( c.getColumnIndex( CourseStudentAttendanceTableItems._ID ) ) );


        ContentResolver resolver = context.getContentResolver();
        Cursor studentCursor =
                resolver.query( RequestProvider.urlForDateAttendanceListItems( 0 ),
                        null, DateWiseStudentAttendanceTableItems.SECTION_ID + " =? ",
                        new String[]{sectionID}, null );
        studentCursor.moveToFirst();


        studentAttendanceAdapter = new CustomDateWiseStudentAttendanceAdapter( context, studentCursor );

        studentListRV.setAdapter( studentAttendanceAdapter );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( context );
        studentListRV.setLayoutManager( layoutManager );
        studentListRV.setItemAnimator( new DefaultItemAnimator() );

        studentListRV.setAdapter( studentAttendanceAdapter );

    }
}

