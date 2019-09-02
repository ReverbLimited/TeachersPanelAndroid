package reverb.smartstudy.teacher.thread;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.AttendanceClassScheduleTableItems;
import reverb.smartstudy.teacher.database.CourseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.responseModelClass.CourseScheduleWithAttendanceResponseModel;
import reverb.smartstudy.teacher.staticclasses.Functions;

/**
 * Created by mdjahirulislam on 08/01/18.
 */

public class CourseWiseScheduleWithAttendanceThread extends Thread {
    private final String TAG = CourseWiseScheduleWithAttendanceThread.class.getSimpleName();

    private ConnectionApi connectionApi;
    //    private DatabaseSource db;
    private Context context;
    private String courseID;
//    public ProgressDialog progressDialog;


    public CourseWiseScheduleWithAttendanceThread(Context context){
        this.context = context;
        connectionApi = Functions.getRetrofit().create( ConnectionApi.class );
    }

    @Override
    public void run() {
        super.run();

        Log.d( TAG, "run: Start" );
        courseID = SharedPref.getInstance( context ).getCourseId();

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername( SharedPref.getInstance( context ).getUsername() );
        userRequest.setPassword( SharedPref.getInstance( context ).getPassword() );
        userRequest.setCourseID( courseID );

        final Call<CourseScheduleWithAttendanceResponseModel> coursesResponseCall = connectionApi.getCourseScheduleWithAttendance( userRequest );
        coursesResponseCall.enqueue( new Callback<CourseScheduleWithAttendanceResponseModel>() {
            @Override
            public void onResponse(Call<CourseScheduleWithAttendanceResponseModel> call, Response<CourseScheduleWithAttendanceResponseModel> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    CourseScheduleWithAttendanceResponseModel body = response.body();
                    boolean error = body.getError();
                    if (!error) {
                        List<CourseScheduleWithAttendanceResponseModel.Schedule> schedules = body.getSchedule();
                        Log.d( TAG, String.valueOf( schedules.size() ) );
//
                        context.getContentResolver().delete( RequestProvider.urlForStudentAttendanceClassScheduleTable(), CourseStudentAttendanceTableItems.COURSE_ID+" =? ",new String []{courseID});


                        if (schedules.size() > 0) {
                            for (int i = 0; i < schedules.size(); i++) {
                                ContentValues cv = new ContentValues();
                                cv.put( AttendanceClassScheduleTableItems.CLASS_SCHEDULE_ID, schedules.get( i ).getId() );
                                cv.put( AttendanceClassScheduleTableItems.COURSE_ID, courseID );
                                cv.put( AttendanceClassScheduleTableItems.CLASS_SCHEDULE_DATE, schedules.get( i ).getDate() );
                                cv.put( AttendanceClassScheduleTableItems.TOTAL_PRESENT_STUDENT_NUMBER, schedules.get( i ).getToatPresentStudentNumber() );
                                cv.put( AttendanceClassScheduleTableItems.CLASS_DAY, schedules.get( i ).getClassDay());
                                cv.put( AttendanceClassScheduleTableItems.CLASS_TIME, schedules.get( i ).getClassTime() );

                                context.getContentResolver().insert( RequestProvider.urlForAttendanceClassScheduleListItems( 0 ), cv );

                            }

                        } else {
                            Log.d( TAG, "onResponse: Class Schedule not Found" );
                            Toast.makeText( context, "Class Schedule not Found", Toast.LENGTH_SHORT ).show();
                        }

                    } else {
                        Log.d( TAG, "Error true; Msg: " + body.getError() );
                    }


                } else {
                    Log.d( TAG, "Status code is no 200; \n status code is : " + statusCode );
                }

            }

            @Override
            public void onFailure(Call<CourseScheduleWithAttendanceResponseModel> call, Throwable t) {
                Log.d( TAG, "On Response: Failed" );

                Toast.makeText( context, "On Response: Failed", Toast.LENGTH_SHORT ).show();

            }
        } );


        Log.d( TAG, "run: last line in this method" );
    }

}
