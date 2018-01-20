package reverb.smartstudy.teacher.Activity.attendance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mdjahirulislam.youtubestyletabs.R;

public class ManualAttendanceInsertListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_manual_attendance_insert_list );
    }

    public void submitAllAttendance(View view) {
        Toast.makeText( this, "Click on Submit Button", Toast.LENGTH_SHORT ).show();
    }
}
