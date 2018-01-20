package reverb.smartstudy.teacher.Activity.attendance.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.homeWork.Activity.CourseListActivity;
import reverb.smartstudy.teacher.Adapter.AttendanceViewPagerAdapter;
import reverb.smartstudy.teacher.Fragment.AttendanceClassScheduleFragment;
import reverb.smartstudy.teacher.Fragment.AttendanceStudentListFragment;

public class CourseOperationActivity extends AppCompatActivity {
    private static final String TAG = CourseOperationActivity.class.getSimpleName();

    private TabLayout attendanceTabLayout;
    private ViewPager attendanceViewPager;
    private Toolbar attendanceToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_course_operation );
        attendanceToolBar = (Toolbar) findViewById( R.id.attendanceToolBar );
        setSupportActionBar( attendanceToolBar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        attendanceTabLayout = (TabLayout) findViewById( R.id.attendanceTabs );
        attendanceViewPager = (ViewPager) findViewById( R.id.attendanceViewPager );
        setDataToViewPager();
        attendanceTabLayout.setupWithViewPager( attendanceViewPager );
    }

    private void setDataToViewPager() {
//        AttendanceStudentListFragment attendanceStudentListFragment = new AttendanceStudentListFragment();
//        AttendanceClassScheduleFragment classScheduleFragment = new AttendanceClassScheduleFragment();

        AttendanceViewPagerAdapter adapter = new AttendanceViewPagerAdapter( getSupportFragmentManager() );

        adapter.addFragment( new AttendanceStudentListFragment(), "Students" );
        adapter.addFragment( new AttendanceClassScheduleFragment() , "Date" );
        attendanceViewPager.setAdapter( adapter );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent( this, CourseListActivity.class ).putExtra( "from", 2 );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity( intent );

            finish();
        }
        // app icon in action bar clicked; goto parent activity.
        this.finish();
        return super.onOptionsItemSelected( item );

    }
}