package reverb.smartstudy.teacher.Activity.homeWork.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdjahirulislam.youtubestyletabs.R;
import com.gun0912.tedpicker.ImagePickerActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reverb.smartstudy.teacher.Activity.PresentationActivity;
import reverb.smartstudy.teacher.contentprovider.RequestProvider;
import reverb.smartstudy.teacher.database.CourseTableItems;
import reverb.smartstudy.teacher.interfaces.ConnectionApi;
import reverb.smartstudy.teacher.model.CourseResponseModel;
import reverb.smartstudy.teacher.model.UserRequest;
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.staticclasses.Functions;

public class InsertHomeWorkActivity extends AppCompatActivity {

    private static final String TAG = InsertHomeWorkActivity.class.getSimpleName();

    private ConnectionApi connectionApi;

    private EditText homeWorkTitleET;
    private EditText homeWorkTaskET;
    private Button chooseFileBTN;
    private Button deadLineBTN;
    private Button homeWorkInsertBTN;

    private String homeWorkTitle;
    private String homeWorkTask;
    private String homeWorkFilePath;
    private String homeWorkdeadLine;

    private Calendar calendar;
    private Long deadLineDateMillSecond;
    private  final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MMM/yyyy");
    private boolean samsung;

    private int PICK_FILE_REQUEST_CODE = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insert_home_work );

        homeWorkTitleET = (EditText) findViewById( R.id.homeWorkTitleET );
        homeWorkTaskET = (EditText) findViewById( R.id.homeWorkTaskET );
        chooseFileBTN = (Button) findViewById( R.id.chooseFileBTN );
        deadLineBTN = (Button) findViewById( R.id.deadLineBTN );
        homeWorkInsertBTN = (Button) findViewById( R.id.homeWorkInsertBTN );

        connectionApi =  Functions.getRetrofit().create(ConnectionApi.class);

        calendar = Calendar.getInstance();

        final Calendar setCalender = Calendar.getInstance();
        final int year = setCalender.get( Calendar.YEAR);
        final int month = setCalender.get(Calendar.MONTH);
        final int date = setCalender.get(Calendar.DAY_OF_MONTH);


        deadLineBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(InsertHomeWorkActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.YEAR,year);
//                        String dateString = sdf.format(calendar.getTimeInMillis());
                        deadLineDateMillSecond =calendar.getTimeInMillis();
                        deadLineBTN.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                        Toast.makeText(getApplicationContext(),String.valueOf(calendar.getTime()),Toast.LENGTH_LONG).show();
                        Log.d("Month",String.valueOf(calendar.getTime()));
                    }
                },year,month,date);
//                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.getDatePicker().setMinDate(setCalender.getTimeInMillis());
                datePickerDialog.show();

            }
        });


        samsung = Functions.isSamsung() ;
        Log.d( TAG,String.valueOf( Functions.isSamsung() ) );


    }

    public void chooseFile(View view) {

        if (samsung){

            Intent intent1 = new Intent("com.sec.android.app.myfiles.PICK_DATA");
            intent1.putExtra("CONTENT_TYPE", "application/pdf");
            intent1.addCategory(Intent.CATEGORY_DEFAULT);
            startActivityForResult(intent1, PICK_FILE_REQUEST_CODE);

        }else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        }

    }

    public void addHomeWork(View view) {



        homeWorkTitle = homeWorkTitleET.getText().toString().trim();
        homeWorkTask = homeWorkTaskET.getText().toString().trim();
        homeWorkdeadLine = deadLineBTN.getText().toString().trim();
        if (homeWorkTitle.isEmpty()){
            homeWorkTitleET.setError( "Required Field" );
        }else if (homeWorkdeadLine.isEmpty()){
            Toast.makeText( this, "Please Select Dead Line Date", Toast.LENGTH_SHORT ).show();
        }else {

//                Log.d( TAG, "onCreate 5--->fetchCourses--->  tagCounter : " +tagCounter++);

//        ConnectionApi service = retrofit.create(ConnectionApi.class);
            UserRequest userRequest = new UserRequest();
            userRequest.setUsername( SharedPref.getInstance(getApplicationContext()).getUsername());
            userRequest.setPassword(SharedPref.getInstance(getApplicationContext()).getPassword());

            final Call<CourseResponseModel> coursesResponseCall = connectionApi.getCourse( userRequest );
            coursesResponseCall.enqueue(new Callback<CourseResponseModel>() {
                @Override
                public void onResponse(Call<CourseResponseModel> call, Response<CourseResponseModel> response) {
                    int statusCode=response.code();
                    if (statusCode == 200){
                        CourseResponseModel body = response.body();
                        boolean error = body.getError();
                        if (!error){
                            List<CourseResponseModel.Datum> courses = body.getData();
                            Log.d(TAG, String.valueOf(courses.size()));
                            Uri a= RequestProvider.urlCourseTable();
                            int count=getContentResolver().delete(RequestProvider.urlCourseTable(), "1",null);
                            Log.d( TAG,"count 187 line ---> "+count+" \n URI----> "+a );
                            for (int i = 0; i < courses.size(); i++) {
                                ContentValues cv = new ContentValues();

                                cv.put(CourseTableItems.CREATED_AT,(Functions.convertTimeStamp(courses.get( i ).getCreatedAt())));
                                cv.put(CourseTableItems.UPDATED_AT,(courses.get(i).getUpdatedAt()));
                                cv.put(CourseTableItems.NAME, (courses.get(i).getName()));
                                cv.put(CourseTableItems.CODE_NAME, (courses.get(i).getCodename()));
                                cv.put(CourseTableItems.CLASS, (courses.get(i).getClass_()));
                                getContentResolver().insert(RequestProvider.urlForCourseItems(0), cv);
                            }

//                                getSupportLoaderManager().restartLoader(0, null, InsertHomeWorkActivity.this);
                        }else {
                            Log.d( TAG, "Error true; Msg: "+body.getError() );
                        }


                    }else {
                        Log.d(TAG, "Status code is no 200; \n status code is : "+statusCode );
                    }


                    //SharedPref.getInstance(getApplicationContext()).setUsername(user.getUsername());
                    // Log.d("LoginActivity","On Response: "+statusCode);
                    // Log.d("LoginActivity","Load from SharedPref, username: "+SharedPref.getInstance(getApplicationContext()).getUsername());

                    //CustomSqliteOpenHelper mSqliteOpenHelper = new CustomSqliteOpenHelper(c1);
                    //      CustomSqliteOpenHelper.createTable();

                    // mAdapter.notify();
                    //mAdapter.notifyDataSetChanged();

                    //mAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<CourseResponseModel> call, Throwable t) {

//                        Log.d( TAG, "onCreate 7--->onFailure--->  tagCounter : " +tagCounter++);
                    Log.d("NewsActivity","On Response: Failed");
//                getSupportLoaderManager().restartLoader(0, null, CourseListActivity.this);

                }
            });



        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
         if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {


            Uri uri = data.getData();
            homeWorkFilePath = getPathFromURI( InsertHomeWorkActivity.this,uri );
             Log.d( TAG, "onActivityResult: homeWorkFilePath"+ homeWorkFilePath +"\nUri : "+String.valueOf( uri ));

        }
    }



    public static String getPathFromURI(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
