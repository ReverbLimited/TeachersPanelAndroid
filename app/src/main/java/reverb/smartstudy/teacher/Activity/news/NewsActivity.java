package reverb.smartstudy.teacher.Activity.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;



import java.util.ArrayList;
import com.example.mdjahirulislam.youtubestyletabs.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import reverb.smartstudy.teacher.Adapter.CustomCursorRecyclerViewAdapter;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.CustomSqliteOpenHelper;
import reverb.smartstudy.teacher.database.NewsTableItems;
import reverb.smartstudy.teacher.model.NewsResponseModel;
import reverb.smartstudy.teacher.preference.SharedPref;

import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.staticclasses.Functions;


public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public final int offset = 30;
    private int page = 0;

    private RecyclerView mRecyclerView;
    private boolean loadingMore = false;
    private Toast shortToast;
    CustomCursorRecyclerViewAdapter mAdapter;
    Context c1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c1=this;
        CustomSqliteOpenHelper mSqliteOpenHelper = new CustomSqliteOpenHelper(c1);
        setContentView(R.layout.news);
       // getContentResolver().delete(RequestProvider.urlNewsTable(), null,null);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CustomCursorRecyclerViewAdapter(this, null);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //int itemsCountLocal = getItemsCountLocal();
       // if (itemsCountLocal == 0) {
        //    fillTestElements();
        //}

        getSupportLoaderManager().restartLoader(0, null, NewsActivity.this);
        fetchNews();

        shortToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int maxPositions = layoutManager.getItemCount();

                if (lastVisibleItemPosition == maxPositions - 1) { //self comment
                  //  if (lastVisibleItemPosition == maxPositions - 10) {
                    if (loadingMore)
                        return;

                    loadingMore = true;
                    page++;
                    getSupportLoaderManager().restartLoader(0, null, NewsActivity.this);
                }
            }
        });

      // getSupportLoaderManager().restartLoader(0, null, NewsActivity.this);  //coped to failure, otherwise load double data for onresponse
    }

    private void fillTestElements() {
        int size = 150;
        ContentValues[] cvArray = new ContentValues[size];
        for (int i = 0; i < cvArray.length; i++) {
            ContentValues cv = new ContentValues();
            cv.put(NewsTableItems.NAME, ("Sakib went to School " + i));
            cv.put(NewsTableItems.DESCRIPTION, ("He always miss the school. Very bad habit " + i));
            cv.put(NewsTableItems.CREATED_AT,("2016-02-15 03:04:30"));
            cv.put(NewsTableItems.UPDATED_AT,("2016-02-16 05:04:30"));
            cv.put(NewsTableItems.IMAGE,("default.jpg"));
            cvArray[i] = cv;
        }
       // getContentResolver().bulkInsert(RequestProvider.urlForItems(0), cvArray);
    }

    public void fillDBWithDummyData() {

        ContentValues cv = new ContentValues();
        cv.put(NewsTableItems.NAME, ("School will be closed!" ));
        cv.put(NewsTableItems.DESCRIPTION, ("He always miss the school. Very bad habit "));
        cv.put(NewsTableItems.CREATED_AT,("2016-02-15 03:04:30"));
        cv.put(NewsTableItems.UPDATED_AT,("2016-02-16 05:04:30"));
        cv.put(NewsTableItems.IMAGE,("default.jpg"));
        getContentResolver().insert(RequestProvider.urlForItems(0), cv);
        /*
        ContentValues values = new ContentValues();
        values.put(NewsProvider.NAME, "School will be closed!");
        values.put(NewsProvider.CREATED_AT, "2017-07-30 13:21:16");
        values.put(NewsProvider.UPDATED_AT, "2017-07-31 17:28:08");
        values.put(NewsProvider.DESCRIPTION, "Due to massive flood, school will be closed");
        values.put(NewsProvider.IMAGE, "School will be opened!");
        getContentResolver().insert(NewsProvider.CONTENT_URI, values);
         */
    }

    public void fetchNews(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://smartstudy.com.bd/demo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConnectionApi service = retrofit.create(ConnectionApi.class);
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(SharedPref.getInstance(getApplicationContext()).getUsername());
        userRequest.setPassword(SharedPref.getInstance(getApplicationContext()).getPassword());

        Call<ArrayList<NewsResponseModel>> newsResponseCall = service.getNews( userRequest );
        newsResponseCall.enqueue(new Callback<ArrayList<NewsResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsResponseModel>> call, Response<ArrayList<NewsResponseModel>> response) {
                int statusCode=response.code();
                ArrayList<NewsResponseModel> news=response.body();
                Log.d("TestActivity", String.valueOf(statusCode));
//                Uri a=RequestProvider.urlNewsTable();
//                int count=getContentResolver().delete(RequestProvider.urlNewsTable(), "1",null);
//                Log.d( TAG,"count 159 line ---> "+count );
                for (int i = 0; i < news.size(); i++) {
                    ContentValues cv = new ContentValues();
                    cv.put(NewsTableItems.NEWS_ID, (news.get(i).getNewsid() ));
                    cv.put(NewsTableItems.NAME, (news.get(i).getName() ));
                    cv.put(NewsTableItems.DESCRIPTION, (news.get(i).getDescription()));
                    cv.put(NewsTableItems.CREATED_AT,(Functions.convertTimeStamp(news.get(i).getCreated_at())));
                    cv.put(NewsTableItems.UPDATED_AT,(news.get(i).getUpdated_at()));
                    cv.put(NewsTableItems.IMAGE,(news.get(i).getImage()));
                    getContentResolver().insert(RequestProvider.urlForItems(0), cv);
                }
               // mAdapter.notify();
                //mAdapter.notifyDataSetChanged();
                getSupportLoaderManager().restartLoader(0, null, NewsActivity.this);
                //mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<NewsResponseModel>> call, Throwable t) {

                Log.d("NewsActivity","On Response: Failed");
//              getSupportLoaderManager().restartLoader(0, null, NewsActivity.this);

            }
        });
    }

    private int getItemsCountLocal() {
        int itemsCount = 0;

        Cursor query = getContentResolver().query(RequestProvider.urlForItems(0), null, null, null, null);
        if (query != null) {
            itemsCount = query.getCount();
            query.close();
        }
        return itemsCount;
    }

    /*loader*/

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new CursorLoader(this, RequestProvider.urlForItems(offset * page), null, null, null, null);
            default:
                throw new IllegalArgumentException("no id handled!");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 0:
                Log.d(TAG, "onLoadFinished: loading MORE");
                //shortToast.setText("loading MORE " + page);
                if(page!=0){
               // shortToast.setText("loading more data");
                //shortToast.show();
                }

                Cursor cursor = ((CustomCursorRecyclerViewAdapter) mRecyclerView.getAdapter()).getCursor();

                //fill all existing in adapter
                MatrixCursor mx = new MatrixCursor(NewsTableItems.Columns);
                fillMx(cursor, mx);

                //fill with additional result
                fillMx(data, mx);

                ((CustomCursorRecyclerViewAdapter) mRecyclerView.getAdapter()).swapCursor(mx);


                handlerToWait.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingMore = false;
                    }
                }, 2000);

                break;
            default:
                throw new IllegalArgumentException("no loader id handled!");
        }
    }

    private Handler handlerToWait = new Handler();

    private void fillMx(Cursor data, MatrixCursor mx) {
        if (data == null)
            return;

        data.moveToPosition(-1);
        while (data.moveToNext()) {
            mx.addRow(new Object[]{
                    data.getString(data.getColumnIndex(NewsTableItems._ID)),
                    data.getString(data.getColumnIndex(NewsTableItems.NEWS_ID)),
                    data.getString(data.getColumnIndex(NewsTableItems.NAME)),
                    data.getString(data.getColumnIndex(NewsTableItems.CREATED_AT)),
                    data.getString(data.getColumnIndex(NewsTableItems.DESCRIPTION)),
            });
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // TODO: 2016-10-13
    }

    //

    private static final String TAG = "NewsActivity";

}