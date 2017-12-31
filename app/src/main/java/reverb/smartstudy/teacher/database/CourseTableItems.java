package reverb.smartstudy.teacher.database;

/**
 * Created by mdjahirulislam on 19/11/17.
 */

public class CourseTableItems {
    public static final String COURSE_TABLE_NAME = "course_table";


    public static final String _ID = "_id";
    public static final String COURSE_ID = "course_id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String NAME = "name";
    public static final String CODE_NAME = "code_name";
    public static final String SESSION_ID = "session_id";
    public static final String START = "start";
    public static final String END = "end";
    public static final String DESCRIPTION = "description";
    public static final String ICON_PATH = "icon_path";
    public static final String CLASS = "class";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + COURSE_TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_ID + " TEXT, " +
                    CREATED_AT + " TEXT, " +
                    UPDATED_AT + " TEXT, " +
                    NAME + " TEXT, " +
                    CODE_NAME + " TEXT, " +
                    SESSION_ID + " TEXT, " +
                    START + " TEXT, " +
                    END + " TEXT, " +
                    DESCRIPTION + " TEXT, " +
                    ICON_PATH + " TEXT, "+
                    CLASS+" TEXT );";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + COURSE_TABLE_NAME;
    public static String[] Columns = new String[]{_ID,COURSE_ID, NAME,CODE_NAME,CLASS};
}
