package reverb.smartstudy.teacher.database;

/**
 * Created by mdjahirulislam on 06/01/18.
 */

public class CourseStudentAttendanceTableItems {


    public static final String ATTENDANCE_TABLE_NAME = "attendance_table";


    public static final String _ID = "_id";
    public static final String COURSE_ID = "course_id";
    public static final String SECTION_ID = "section_id";
    public static final String SECTION_NAME = "section_name";
    public static final String STUDENT_ID = "student_id";
    public static final String STUDENT_NAME = "student_name";
    public static final String STUDENT_ROLL = "student_roll";
    public static final String STUDENT_IMAGE_PATH = "image_path";
    public static final String LAST_PRESENT_DATE = "last_present_date";
    public static final String TOTAL_PRESENT = "total_present";


    public static final String CREATE_TABLE =
            " CREATE TABLE " + ATTENDANCE_TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COURSE_ID + " TEXT, " +
                    SECTION_ID + " TEXT, " +
                    SECTION_NAME + " TEXT, " +
                    STUDENT_ID + " TEXT, " +
                    STUDENT_NAME + " TEXT, " +
                    STUDENT_ROLL + " TEXT, " +
                    STUDENT_IMAGE_PATH + " TEXT, " +
                    LAST_PRESENT_DATE + " TEXT, " +
                    TOTAL_PRESENT+" TEXT );";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + ATTENDANCE_TABLE_NAME;
    public static String[] Columns = new String[]{_ID,COURSE_ID,SECTION_ID,SECTION_NAME,STUDENT_ID,
            STUDENT_NAME,STUDENT_ROLL,STUDENT_IMAGE_PATH,LAST_PRESENT_DATE,TOTAL_PRESENT};
//    public static String[] Columns = new String[]{_ID,SECTION_NAME,SECTION_ID};

}
