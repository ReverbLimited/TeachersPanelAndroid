package reverb.smartstudy.teacher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import reverb.smartstudy.teacher.Activity.classTime.ClassTimeHomeActivity;
import reverb.smartstudy.teacher.Activity.homeWork.Activity.CourseListActivity;
import reverb.smartstudy.teacher.Activity.news.NewsActivity;

import com.example.mdjahirulislam.youtubestyletabs.R;

public class FirstFragment extends Fragment {

    private final static String TAG = FirstFragment.class.getSimpleName();

    private LinearLayout attendanceLL;
    private LinearLayout classTimeLL;
    private LinearLayout presentationLL;
    private LinearLayout newsLL;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_first, container, false );
        attendanceLL = (LinearLayout) view.findViewById( R.id.homeFragmentAttendanceLL );

        presentationLL = (LinearLayout) view.findViewById( R.id.homeFragmentPresentationLL );

        newsLL = (LinearLayout) view.findViewById( R.id.homeFragmentNewsLL );

        classTimeLL = (LinearLayout) view.findViewById( R.id.homeFragmentClassTimeLL );


//        classTimeLL.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(),ClassTimeHomeActivity.class));
//            }
//        } );

        classTimeLL.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d( TAG, "onTouch 1: classTimeLL ---> " + event.getAction() );
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        classTimeLL.setAlpha( 0.5f );
                        Log.d( TAG, "onTouch 2: classTimeLL ---> " + MotionEvent.ACTION_DOWN );
                        break;
                    case MotionEvent.ACTION_UP:
                        classTimeLL.setAlpha( 1.0f );
                        Log.d( TAG, "onTouch 3: classTimeLL ---> " + MotionEvent.ACTION_UP );
                        startActivity( new Intent( getContext(), ClassTimeHomeActivity.class ) );
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        classTimeLL.setAlpha( 1.0f );
                        break;
                    case MotionEvent.ACTION_MOVE:
                        classTimeLL.setAlpha( 1.0f );
                        break;
                    default:
                        break;
                }
                return false;
            }
        } );

        attendanceLL.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        attendanceLL.setAlpha( 0.5f );
                        break;
                    case MotionEvent.ACTION_UP:
                        attendanceLL.setAlpha( 1.0f );
//                        startActivity( new Intent( getContext(), AttendanceActivity.class ) );
                        startActivity( new Intent( getContext(), CourseListActivity.class ).putExtra( "from",2 ) );
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        attendanceLL.setAlpha( 1.0f );
                        break;
                    case MotionEvent.ACTION_MOVE:
                        attendanceLL.setAlpha( 1.0f );
                    default:
                        break;

                }
                return false;
            }
        } );


        presentationLL.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        presentationLL.setAlpha( 0.5f );
                        break;
                    case MotionEvent.ACTION_UP:
                        presentationLL.setAlpha( 1.0f );
                        startActivity( new Intent( getContext(), CourseListActivity.class ).putExtra( "from",3 ) );
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        presentationLL.setAlpha( 1.0f );
                        break;
                    case MotionEvent.ACTION_MOVE:
                        presentationLL.setAlpha( 1.0f );
                    default:
                        break;

                }
                return false;
            }
        } );

        newsLL.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        newsLL.setAlpha( 0.5f );
                        break;
                    case MotionEvent.ACTION_UP:
                        newsLL.setAlpha( 1.0f );
                        startActivity( new Intent( getContext(), NewsActivity.class ) );
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        newsLL.setAlpha( 1.0f );
                        break;
                    case MotionEvent.ACTION_MOVE:
                        newsLL.setAlpha( 1.0f );
                    default:
                        break;

                }
                return false;
            }
        } );


        return view;
    }


}
