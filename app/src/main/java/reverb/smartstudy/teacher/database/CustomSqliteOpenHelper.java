package reverb.smartstudy.teacher.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomSqliteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "CustomSqliteOpenHelper";
    public static SQLiteDatabase db;

    public CustomSqliteOpenHelper(Context context) {
        super(context, "SmartStudy.db", null, 2);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewsTableItems.CREATE_TABLE);
        db.execSQL( CourseTableItems.CREATE_TABLE );
       this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( NewsTableItems.DROP_TABLE );
        db.execSQL( CourseTableItems.DROP_TABLE );
        onCreate(db);
    }

    public static SQLiteDatabase createTable(){
       db.execSQL(NewsTableItems.CREATE_TABLE);
        return db;
    }

    public static SQLiteDatabase createEventTable(){
        db.execSQL(" CREATE TABLE " + "events" +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " created_at TEXT, " +
                " updated_at TEXT, " +
                " name TEXT, " +
                " wholedayevent INTEGER, " +
                " start TEXT, " +
                " end TEXT, " +
                " eventdate TEXT, " +
                " description TEXT, " +
                " image TEXT);");
        return db;
    }

}