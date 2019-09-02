package reverb.smartstudy.teacher.Activity.classTime;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ClassTimeHomeActivity extends AppCompatActivity {

    private TextView timerTV;
    long nowTime = 0;
    long nexrClassTime = 0;

    int nextClassHour = 16;
    int nextClassMinute = 00;
    int nextClassSecond = 00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_class_time_home );
        timerTV = (TextView) findViewById( R.id.timerTV );
        nowTime = Calendar.getInstance().getTimeInMillis();

        Calendar nowCalender = Calendar.getInstance();

        int nowHour = nowCalender.getTime().getHours();
        int nowMinute = nowCalender.getTime().getMinutes();
        int nowSecond = nowCalender.getTime().getSeconds();



//        Calendar classCalender = Calendar.getInstance();
//        classCalender.set( Calendar.HOUR,nextClassHour );
//        classCalender.set( Calendar.MINUTE,nextClassMinute );

//        nexrClassTime = classCalender.getTimeInMillis();
        long counter = nowTime-((nextClassHour*1000*60*60)+(nextClassMinute*60*1000)+(nextClassSecond*1000));
        long counter1 = ((nextClassHour*1000*60*60)+(nextClassMinute*60*1000)+(nextClassSecond*1000))-
                ((nowHour*1000*60*60)+(nowMinute*60*1000)+(nowSecond*1000));

        Log.d( "nowHour",  String.valueOf( counter ) );
        Log.d( "Time--------->",  String.valueOf( counter1 ) );

        //        nexrClassTime =

        new CountDownTimer(counter1, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTV.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerTV.setText("done!");
            }
        }.start();
    }
}
