package reverb.smartstudy.teacher.database;

/**
 * Created by mdjahirulislam on 15/01/18.
 */

public class DateWiseStudentAttendanceTableItems {

    public static final String DATE_WISE_ATTENDANCE_TABLE_NAME = "date_wise_attendance_table";


    public static final String _ID = "_id";
    public static final String COURSE_ID = "course_id";
    public static final String SECTION_ID = "section_id";
    public static final String SECTION_NAME = "section_name";
    public static final String STUDENT_ID = "user_id";
    public static final String DATE = "date";
    public static final String CLASS_ROLL = "roll";
    public static final String STUDENT_NAME = "user_name";
    public static final String IMAGE_PATH = "image_path";
    public static final String STATUS = "status";


    public static final String CREATE_TABLE =
            " CREATE TABLE " + DATE_WISE_ATTENDANCE_TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COURSE_ID + " TEXT, " +
                    SECTION_ID + " TEXT, " +
                    SECTION_NAME + " TEXT, " +
                    STUDENT_ID + " TEXT, " +
                    DATE + " TEXT, " +
                    CLASS_ROLL + " TEXT, " +
                    STUDENT_NAME + " TEXT, " +
                    IMAGE_PATH + " TEXT, " +
                    STATUS+" TEXT );";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DATE_WISE_ATTENDANCE_TABLE_NAME;

    public static String[] Columns = new String[]{_ID, COURSE_ID,SECTION_ID,SECTION_NAME, STUDENT_ID,DATE, CLASS_ROLL,STUDENT_NAME,
            IMAGE_PATH,STATUS};

}
