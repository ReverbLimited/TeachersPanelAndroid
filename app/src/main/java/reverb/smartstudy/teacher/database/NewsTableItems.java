package reverb.smartstudy.teacher.database;

public class NewsTableItems {

    public static final String _ID = "_id";
    public static final String NEWS_ID = "news_id";
    public static final String TEXT = "text";
    public static final String NEWS_TABLE_NAME = "news";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";

    public static final String CREATE_TABLE =
            " CREATE TABLE " + NEWS_TABLE_NAME +
                    " (_id INTEGER, " +
                    " news_id INTEGER PRIMARY KEY, " +
                    " created_at TEXT, " +
                    " updated_at TEXT, " +
                    " name TEXT, " +
                    " description TEXT, " +
                    " image TEXT);";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NEWS_TABLE_NAME;
    public static String[] Columns = new String[]{_ID,NEWS_ID, NAME,CREATED_AT,DESCRIPTION};
}