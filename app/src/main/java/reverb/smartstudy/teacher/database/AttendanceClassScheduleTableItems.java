package reverb.smartstudy.teacher.database;

/**
 * Created by mdjahirulislam on 13/01/18.
 */

public class AttendanceClassScheduleTableItems {

    public static final String ATTENDANCE_CLASS_SCHEDULE_TABLE_NAME = "attendance_class_schedule_table";


    public static final String _ID = "_id";
    public static final String CLASS_SCHEDULE_ID = "class_schedule_id";
    public static final String COURSE_ID = "course_id";
    public static final String CLASS_SCHEDULE_DATE = "date";
    public static final String TOTAL_PRESENT_STUDENT_NUMBER = "present_student_number";
    public static final String CLASS_DAY = "class_day";
    public static final String CLASS_TIME = "class_time";


    public static final String CREATE_TABLE =
            " CREATE TABLE " + ATTENDANCE_CLASS_SCHEDULE_TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    CLASS_SCHEDULE_ID + " TEXT, " +
                    COURSE_ID + " TEXT, " +
                    CLASS_SCHEDULE_DATE + " TEXT, " +
                    TOTAL_PRESENT_STUDENT_NUMBER + " TEXT, " +
                    CLASS_DAY + " TEXT, " +
                    CLASS_TIME+" TEXT );";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + ATTENDANCE_CLASS_SCHEDULE_TABLE_NAME;
    public static String[] Columns = new String[]{_ID, CLASS_SCHEDULE_ID, COURSE_ID,CLASS_SCHEDULE_DATE,
            TOTAL_PRESENT_STUDENT_NUMBER,CLASS_DAY, CLASS_TIME};
}
