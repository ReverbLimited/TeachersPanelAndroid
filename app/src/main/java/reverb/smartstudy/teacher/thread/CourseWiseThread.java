package reverb.smartstudy.teacher.thread;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.Activity.homeWork.Activity.HomeWorkListActivity;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.HomeWorkListTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.HomeWorkListPojoModel;
import reverb.smartstudy.teacher.model.SubmittedHomeWorkResponseModel;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.staticclasses.Functions;


/**
 * Created by mdjahirulislam on 17/12/17.
 */

public class CourseWiseThread extends Thread {
    private final String TAG = CourseWiseThread.class.getSimpleName();

    private ConnectionApi connectionApi;
//    private DatabaseSource db;
    private Context context;
    private String courseName;
    private String courseSection;
    private String courseID;


    public CourseWiseThread(Context context,String courseName,String courseSection,String courseID){
        this.context = context;
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseSection = courseSection;
        this.
        connectionApi = Functions.getRetrofit().create( ConnectionApi.class );
//        db = new DatabaseSource( context );

    }

    @Override
    public void run() {
        super.run();

        Log.d( TAG, "run: Start" );

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername( SharedPref.getInstance( context ).getUsername() );
        userRequest.setPassword( SharedPref.getInstance( context ).getPassword() );
        userRequest.setCourseID( courseID );

        final Call<HomeWorkListPojoModel> coursesResponseCall = connectionApi.getHomeWorkList( userRequest );
        coursesResponseCall.enqueue( new Callback<HomeWorkListPojoModel>() {
            @Override
            public void onResponse(Call<HomeWorkListPojoModel> call, Response<HomeWorkListPojoModel> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    HomeWorkListPojoModel body = response.body();
                    boolean error = body.getError();
                    if (!error) {
                        List<HomeWorkListPojoModel.Datum> homeWorks = body.getData();
                        Log.d( TAG, String.valueOf( homeWorks.size() ) );
                        Uri a = RequestProvider.urlCourseTable();
//                        int count=context.getContentResolver().delete(RequestProvider.urlHomeWorkTable(), "1",null);
//                        Log.d( TAG,"count 187 line ---> "+count+" \n URI----> "+a );
                        if (homeWorks.size() > 0) {
                            for (int i = 0; i < homeWorks.size(); i++) {
                                ContentValues cv = new ContentValues();

                                cv.put( HomeWorkListTableItems.HW_ID, (homeWorks.get( i ).getId()) );
                                cv.put( HomeWorkListTableItems.CREATED_AT, (Functions.convertTimeStamp( homeWorks.get( i ).getCreatedAt() )) );
                                cv.put( HomeWorkListTableItems.UPDATED_AT, (homeWorks.get( i ).getUpdatedAt()) );
                                cv.put( HomeWorkListTableItems.HW_TITLE, (homeWorks.get( i ).getName()) );
                                cv.put( HomeWorkListTableItems.HW_TASK, (homeWorks.get( i ).getTask()) );
                                cv.put( HomeWorkListTableItems.HW_FILE_PATH, (homeWorks.get( i ).getFilepath()) );
                                cv.put( HomeWorkListTableItems.HW_DEADLINE, (homeWorks.get( i ).getDeadline()) );
                                cv.put( HomeWorkListTableItems.HW_COURSE_ID, (homeWorks.get( i ).getCoursesId()) );
                                cv.put( HomeWorkListTableItems.HW_COURSE_NAME, (homeWorks.get( i ).getCoursename()) );
                                cv.put( HomeWorkListTableItems.HW_TEACHER_ID, (homeWorks.get( i ).getTeachersId()) );
                                context.getContentResolver().insert( RequestProvider.urlForHomeWorkListItems( 0 ), cv );
                            }

                        } else {
                            Log.d( TAG, "onResponse: Home Work Not Assign" );
                        }
                        Intent myIntent = new Intent( context, HomeWorkListActivity.class );
                        myIntent.putExtra( "courseName", courseName );
                        myIntent.putExtra( "courseSection", courseSection );
                        myIntent.putExtra( "courseID", courseID );
//                        myIntent.putExtra("noData", noData[0]);
                        context.startActivity( myIntent );

                    } else {
                        Log.d( TAG, "Error true; Msg: " + body.getError() );
                    }


                } else {
                    Log.d( TAG, "Status code is no 200; \n status code is : " + statusCode );
                }

            }

            @Override
            public void onFailure(Call<HomeWorkListPojoModel> call, Throwable t) {

//                Log.d( TAG, "onCreate 7--->onFailure--->  tagCounter : " +tagCounter++);
                Log.d( "NewsActivity", "On Response: Failed" );
//                getSupportLoaderManager().restartLoader(0, null, CourseListActivity.this);
//                noData[0] = "1";

                Intent myIntent = new Intent( context, HomeWorkListActivity.class );
                myIntent.putExtra( "courseName", courseName );
                myIntent.putExtra( "courseSection", courseSection );
                myIntent.putExtra( "courseID", courseID );
//                myIntent.putExtra("noData", noData[0]);
                context.startActivity( myIntent );

            }
        } );


    }
}
