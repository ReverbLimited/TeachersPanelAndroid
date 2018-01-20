package reverb.smartstudy.teacher.ViewHolder;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdjahirulislam.youtubestyletabs.R;

import java.util.ArrayList;

import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.AttendanceClassScheduleTableItems;
import reverb.smartstudy.teacher.database.DateWiseStudentAttendanceTableItems;
import reverb.smartstudy.teacher.model.InsertManualAttendanceModel;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.staticclasses.Functions;
import reverb.smartstudy.teacher.thread.InsertManualAttendanceThread;

/**
 * Created by mdjahirulislam on 15/01/18.
 */

public class AllStudentDateWiseAttendanceListViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = AllStudentDateWiseAttendanceListViewHolder.class.getSimpleName();

    private TextView nameTV;
    private TextView rollTV;
    private TextView statusTV;
    private int status;
    private Context context;
    private int counter = 0;
    private String name;
    private String roll;

    private String presentDate;
    private String presentTime;
    private String delay;
    private ArrayList<InsertManualAttendanceModel.Attendance> attendances;
    private String studentID;
    private String updateStatus = "0";
    private InsertManualAttendanceModel.Attendance attendance;





    public AllStudentDateWiseAttendanceListViewHolder(final View itemView) {
        super( itemView );
        context = itemView.getContext();

        nameTV = (TextView) itemView.findViewById( R.id.dateWiseAttendanceStudentNameTV );
        rollTV = (TextView) itemView.findViewById( R.id.dateWiseAttendanceStudentRollTV );
        statusTV = (TextView) itemView.findViewById( R.id.dateWiseAttendanceStudentStatusTV );

        attendances = new ArrayList<>(  );
        attendance = new InsertManualAttendanceModel.Attendance();


        statusTV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;

                ContentResolver resolver = context.getContentResolver();
                Cursor studentCursor =
                        resolver.query( RequestProvider.urlForAttendanceClassScheduleListItems( 0 ),
                                null, AttendanceClassScheduleTableItems.COURSE_ID + " =? ",
                                new String[]{SharedPref.getInstance( context ).getCourseId()}, null );
                studentCursor.moveToFirst();


                String[] totalTime = studentCursor.getString( studentCursor.getColumnIndex( AttendanceClassScheduleTableItems.CLASS_TIME ) ).split("-");

                presentTime = Functions.convertTimeInHMS( totalTime[0] );

                //open dialog


                LayoutInflater li = LayoutInflater.from( context );
                final View promptsView = li.inflate( R.layout.manual_single_attendance_dialog, null );

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( context );

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView( promptsView );
                final AlertDialog alertDialog = alertDialogBuilder.create();

                final TextView studentName = (TextView) promptsView.findViewById( R.id.insertDialogAttendanceStudentNameTV );
                final TextView studentRoll = (TextView) promptsView.findViewById( R.id.insertDialogAttendanceStudentRollTV );
                final TextView cancelTV = (TextView) promptsView.findViewById( R.id.insertDialogAttendanceCancelTV );
                final Button okBTN = (Button) promptsView.findViewById( R.id.insertDialogAttendanceOkBTN );

                final CheckBox presentCB = (CheckBox) promptsView.findViewById( R.id.dialogPresentCB );
                final CheckBox absentCB = (CheckBox) promptsView.findViewById( R.id.dialogAbsentCB );
                final CheckBox lateCB = (CheckBox) promptsView.findViewById( R.id.dialogLateCB );

                studentName.setText( "Name: " + name );
                studentRoll.setText( "Roll: " + roll );



                if (statusTV.getText().toString().toLowerCase().equals( "present" )){
                    presentCB.setChecked( true );
                    absentCB.setChecked( false );
                    lateCB.setChecked( false );
                }else  if (statusTV.getText().toString().toLowerCase().equals( "absent" )){
                    presentCB.setChecked( false );
                    absentCB.setChecked( true );
                    lateCB.setChecked( false );
                }else  if (statusTV.getText().toString().toLowerCase().equals( "late" )){
                    presentCB.setChecked( false );
                    absentCB.setChecked( false );
                    lateCB.setChecked( true );
                }
                cancelTV.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }

                } );


                presentCB.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked){
                            absentCB.setChecked( false );
                            lateCB.setChecked( false );
                            updateStatus = "1";
                            attendance.setPresent( updateStatus );
                            attendance.setUserid( studentID );
                            attendances.add( attendance );
                        }

                    }
                } );

                absentCB.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked){
                            presentCB.setChecked( false );
                            lateCB.setChecked( false );
                            updateStatus = "0";
                        }

                    }
                } );

                lateCB.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked){
                            absentCB.setChecked( false );
                            presentCB.setChecked( false );
                            updateStatus = "1";
                        }

                    }
                } );

                okBTN.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText( context, "Hello", Toast.LENGTH_SHORT ).show();

                        InsertManualAttendanceModel myModel = new InsertManualAttendanceModel();
                        myModel.setUsername( SharedPref.getInstance( context ).getUsername() );
                        myModel.setPassword( SharedPref.getInstance( context ).getPassword() );
                        myModel.setCourseID( SharedPref.getInstance( context ).getCourseId() );
                        myModel.setPresentDate( presentDate );
                        myModel.setPresentTime( presentTime );
                        myModel.setAttendance( attendances );

                        Log.d( TAG, "onClick: InsertManualAttendanceModel "+myModel.toString() );

                        InsertManualAttendanceThread insertManualAttendanceThread = new InsertManualAttendanceThread( context,myModel );
                        insertManualAttendanceThread.run();
                        alertDialog.cancel();
                    }
                } );



                alertDialog.show();


            }
        } );
    }

    public void setData(Cursor c) {

        nameTV.setText( c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.STUDENT_NAME ) ) );
        rollTV.setText( c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.CLASS_ROLL ) ) );
        status = Integer.parseInt( c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.STATUS ) ) );

        name = c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.STUDENT_NAME ) );
        roll = c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.CLASS_ROLL ) );
        studentID = c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.STUDENT_ID ) );
        presentDate = c.getString( c.getColumnIndex( DateWiseStudentAttendanceTableItems.DATE ) );



        if (status == 0) {
            statusTV.setText( "Absent" );
            GradientDrawable bgShape = (GradientDrawable) statusTV.getBackground();
            bgShape.setColor( context.getResources().getColor( R.color.colorAbsent ) );
        } else if (status == 1) {
            statusTV.setText( "Present" );
            GradientDrawable bgShape = (GradientDrawable) statusTV.getBackground();
            bgShape.setColor( context.getResources().getColor( R.color.colorPresent ) );
        } else if (status == 2) {
            statusTV.setText( "Late" );
            GradientDrawable bgShape = (GradientDrawable) statusTV.getBackground();
            bgShape.setColor( context.getResources().getColor( R.color.colorLate ) );
        }


    }

}
