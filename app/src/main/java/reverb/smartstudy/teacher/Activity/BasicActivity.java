package reverb.smartstudy.teacher.Activity;

import com.alamkanak.weekview.WeekViewEvent;
import com.example.mdjahirulislam.youtubestyletabs.R;
import reverb.smartstudy.teacher.apiclient.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A basic example of how to use week view library.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 *
 *
 * {"name":"Event 1","dayOfMonth":3,"startTime":"01:00","endTime":"02:00","color":"#F57F68"}
 */
public class BasicActivity extends CalendarViewActivity {

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.

        WeekViewEvent weekViewEvent;
        int month = 9;


//        weekViewEvent = new WeekViewEvent( 1001, "Test",  )


        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        Event event1 = new Event("Event1", month,1,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,1,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,1,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,1,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,1,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,1,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Event1", month,2,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,2,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,2,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,2,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,2,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,2,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Event1", month,3,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,3,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,3,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,3,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,3,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,3,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,4,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,4,"11:00", "12:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,4,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,4,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,4,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,4,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Absent", month,5,"10:00", "11:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,5,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,5,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,5,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,5,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,5,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Holiday", month,6,"10:00", "16:00","#C0C0C0" );
        events.add(event1.toWeekViewEvent());



        event1 = new Event("Present", month,7,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,7,"11:00", "12:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,7,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,7,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,7,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,7,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,8,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,8,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,8,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,8,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,8,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,8,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,9,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,9,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,9,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,9,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,9,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,9,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Event1", month,10,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,10,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,10,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,10,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,10,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,10,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Event1", month,11,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,11,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,11,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,11,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,11,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,11,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,12,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,12,"11:00", "12:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,12,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,12,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,12,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,12,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());



        event1 = new Event("Holiday", month,13,"10:00", "16:00","#C0C0C0" );
        events.add(event1.toWeekViewEvent());



        event1 = new Event("Absent", month,14,"10:00", "11:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,14,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,14,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,14,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,14,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,14,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());



        event1 = new Event("Present", month,15,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,15,"11:00", "12:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,15,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,15,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,15,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,15,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,16,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,16,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,16,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,16,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,16,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,16,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());

        event1 = new Event("Present", month,17,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,17,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,17,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,17,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,17,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,17,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,18,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,18,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,18,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,18,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,18,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,18,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());




        event1 = new Event("Event1", month,19,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,19,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,19,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,19,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,19,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,19,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());

        event1 = new Event("Holiday", month,20,"10:00", "16:00","#C0C0C0" );
        events.add(event1.toWeekViewEvent());

        event1 = new Event("Present", month,21,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,21,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,21,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,21,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,21,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,21,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Absent", month,22,"10:00", "11:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,22,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,22,"12:00", "13:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,22,"13:00", "14:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,22,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,22,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Event1", month,23,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,23,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,23,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,23,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,23,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,23,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());

        event1 = new Event("Present", month,24,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,24,"11:00", "12:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,24,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,24,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,24,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,24,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());

        event1 = new Event("Present", month,25,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,25,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,25,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,25,"13:00", "14:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,25,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,25,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());




        event1 = new Event("Event1", month,26,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,26,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,26,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,26,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,26,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,26,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Holiday", month,27,"10:00", "16:00","#C0C0C0" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,28,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,28,"11:00", "12:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,28,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,28,"13:00", "14:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,28,"14:00", "15:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,28,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Present", month,29,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,29,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,29,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,29,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,29,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,29,"15:00", "16:00","#008000" );
        events.add(event1.toWeekViewEvent());


        event1 = new Event("Event1", month,30,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,30,"11:00", "12:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,30,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,30,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,30,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,30,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());

        event1 = new Event("Event1", month,31,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,31,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,31,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month,31,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,31,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month,31,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());

        event1 = new Event("Present", month+1,1,"10:00", "11:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month+1,1,"11:00", "12:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month+1,1,"12:00", "13:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Present", month+1,1,"13:00", "14:00","#008000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month+1,1,"14:00", "15:00","#FF0000" );
        events.add(event1.toWeekViewEvent());
        event1 = new Event("Absent", month+1,1,"15:00", "16:00","#FF0000" );
        events.add(event1.toWeekViewEvent());






        Calendar startTime = Calendar.getInstance();
        startTime.set( Calendar.MONTH, 10 );
        startTime.set( Calendar.DAY_OF_MONTH, 29 );
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
//        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, "Calendar \nEvent", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 4);
//        endTime.set(Calendar.MINUTE, 30);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 4);
//        startTime.set(Calendar.MINUTE, 20);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 5);
//        endTime.set(Calendar.MINUTE, 0);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 30);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 2);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.HOUR_OF_DAY, 5);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        startTime.add(Calendar.DATE, 1);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 15);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, 1);
//        startTime.set(Calendar.HOUR_OF_DAY, 3);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
//        startTime.set(Calendar.HOUR_OF_DAY, 15);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 3);
//        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);

        return events;
    }

}
