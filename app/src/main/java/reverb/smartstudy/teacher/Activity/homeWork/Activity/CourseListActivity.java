package reverb.smartstudy.teacher.Activity.homeWork.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.mdjahirulislam.youtubestyletabs.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.Activity.homeWork.Adapter.CustomCourseListRecyclerViewAdapter;
import reverb.smartstudy.teacher.Adapter.RecyclerTouchListener;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.CourseTableItems;
import reverb.smartstudy.teacher.database.CustomSqliteOpenHelper;
import reverb.smartstudy.teacher.database.NewsTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.CourseResponseModel;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.staticclasses.Functions;

public class CourseListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = CourseListActivity.class.getSimpleName();

    public final int offset = 30;
    private int page = 0;

    private RecyclerView mRecyclerView;
    private boolean loadingMore = false;
    private CustomCourseListRecyclerViewAdapter mAdapter;
    private CustomSqliteOpenHelper mSqliteOpenHelper ;
    private ConnectionApi connectionApi;
    private Handler handlerToWait = new Handler();
    private int tagCounter = 1;

    private int from;

//    Context c1;
    private Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mSqliteOpenHelper =new CustomSqliteOpenHelper(this);
        setContentView( R.layout.activity_course_list );
//        c1=this;

        from = getIntent().getIntExtra( "from",0 );
        connectionApi = Functions.getRetrofit().create(ConnectionApi.class);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CustomCourseListRecyclerViewAdapter(this, null, from);
        mRecyclerView = (RecyclerView) findViewById(R.id.coursesRV);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        fetchCourses();

        getSupportLoaderManager().restartLoader(0, null, CourseListActivity.this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int maxPositions = layoutManager.getItemCount();
                if (lastVisibleItemPosition == maxPositions - 1) { //self comment
                    //  if (lastVisibleItemPosition == maxPositions - 10) {
                    if (loadingMore) {
                        return;
                    }
                    loadingMore = true;
                    page++;
                    getSupportLoaderManager().restartLoader(0, null, CourseListActivity.this);
                }
            }
        });


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id) {
            case 0:
                return new CursorLoader(this, RequestProvider.urlForCourseItems(offset * page), null, null, null, null);
//                return null;
            default:
                throw new IllegalArgumentException("no id handled!");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 0:
                //shortToast.setText("loading MORE " + page);
                if(page!=0){
                    // shortToast.setText("loading more data");
                    //shortToast.show();
                }

                Cursor cursor = ((CustomCourseListRecyclerViewAdapter) mRecyclerView.getAdapter()).getCursor();

                //fill all existing in adapter
                MatrixCursor mx = new MatrixCursor(CourseTableItems.Columns);
                fillMx(cursor, mx);

                //fill with additional result
                fillMx(data, mx);

                ((CustomCourseListRecyclerViewAdapter) mRecyclerView.getAdapter()).swapCursor(mx);


                handlerToWait.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingMore = false;
                    }
                }, 1000);

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
                    data.getString(data.getColumnIndex(CourseTableItems._ID)),
                    data.getString(data.getColumnIndex(CourseTableItems.COURSE_ID)),
                    data.getString(data.getColumnIndex(CourseTableItems.NAME)),
                    data.getString(data.getColumnIndex(CourseTableItems.CODE_NAME)),
                    data.getString(data.getColumnIndex(CourseTableItems.CLASS)),
            });
        }
    }

    public void fetchCourses(){

//        ConnectionApi service = retrofit.create(ConnectionApi.class);
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername( SharedPref.getInstance(getApplicationContext()).getUsername());
        userRequest.setPassword(SharedPref.getInstance(getApplicationContext()).getPassword());

        final Call<CourseResponseModel> coursesResponseCall = connectionApi.getCourse( userRequest );
        coursesResponseCall.enqueue(new Callback<CourseResponseModel>() {
            @Override
            public void onResponse(Call<CourseResponseModel> call, Response<CourseResponseModel> response) {
                int statusCode=response.code();
                if (statusCode == 200){
                    CourseResponseModel body = response.body();
                    boolean error = body.getError();
                    if (!error){
                        List<CourseResponseModel.Datum> courses = body.getData();
                        Log.d(TAG, String.valueOf(courses.size()));
                        Uri a= RequestProvider.urlCourseTable();
                        int count=getContentResolver().delete(RequestProvider.urlCourseTable(), "1",null);
                        for (int i = 0; i < courses.size(); i++) {
                            ContentValues cv = new ContentValues();
                            cv.put(CourseTableItems.COURSE_ID, (courses.get(i).getId()));
                            cv.put(CourseTableItems.CREATED_AT,(Functions.convertTimeStamp(courses.get( i ).getCreatedAt())));
                            cv.put(CourseTableItems.UPDATED_AT,(courses.get(i).getUpdatedAt()));
                            cv.put(CourseTableItems.NAME, (courses.get(i).getName()));
                            cv.put(CourseTableItems.CODE_NAME, (courses.get(i).getCodename()));
                            cv.put(CourseTableItems.CLASS, (courses.get(i).getClass_()));
//                            cv.put(CourseTableItems.NAME, (courses.get(i).getName()));
//                            cv.put(CourseTableItems.DESCRIPTION, (courses.get(i).getDescription()));
//                            cv.put(CourseTableItems.IMAGE,(courses.get(i).getImage()));
                            Log.d( TAG, "onResponse: course_id :" +courses.get( i ).getId());
                            getContentResolver().insert(RequestProvider.urlForCourseItems(0), cv);
                        }

                        getSupportLoaderManager().restartLoader(0, null, CourseListActivity.this);
                    }else {
                        Log.d( TAG, "Error true; Msg: "+body.getError() );
                    }


                }else {
                    Log.d(TAG, "Status code is no 200; \n status code is : "+statusCode );
                }


            }

            @Override
            public void onFailure(Call<CourseResponseModel> call, Throwable t) {

                Log.d( TAG, "onCreate 7--->onFailure--->  tagCounter : " +tagCounter++);
                Log.d("NewsActivity","On Response: Failed");
//                getSupportLoaderManager().restartLoader(0, null, CourseListActivity.this);

            }
        });
    }
}
