package reverb.smartstudy.teacher.thread;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mdjahirulislam.youtubestyletabs.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.Activity.attendance.activity.CourseOperationActivity;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.CourseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.responseModelClass.CourseStudentAttendanceModel;
import reverb.smartstudy.teacher.staticclasses.Functions;


/**
 * Created by mdjahirulislam on 17/12/17.
 */

public class CourseWiseStudentAttendanceThread extends Thread {
    private final String TAG = CourseWiseStudentAttendanceThread.class.getSimpleName();

    private ConnectionApi connectionApi;
    private Context context;
    private String courseSection;
    private String courseID;
    public ProgressDialog progressDialog;


    public CourseWiseStudentAttendanceThread(Context context, String courseSection){
        this.context = context;
        this.courseSection = courseSection;
        connectionApi = Functions.getRetrofit().create( ConnectionApi.class );
//        db = new DatabaseSource( context );
        progressDialog = new ProgressDialog(context, R.style.MyAlertDialogStyle);

        progressDialog.setCancelable(false);

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

        Log.d( TAG, "run: "+userRequest.toString() );

        final Call<CourseStudentAttendanceModel> coursesResponseCall = connectionApi.getCourseStudentsAttendance( userRequest );
        coursesResponseCall.enqueue( new Callback<CourseStudentAttendanceModel>() {
            @Override
            public void onResponse(Call<CourseStudentAttendanceModel> call, Response<CourseStudentAttendanceModel> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    CourseStudentAttendanceModel body = response.body();
                    boolean error = body.getError();
                    if (!error) {
                        List<CourseStudentAttendanceModel.Datum> classes = body.getData();
                        Log.d( TAG, String.valueOf( classes.size() ) );
//                        Uri a = RequestProvider.urlCourseTable();
//                        int count=context.getContentResolver().delete(RequestProvider.urlHomeWorkTable(), "1",null);
//                        Log.d( TAG,"count 187 line ---> "+count+" \n URI----> "+a );
                        context.getContentResolver().delete(RequestProvider.urlForStudentAttendanceTable(), CourseStudentAttendanceTableItems.COURSE_ID+" =? ",new String []{courseID});


                        if (classes.size() > 0) {
                            for (int i = 0; i < classes.size(); i++) {
                                ContentValues cv = new ContentValues();

                                String sectionID = String.valueOf( (int) Calendar.getInstance().getTimeInMillis() );
                                List<CourseStudentAttendanceModel.Datum.Student> studentList = new ArrayList<>(  );
                                studentList = classes.get( i ).getStudents();

                                if (studentList.size()>0){

                                    for (int j = 0; j < studentList.size(); j++) {

                                        cv.put( CourseStudentAttendanceTableItems.COURSE_ID, courseID );
                                        cv.put( CourseStudentAttendanceTableItems.SECTION_ID,sectionID );
                                        cv.put( CourseStudentAttendanceTableItems.SECTION_NAME, classes.get( i ).getName().toString() );
                                        cv.put( CourseStudentAttendanceTableItems.STUDENT_ID, studentList.get( j ).getUserid().toString());
                                        cv.put( CourseStudentAttendanceTableItems.STUDENT_NAME, studentList.get( j ).getName().toString());
                                        cv.put( CourseStudentAttendanceTableItems.STUDENT_ROLL, studentList.get( j ).getClassroll().toString());
                                        cv.put( CourseStudentAttendanceTableItems.STUDENT_IMAGE_PATH, studentList.get( j ).getImagePath().toString());
                                        cv.put( CourseStudentAttendanceTableItems.LAST_PRESENT_DATE, studentList.get( j ).getLastPresentDate());
                                        cv.put( CourseStudentAttendanceTableItems.TOTAL_PRESENT, studentList.get( j ).getTotalPresent());
                                        context.getContentResolver().insert( RequestProvider.urlForStudentAttendanceListItems( 0 ), cv );
//                                        Log.d( TAG, "onResponse: insert ---> "+cv );
                                    }
                                }

                            }

                        } else {
                            Log.d( TAG, "onResponse: Home Work Not Assign" );
                        }
                        hideDialog();
                        Intent myIntent = new Intent( context, CourseOperationActivity.class );
                        myIntent.putExtra( "courseSection", courseSection );
//                        myIntent.putExtra("noData", noData[0]);
                        context.startActivity( myIntent );

                    } else {
                        hideDialog();
                        Log.d( TAG, "Error true; Msg: " + body.getError() );
                    }


                } else {
                    hideDialog();
                    Log.d( TAG, "Status code is no 200; \n status code is : " + statusCode );
                }

            }

            @Override
            public void onFailure(Call<CourseStudentAttendanceModel> call, Throwable t) {

                hideDialog();
                Log.d( TAG, "On Response: Failed" );
                Intent myIntent = new Intent( context, CourseOperationActivity.class );
                context.startActivity( myIntent );

            }
        } );


    }

    public void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
