package reverb.smartstudy.teacher.Activity.attendance.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.attendance.adapter.CustomDateWiseAttendanceListRecyclerViewAdapter;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.DateWiseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.staticclasses.Functions;


public class ManualAttendanceListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = ManualAttendanceListActivity.class.getSimpleName();

    private Toolbar manualAttendanceToolBar;
    public final int offset = 30;
    private int page = 0;
    private Handler handlerToWait = new Handler();
    private boolean loadingMore = false;


    private TextView noAttendanceTV;
    private TextView dateWiseStudentAttendanceDateTV;
    private CustomDateWiseAttendanceListRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String courseID;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_manual_attendance_list );
        manualAttendanceToolBar = (Toolbar) findViewById( R.id.manualAttendanceToolBar );
        setSupportActionBar( manualAttendanceToolBar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
//        mRecyclerView.notify();


        mAdapter = new CustomDateWiseAttendanceListRecyclerViewAdapter( this, null );

        mRecyclerView = (RecyclerView) findViewById( R.id.dateWiseAttendanceListRV );
        mLayoutManager = new LinearLayoutManager( this );
        mRecyclerView.setLayoutManager( mLayoutManager );
        mRecyclerView.setAdapter( mAdapter );


        courseID = SharedPref.getInstance( this ).getCourseId();
        Log.d( TAG, "onCreateView: course id---> " +courseID);

        noAttendanceTV = (TextView) findViewById( R.id.dateWiseAttendanceStudentsListNotFoundTV );
        dateWiseStudentAttendanceDateTV = (TextView) findViewById( R.id.dateWiseAttendanceDateTV );

        date = getIntent().getStringExtra( "date" );
        dateWiseStudentAttendanceDateTV.setText( Functions.convertDay( date ) );

        getSupportLoaderManager().restartLoader( 2, null, ManualAttendanceListActivity.this );

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent( this, CourseOperationActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity( intent );

            finish();
        }
        // app icon in action bar clicked; goto parent activity.
        this.finish();
        return super.onOptionsItemSelected( item );

    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 2:
                return new CursorLoader( this, RequestProvider.urlForDateWiseAttendanceListItems( offset * page ),
                        null, DateWiseStudentAttendanceTableItems.DATE + " =? AND "+DateWiseStudentAttendanceTableItems.COURSE_ID+" =? ", new String[]{date,courseID}, null );

            default:
                throw new IllegalArgumentException( "no id handled!" );
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 2:
                if (data.getCount() > 0) {
                    noAttendanceTV.setVisibility( View.GONE );
                    //shortToast.setText("loading MORE " + page);
                    if (page != 0) {
                        // shortToast.setText("loading more data");
                        //shortToast.show();
                    }

                    Cursor cursor = ((CustomDateWiseAttendanceListRecyclerViewAdapter) mRecyclerView.getAdapter()).getCursor();

                    //fill all existing in adapter
                    MatrixCursor mx = new MatrixCursor( DateWiseStudentAttendanceTableItems.Columns );
                    fillMx( cursor, mx );

                    //fill with additional result
                    fillMx( data, mx );

                    ((CustomDateWiseAttendanceListRecyclerViewAdapter) mRecyclerView.getAdapter()).swapCursor( mx );

                    handlerToWait.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            loadingMore = false;
                        }
                    }, 1000 );
                } else {
                    noAttendanceTV.setVisibility( View.VISIBLE );
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
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems._ID ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.COURSE_ID ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.SECTION_ID ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.SECTION_NAME ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.STUDENT_ID ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.DATE ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.CLASS_ROLL ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.STUDENT_NAME ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.IMAGE_PATH ) ),
                    data.getString( data.getColumnIndex( DateWiseStudentAttendanceTableItems.STATUS ) ),

            } );
        }
    }

    public void insertManualAttendance(View view) {
        startActivity( new Intent( ManualAttendanceListActivity.this,ManualAttendanceInsertListActivity.class ) );

    }

    public class ReceiverThread extends Thread {

        public ReceiverThread() {
        }

        @Override
        public void run() {
            ManualAttendanceListActivity.this.runOnUiThread( new Runnable() {

                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }
            } );
        }
    }
}
