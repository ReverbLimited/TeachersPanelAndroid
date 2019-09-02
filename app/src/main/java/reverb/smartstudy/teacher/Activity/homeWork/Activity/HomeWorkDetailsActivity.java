package reverb.smartstudy.teacher.Activity.homeWork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.mdjahirulislam.youtubestyletabs.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.Activity.homeWork.Adapter.CustomSubmittedHWListRecyclerViewAdapter;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.responseModelClass.SubmittedHomeWorkResponseModel;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.staticclasses.Functions;


public class HomeWorkDetailsActivity extends AppCompatActivity {
    private static final String TAG = HomeWorkDetailsActivity.class.getSimpleName();
    public final int offset = 30;
    private int page = 0;

    private RecyclerView submittedStudentRV;
    private TextView hwTitleTV;
    private TextView hwTaskTV;
    private TextView hwDeadLineTV;
    private TextView noHWSubmitTV;
    private String homeWorkUniqueID;

    private CustomSubmittedHWListRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ConnectionApi connectionApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_work_deatils );
        Log.d( TAG, "onCreate: " );

        hwTaskTV = (TextView) findViewById( R.id.submittedTaskTV );
        hwTitleTV = (TextView) findViewById( R.id.submittedHWNameTV );
        hwDeadLineTV = (TextView) findViewById( R.id.submittedDeadLineTV );
        noHWSubmitTV = (TextView) findViewById( R.id.noHWSubmitTV );
        connectionApi = Functions.getRetrofit().create( ConnectionApi.class );

        homeWorkUniqueID = getIntent().getStringExtra( "hwUniqueId" );
        hwTitleTV.setText( getIntent().getStringExtra( "title" ) );
        hwTaskTV.setText( getIntent().getStringExtra( "task" ) );
        hwDeadLineTV.setText( "Dead Line: " + getIntent().getStringExtra( "deadLine" ) );
        submittedStudentRV = (RecyclerView) findViewById( R.id.homeWorkSubmissionListRV );
//        mAdapter = new CustomSubmittedHWListRecyclerViewAdapter( HomeWorkDetailsActivity.this, null );
        mLayoutManager = new LinearLayoutManager( this );
        submittedStudentRV.setLayoutManager( mLayoutManager );
//        submittedStudentRV.setAdapter(mAdapter);

//        getSupportLoaderManager().restartLoader(0, null, HomeWorkDetailsActivity.this);

        fetchSubmittedStudentList();


    }


    public void fetchSubmittedStudentList() {

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername( SharedPref.getInstance( getApplicationContext() ).getUsername() );
        userRequest.setPassword( SharedPref.getInstance( getApplicationContext() ).getPassword() );
        userRequest.setHomeworkID( homeWorkUniqueID );

        Call<SubmittedHomeWorkResponseModel> submittedHomeWorkResponseModelCall = connectionApi.getHomeWorkSubmittedList( userRequest );

        submittedHomeWorkResponseModelCall.enqueue( new Callback<SubmittedHomeWorkResponseModel>() {
            @Override
            public void onResponse(Call<SubmittedHomeWorkResponseModel> call, Response<SubmittedHomeWorkResponseModel> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    SubmittedHomeWorkResponseModel body = response.body();
                    boolean error = body.getError();
                    if (!error) {
                        List<SubmittedHomeWorkResponseModel.SubmittedStudentList> submittedStudentLists = body.getData();
                        Log.d( TAG, String.valueOf( submittedStudentLists.size() ) + "\n" + body.getData().toString() );

                        if (submittedStudentLists.size() > 0) {
                            noHWSubmitTV.setVisibility( View.GONE );
                            for (int i = 0; i < submittedStudentLists.size(); i++) {

                                mAdapter = new CustomSubmittedHWListRecyclerViewAdapter( HomeWorkDetailsActivity.this, (ArrayList<SubmittedHomeWorkResponseModel.SubmittedStudentList>) submittedStudentLists );
                                submittedStudentRV.setAdapter( mAdapter );

                            }
                        } else{
                            noHWSubmitTV.setVisibility( View.VISIBLE );
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<SubmittedHomeWorkResponseModel> call, Throwable t) {

                noHWSubmitTV.setVisibility( View.VISIBLE );
                noHWSubmitTV.setText( "No Internet Connection \nor \nSomething Wrong!!!" );
            }
        } );

    }
}
