package reverb.smartstudy.teacher.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import reverb.smartstudy.teacher.database.CourseTableItems;
import reverb.smartstudy.teacher.database.CustomSqliteOpenHelper;
import reverb.smartstudy.teacher.database.NewsTableItems;

public class RequestProvider extends ContentProvider {
    private static final String TAG = "RequestProvider";

    private SQLiteOpenHelper mSqliteOpenHelper;
    private static final UriMatcher sUriMatcher;

    public static final String AUTHORITY = "reverb.SmartStudy.db";

    private static final int TABLE_ITEMS = 0;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, NewsTableItems.NEWS_TABLE_NAME + "/offset/" + "#", 0);
        sUriMatcher.addURI(AUTHORITY, CourseTableItems.COURSE_TABLE_NAME + "/offset/" + "#", 1);
        sUriMatcher.addURI(AUTHORITY, "events" + "/offset/" + "#", 2);
    }

    public static Uri urlForItems(int limit) {
        Uri uri= Uri.parse("content://" + AUTHORITY + "/" + NewsTableItems.NEWS_TABLE_NAME + "/offset/" + limit);
        return uri;
    }



    public static Uri urlNewsTable() {
        return Uri.parse("content://" + AUTHORITY + "/" + NewsTableItems.NEWS_TABLE_NAME);
    }

    //          jahir edit          //

    public static Uri urlForCourseItems(int limit) {
        Uri uri= Uri.parse("content://" + AUTHORITY + "/" + CourseTableItems.COURSE_TABLE_NAME + "/offset/" + limit);
        return uri;
    }

    public static Uri urlCourseTable() {
        return Uri.parse("content://" + AUTHORITY + "/" + CourseTableItems.COURSE_TABLE_NAME);
    }

    //          jahir edit End         //

    public static Uri urlForNews(int limit) {
        return Uri.parse("content://" + AUTHORITY + "/" + "news" + "/offset/" + limit);
    }

    @Override
    public boolean onCreate() {
        mSqliteOpenHelper = new CustomSqliteOpenHelper(getContext());
        return true;
    }

    @Override
    synchronized public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mSqliteOpenHelper.getReadableDatabase();
        SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
        Cursor c = null;
        String offset;

        int i=sUriMatcher.match(uri);

        switch (sUriMatcher.match(uri)) {
            case 0: {
                sqb.setTables(NewsTableItems.NEWS_TABLE_NAME);
                offset = uri.getLastPathSegment();
                break;
            }
            case 1: {
                sqb.setTables(CourseTableItems.COURSE_TABLE_NAME);
                offset = uri.getLastPathSegment();
                break;
            }

            default:
                throw new IllegalArgumentException("uri not recognized!");
        }

        int intOffset = Integer.parseInt(offset);

        String limitArg = intOffset + ", " + 30;
        Log.d(TAG, "query: " + limitArg);
        c = sqb.query(db, projection, selection, selectionArgs, null, null, sortOrder, limitArg);

        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return AUTHORITY + ".item";
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        String table = "";
        int i=sUriMatcher.match(uri);
        switch (sUriMatcher.match(uri)) {
            case 0: {
                table = NewsTableItems.NEWS_TABLE_NAME;
                break;
            }case 1: {
                table = CourseTableItems.COURSE_TABLE_NAME;
                break;
            }
            case 2: {
                table = "events";
                break;
            }
        }

        long result = mSqliteOpenHelper.getWritableDatabase().insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        if (result == -1) {
            throw new SQLException("insert with conflict!");
        }

        Uri retUri = ContentUris.withAppendedId(uri, result);
        return retUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        String table = NewsTableItems.NEWS_TABLE_NAME;
        Log.d( TAG, "Delete uri is : "+uri );
        if(uri.equals( urlNewsTable() )){
            int result = mSqliteOpenHelper.getWritableDatabase().delete(table, selection,selectionArgs);
            Log.d( TAG, "CAll not null delete" );
            return result;
        }else if (uri.equals( urlCourseTable() )){
            int result = mSqliteOpenHelper.getWritableDatabase().delete(CourseTableItems.COURSE_TABLE_NAME, selection,selectionArgs);
            Log.d( TAG, "CAll 2 delete" );
            return result;
        }



      //  int result = mSqliteOpenHelper.getWritableDatabase().delete(table, selection,selectionArgs);
        return -1;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return -1;
    }
}