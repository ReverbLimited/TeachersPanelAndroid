package reverb.smartstudy.teacher.thread;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.mdjahirulislam.youtubestyletabs.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.Activity.attendance.activity.ManualAttendanceListActivity;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.DateWiseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.responseModelClass.DateWiseStudentAttendanceListResponseModel;
import reverb.smartstudy.teacher.staticclasses.Functions;

/**
 * Created by mdjahirulislam on 15/01/18.
 */

public class DateWiseStudentAttendanceThread extends Thread {
    private final String TAG = DateWiseStudentAttendanceThread.class.getSimpleName();

    private ConnectionApi connectionApi;
    private Context context;
    private String courseID;
    private String courseScheduleDate;
    public ProgressDialog progressDialog;


    public DateWiseStudentAttendanceThread(Context context,String courseScheduleDate){
        this.context = context;
        this.courseScheduleDate = courseScheduleDate;
        connectionApi = Functions.getRetrofit().create( ConnectionApi.class );
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
        userRequest.setDate( courseScheduleDate );


        final Call<DateWiseStudentAttendanceListResponseModel> coursesResponseCall = connectionApi.getDateWiseStudentAttendance( userRequest );
        coursesResponseCall.enqueue( new Callback<DateWiseStudentAttendanceListResponseModel>() {
            @Override
            public void onResponse(Call<DateWiseStudentAttendanceListResponseModel> call, Response<DateWiseStudentAttendanceListResponseModel> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    DateWiseStudentAttendanceListResponseModel body = response.body();
                    boolean error = body.getError();
                    if (!error) {
                        List<DateWiseStudentAttendanceListResponseModel.Datum> sections = body.getData();
//                        Log.d( TAG, sections.toString() );
                        Log.d( TAG, String.valueOf( sections.size() ) );

                        context.getContentResolver().delete( RequestProvider.urlForDateWiseStudentAttendanceTable(), DateWiseStudentAttendanceTableItems.DATE+" =? ",new String []{courseScheduleDate});


                        if (sections.size() > 0) {
                            for (int i = 0; i < sections.size(); i++) {
                                ContentValues cv = new ContentValues();

                                String sectionID = String.valueOf( (int) Calendar.getInstance().getTimeInMillis() );
                                List<DateWiseStudentAttendanceListResponseModel.Datum.Student> studentList = new ArrayList<>(  );
                                studentList = sections.get( i ).getStudents();

                                if (studentList.size()>0) {

                                    for (int j = 0; j < studentList.size(); j++) {
                                        cv.put( DateWiseStudentAttendanceTableItems.COURSE_ID, courseID );
                                        cv.put( DateWiseStudentAttendanceTableItems.SECTION_ID, sectionID );
                                        cv.put( DateWiseStudentAttendanceTableItems.SECTION_NAME, sections.get( i ).getName() );
                                        cv.put( DateWiseStudentAttendanceTableItems.STUDENT_ID, studentList.get( j ).getUserid() );
                                        cv.put( DateWiseStudentAttendanceTableItems.DATE, courseScheduleDate);
                                        cv.put( DateWiseStudentAttendanceTableItems.CLASS_ROLL, studentList.get( j ).getClassroll() );
                                        cv.put( DateWiseStudentAttendanceTableItems.STUDENT_NAME, studentList.get( j ).getName() );
                                        cv.put( DateWiseStudentAttendanceTableItems.IMAGE_PATH, studentList.get( j ).getImagePath());
                                        cv.put( DateWiseStudentAttendanceTableItems.STATUS, studentList.get( j ).getStatus());

                                        context.getContentResolver().insert( RequestProvider.urlForDateWiseAttendanceListItems( 0 ), cv );
                                        Log.d( TAG, "onResponse: insert ---> "+cv );


                                    }

                                }else {
                                    hideDialog();
                                    Log.d( TAG, "onResponse: student list not found" );
                                    Toast.makeText( context, "onResponse: student list not found", Toast.LENGTH_SHORT ).show();
                                }

                            }
                            hideDialog();
                            Intent myIntent = new Intent( context, ManualAttendanceListActivity.class );
                            myIntent.putExtra( "date", courseScheduleDate );
                            context.startActivity( myIntent );

                        } else {
                            hideDialog();
                            Log.d( TAG, "onResponse: Class Schedule not Found" );
                            Toast.makeText( context, "Class Schedule not Found", Toast.LENGTH_SHORT ).show();
                        }

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
            public void onFailure(Call<DateWiseStudentAttendanceListResponseModel> call, Throwable t) {
                hideDialog();
                Log.d( TAG, "On Response: Failed" );

                Toast.makeText( context, "Check Your Internet Connection\nYou Can't Show Update Attendance", Toast.LENGTH_LONG ).show();

                Intent myIntent = new Intent( context, ManualAttendanceListActivity.class );
                myIntent.putExtra( "date", courseScheduleDate );
                context.startActivity( myIntent );

            }
        } );


        Log.d( TAG, "run: last line in this method" );
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
