package reverb.smartstudy.teacher.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import reverb.smartstudy.teacher.Activity.AttendanceActivity;
import reverb.smartstudy.teacher.Activity.PresentationActivity;
import reverb.smartstudy.teacher.Activity.news.NewsActivity;
import com.example.mdjahirulislam.youtubestyletabs.R;

public class FirstFragment extends Fragment {

    private LinearLayout attendanceLL;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_first, container, false);
        attendanceLL = (LinearLayout) view.findViewById(R.id.homeFragmentAttendanceLL);

        presentationLL = (LinearLayout) view.findViewById( R.id.homeFragmentPresentationLL );


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
                        startActivity(new Intent(getContext(),NewsActivity.class));
                        break;

                }
                return false;
            }
        } );







        return view;
    }


}
