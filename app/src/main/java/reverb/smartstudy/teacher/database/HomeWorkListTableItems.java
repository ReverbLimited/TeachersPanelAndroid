package reverb.smartstudy.teacher.database;

/**
 * Created by mdjahirulislam on 10/12/17.
 */

public class HomeWorkListTableItems {

    public static final String HW_TABLE_NAME = "home_work_table";


    public static final String _ID = "_id";
    public static final String HW_ID = "hw_id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String HW_TITLE = "hw_title";
    public static final String HW_TASK = "hw_task";
    public static final String HW_FILE_PATH = "hw_file_path";
    public static final String HW_DEADLINE = "hw_dead_line";
    public static final String HW_COURSE_ID = "hw_course_id";
    public static final String HW_COURSE_NAME = "hw_course_name";
    public static final String HW_TEACHER_ID = "hw_teacher_name";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + HW_TABLE_NAME + " (" +
                    _ID + " INTEGER , " +
                    HW_ID + " TEXT PRIMARY KEY, " +
                    CREATED_AT + " TEXT, " +
                    UPDATED_AT + " TEXT, " +
                    HW_TITLE + " TEXT, " +
                    HW_TASK + " TEXT, " +
                    HW_FILE_PATH + " TEXT, " +
                    HW_DEADLINE + " TEXT, " +
                    HW_COURSE_ID + " TEXT, " +
                    HW_COURSE_NAME + " TEXT, " +
                    HW_TEACHER_ID + " TEXT);";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + HW_TABLE_NAME;
    public static String[] Columns = new String[]{_ID,HW_ID, HW_TITLE, HW_TASK, HW_FILE_PATH, HW_DEADLINE, CREATED_AT};
}
