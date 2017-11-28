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

import reverb.smartstudy.teacher.Activity.AttendanceActivity;
import reverb.smartstudy.teacher.Activity.PresentationActivity;
import reverb.smartstudy.teacher.Activity.classTime.ClassTimeHomeActivity;
import reverb.smartstudy.teacher.Activity.homeWork.Activity.CourseListActivity;
import reverb.smartstudy.teacher.Activity.news.NewsActivity;
import reverb.smartstudy.teacher.model.News;

import com.example.mdjahirulislam.youtubestyletabs.R;

public class FirstFragment extends Fragment {

    private LinearLayout attendanceLL;
    private LinearLayout classTimeLL;
    private LinearLayout presentationLL;
    private LinearLayout newsLL;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_first, container, false);
        attendanceLL = (LinearLayout) view.findViewById(R.id.homeFragmentAttendanceLL);

        presentationLL = (LinearLayout) view.findViewById( R.id.homeFragmentPresentationLL );

        newsLL =  (LinearLayout) view.findViewById( R.id.homeFragmentNewsLL );

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
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        classTimeLL.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_UP:
                        classTimeLL.setAlpha(1.0f);
                        startActivity(new Intent(getContext(),ClassTimeHomeActivity.class));
                        break;
                }
                return true;
            }
        } );

        attendanceLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        attendanceLL.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_UP:
                        attendanceLL.setAlpha(1.0f);
                        startActivity(new Intent(getContext(),AttendanceActivity.class));
                        break;

                }
                return false;
            }
        });


        presentationLL.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        presentationLL.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_UP:
                        presentationLL.setAlpha(1.0f);
                        startActivity(new Intent(getContext(),CourseListActivity.class));
                        break;

                }
                return false;
            }
        } );

        newsLL.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        newsLL.setAlpha(0.5f);
                        break;
                    case MotionEvent.ACTION_UP:
                        newsLL.setAlpha(1.0f);
                        startActivity(new Intent(getContext(),NewsActivity.class));
                        break;

                }
                return true;
            }
        } );







        return view;
    }


}
