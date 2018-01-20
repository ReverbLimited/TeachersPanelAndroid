package reverb.smartstudy.teacher.Fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.attendance.adapter.CustomAttendanceListRecyclerViewAdapter;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.CourseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.preference.SharedPref;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceStudentListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = AttendanceStudentListFragment.class.getSimpleName();

    public final int offset = 30;
    private int page = 0;
    private Handler handlerToWait = new Handler();
    private boolean loadingMore = false;


    private Context context;
    private TextView fragmentCourseNameTV;
    private TextView fragmentAttendanceStudentsListNotFoundTV;
    private CustomAttendanceListRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String courseID;

    public AttendanceStudentListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate( savedInstanceState );
        View v = inflater.inflate( R.layout.fragment_attendance_student_list, container, false );
        context = getActivity();
        mAdapter = new CustomAttendanceListRecyclerViewAdapter( context, null );
        mRecyclerView = (RecyclerView) v.findViewById( R.id.fragmentAttendanceStudentsListRV );
        mLayoutManager = new LinearLayoutManager( context );
        mRecyclerView.setLayoutManager( mLayoutManager );
        mRecyclerView.setAdapter( mAdapter );


        courseID = SharedPref.getInstance( context ).getCourseId();
        Log.d( TAG, "onCreateView: course id---> " +courseID);

        fragmentCourseNameTV = (TextView) v.findViewById( R.id.fragmentCourseNameTV );
        fragmentAttendanceStudentsListNotFoundTV = (TextView) v.findViewById( R.id.fragmentAttendanceStudentsListNotFoundTV );

        fragmentCourseNameTV.setText( SharedPref.getInstance( context ).getCourseName() );


        getActivity().getSupportLoaderManager().restartLoader( 0, null, AttendanceStudentListFragment.this );


        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new CursorLoader( context, RequestProvider.urlForStudentAttendanceListItems( offset * page ),
                        null, CourseStudentAttendanceTableItems.COURSE_ID + "=? ", new String[]{courseID}, null );

            default:
                throw new IllegalArgumentException( "no id handled!" );
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 0:
                if (data.getCount() > 0) {
                    fragmentAttendanceStudentsListNotFoundTV.setVisibility( View.GONE );
                    //shortToast.setText("loading MORE " + page);
                    if (page != 0) {
                        // shortToast.setText("loading more data");
                        //shortToast.show();
                    }

                    Cursor cursor = ((CustomAttendanceListRecyclerViewAdapter) mRecyclerView.getAdapter()).getCursor();

                    //fill all existing in adapter
                    MatrixCursor mx = new MatrixCursor( CourseStudentAttendanceTableItems.Columns );
                    fillMx( cursor, mx );

                    //fill with additional result
                    fillMx( data, mx );

                    ((CustomAttendanceListRecyclerViewAdapter) mRecyclerView.getAdapter()).swapCursor( mx );

                    handlerToWait.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            loadingMore = false;
                        }
                    }, 1000 );
                } else {
                    fragmentAttendanceStudentsListNotFoundTV.setVisibility( View.VISIBLE );
                }

                break;
            default:
                throw new IllegalArgumentException( "no loader id handled!" );
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void fillMx(Cursor data, MatrixCursor mx) {
        if (data == null)
            return;

        data.moveToPosition( -1 );
        while (data.moveToNext()) {
            mx.addRow( new Object[]{
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems._ID ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.COURSE_ID ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.SECTION_ID ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.SECTION_NAME ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_ID ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_NAME ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_ROLL ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.STUDENT_IMAGE_PATH ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.LAST_PRESENT_DATE ) ),
                    data.getString( data.getColumnIndex( CourseStudentAttendanceTableItems.TOTAL_PRESENT ) ),

            } );
        }
    }
}
