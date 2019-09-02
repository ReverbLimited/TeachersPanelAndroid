package reverb.smartstudy.teacher.thread;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.Activity.attendance.activity.ManualAttendanceListActivity;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.DateWiseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.InsertManualAttendanceModel;
import reverb.smartstudy.teacher.responseModelClass.ManualAttendanceInsertResponseModel;
import reverb.smartstudy.teacher.staticclasses.Functions;

/**
 * Created by mdjahirulislam on 18/01/18.
 */

public class InsertManualAttendanceThread extends Thread{

    private final String TAG = InsertManualAttendanceThread.class.getSimpleName();

    private ConnectionApi connectionApi;
    private Context context;
    private InsertManualAttendanceModel myModel;
    private String delay;
    public ProgressDialog progressDialog;



    public InsertManualAttendanceThread(Context context, InsertManualAttendanceModel myModel){
        this.context = context;
        connectionApi = Functions.getRetrofit().create( ConnectionApi.class );
        this.myModel = myModel;
    }


    @Override
    public void run() {
        super.run();

        Log.d( TAG, "run: Start" );


        myModel.setDelay( "0" );

        final Call<ManualAttendanceInsertResponseModel> coursesResponseCall = connectionApi.setInsertManualAttendance( myModel );
        coursesResponseCall.enqueue( new Callback<ManualAttendanceInsertResponseModel>() {
            @Override
            public void onResponse(Call<ManualAttendanceInsertResponseModel> call, Response<ManualAttendanceInsertResponseModel> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    ManualAttendanceInsertResponseModel body = response.body();
                    boolean error = body.getError();
                    if (!error) {


                        ContentResolver resolver = context.getContentResolver();
                        ContentValues cv = new ContentValues();
                        for (int i = 0; i < myModel.getAttendance().size(); i++) {

                            cv.put( DateWiseStudentAttendanceTableItems.STATUS , myModel.getAttendance().get( i ).getPresent() );

                            int isUpdate = resolver.update( RequestProvider.urlForDateWiseStudentAttendanceTable(), cv,
                                    DateWiseStudentAttendanceTableItems.STUDENT_ID + " =? ",
                                    new String[]{myModel.getAttendance().get( i ).getUserid()} );

                            if (isUpdate>0){
                                context.startActivity( new Intent( context,ManualAttendanceListActivity.class )
                                        .putExtra( "date",myModel.getPresentDate() )
                                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) );
//                                context.finish();

///                                notify();
//                                ManualAttendanceListActivity.ReceiverThread receiverThread = new ManualAttendanceListActivity.ReceiverThread();




                            }else {
                                Log.d( TAG, "onResponse: Attendance not update" );
                            }


                        }




                    } else {
                        Log.d( TAG, "Error true; Msg: " + body.toString() );
                    }


                } else {
                    Log.d( TAG, "Status code is no 200; \n status code is : " + statusCode );
                }

            }

            @Override
            public void onFailure(Call<ManualAttendanceInsertResponseModel> call, Throwable t) {
                Log.d( TAG, "On Response: Failed" );

                myModel.setDelay( "1" );
                Toast.makeText( context, "On Response: Failed", Toast.LENGTH_SHORT ).show();

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
