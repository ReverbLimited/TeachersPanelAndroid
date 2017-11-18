package reverb.smartstudy.teacher.staticclasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP on 9/30/2017.
 */

public class Functions {



    public static String convertTimeStamp(String inputDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = null;
        try {
            date = simpleDateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date == null) {
            return "";
        }

        SimpleDateFormat convetDateFormat = new SimpleDateFormat("dd MMM, yyyy hh:mm a");

        return convetDateFormat.format(date);
    }

    public static String convertDay(String inputDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = simpleDateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date == null) {
            return "";
        }

        SimpleDateFormat convetDateFormat = new SimpleDateFormat("dd MMM, yyyy");

        return convetDateFormat.format(date);
    }

    public static String convertTime(String inputTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

        Date date = null;
        try {
            date = simpleDateFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date == null) {
            return "";
        }

        SimpleDateFormat convetDateFormat = new SimpleDateFormat("hh:mm a");

        return convetDateFormat.format(date);
    }

    public static int timeDifference(long datetime) throws ParseException {
        long differenceInMillis = Calendar.getInstance().getTimeInMillis() - datetime;
        long differenceInHours = (differenceInMillis) / 1000L / 60L / 60L; // Divide by millis/sec, secs/min, mins/hr
        return (int)differenceInHours;
    }
}
