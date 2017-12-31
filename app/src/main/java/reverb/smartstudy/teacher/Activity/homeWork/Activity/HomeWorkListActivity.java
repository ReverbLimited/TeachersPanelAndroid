package reverb.smartstudy.teacher.Activity.homeWork.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.homeWork.Adapter.CustomCourseListRecyclerViewAdapter;
import reverb.smartstudy.teacher.Activity.homeWork.Adapter.CustomHomeWorkListRecyclerViewAdapter;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.CustomSqliteOpenHelper;
import reverb.smartstudy.teacher.database.HomeWorkListTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;

public class HomeWorkListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = HomeWorkListActivity.class.getSimpleName();

    public final int offset = 30;
    private int page = 0;

    private RecyclerView mRecyclerView;
    private boolean loadingMore = false;
    private CustomHomeWorkListRecyclerViewAdapter mAdapter;
    private CustomSqliteOpenHelper mSqliteOpenHelper ;
    private ConnectionApi connectionApi;
    private Handler handlerToWait = new Handler();
    private FloatingActionButton addHomeWorkBTN;
    private TextView hwCourseNameTV;
    private TextView hwCourseSectionTV;
    private TextView noHomeWorkTV;
    private String courseID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_work_list );
        addHomeWorkBTN = (FloatingActionButton) findViewById( R.id.addHomeWorkFAB );
        hwCourseNameTV = (TextView) findViewById( R.id.hwCourseNameTV );
        hwCourseSectionTV = (TextView) findViewById( R.id.hwCourseSectionTV );
        noHomeWorkTV = (TextView) findViewById( R.id.noHomeWorkTV );
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CustomHomeWorkListRecyclerViewAdapter(this, null);
        mRecyclerView = (RecyclerView) findViewById(R.id.homeWorkListRV);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        hwCourseNameTV.setText( getIntent().getStringExtra( "courseName" ) );
        hwCourseSectionTV.setText( getIntent().getStringExtra( "courseSection" ) );
        courseID = getIntent().getStringExtra( "courseID" );

        getSupportLoaderManager().restartLoader(0, null, HomeWorkListActivity.this);

        addHomeWorkBTN.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( HomeWorkListActivity.this,InsertHomeWorkActivity.class ) );
            }
        } );


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new CursorLoader(this, RequestProvider.urlForHomeWorkListItems(offset * page), null, HomeWorkListTableItems.HW_COURSE_ID +"=?", new String[]{courseID}, null);

            default:
                throw new IllegalArgumentException("no id handled!");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 0:
                if (data.getCount()>0){
                    noHomeWorkTV.setVisibility( View.GONE );
                    //shortToast.setText("loading MORE " + page);
                    if(page!=0){
                        // shortToast.setText("loading more data");
                        //shortToast.show();
                    }

                    Cursor cursor = ((CustomHomeWorkListRecyclerViewAdapter) mRecyclerView.getAdapter()).getCursor();

                    //fill all existing in adapter
                    MatrixCursor mx = new MatrixCursor( HomeWorkListTableItems.Columns);
                    fillMx(cursor, mx);

                    //fill with additional result
                    fillMx(data, mx);

                    ((CustomHomeWorkListRecyclerViewAdapter) mRecyclerView.getAdapter()).swapCursor(mx);

                    handlerToWait.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingMore = false;
                        }
                    }, 1000);
                }else {
                    noHomeWorkTV.setVisibility( View.VISIBLE );
                }

                break;
            default:
                throw new IllegalArgumentException("no loader id handled!");
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void fillMx(Cursor data, MatrixCursor mx) {
        if (data == null)
            return;

        data.moveToPosition(-1);
        while (data.moveToNext()) {
            mx.addRow(new Object[]{
                    data.getString(data.getColumnIndex(HomeWorkListTableItems._ID)),
                    data.getString(data.getColumnIndex(HomeWorkListTableItems.HW_ID)),
                    data.getString(data.getColumnIndex(HomeWorkListTableItems.HW_TITLE)),
                    data.getString(data.getColumnIndex(HomeWorkListTableItems.HW_TASK)),
                    data.getString(data.getColumnIndex(HomeWorkListTableItems.HW_FILE_PATH)),
                    data.getString(data.getColumnIndex(HomeWorkListTableItems.HW_DEADLINE)),
                    data.getString(data.getColumnIndex(HomeWorkListTableItems.CREATED_AT)),
            });
        }
    }
}
