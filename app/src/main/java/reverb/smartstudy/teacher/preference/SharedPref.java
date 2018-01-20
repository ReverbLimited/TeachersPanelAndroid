package reverb.smartstudy.teacher.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPref {
    private static SharedPref sharedPref = new SharedPref();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static final String PLACE_OBJ = "place_obj";
    private static final String USERNAME_ = "username";
    private static final String PASSWORD_ = "password";
    private static final String ROLE_ = "role";
    private static final String NAME_="name";
    private static final String USERID_="userid";
    private static final String STUDENTUSERID_ ="studentuserid";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String LAST_USAGE = "lastusage";
    private static final String LOGGED_IN= "loggedin";


    private static final String COURSE_NAME= "course_name";
    private static final String COURSE_ID= "course_id";



    private SharedPref() {} //prevent creating multiple instances by making the constructor private

    //The context passed into the getInstance should be application level context.
    public static SharedPref getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharedPref;
    }

  //Project implementations
    //Username
    public void setUsername(String username) {
      editor.putString(USERNAME_, username);
      editor.commit();
    }

    public String getUsername() {

      //  return sharedPreferences.getString(USERNAME_, "");
      return "teacher"; //keep it like this until login panel will be implemented
    }

    public void clearPreference() {
        editor.clear();
        editor.commit();
    }

    //Password
    public void setPassword(String password) {
        editor.putString(PASSWORD_, password);
        editor.commit();
    }

    public String getPassword() {

        //return sharedPreferences.getString(PASSWORD_, "");
        return "123456"; //keep it like this until login panel will be implemented
    }


    public void setCourseName(String courseName) {
        editor.putString(COURSE_NAME, courseName);
        editor.commit();
    }

    public String getCourseName() {
        return sharedPreferences.getString(COURSE_NAME, "No Course Name Found");
    }

    public void setCourseID(String courseID) {
        Log.d( "pre", "setCourseID: "+courseID );
        editor.putString(COURSE_ID, courseID);
        editor.commit();
    }

    public String getCourseId() {
        Log.d( "pre", "setCourseID: "+sharedPreferences.getString(COURSE_ID, "null") );
        return sharedPreferences.getString(COURSE_ID, "null");
    }



    //Role
    public void setRole(String role) {
        editor.putString(ROLE_, role);
        editor.commit();
    }

    public String getRole() {
        return sharedPreferences.getString(ROLE_, "");
    }

    //Name
    public void setName(String name) {
        editor.putString(NAME_, name);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(NAME_, "");
    }

    //USERID
    public void setUserid(String userid) {
        editor.putString(USERID_, userid);
        editor.commit();
    }

    public String getUserid() {
        return sharedPreferences.getString(USERID_, "");
    }

    //STUDENTID
    public void setStudentUserid(String studentuserid) {
        editor.putString(STUDENTUSERID_, studentuserid);
        editor.commit();
    }

    public String getStudentUserid() {
        return sharedPreferences.getString(STUDENTUSERID_, "");
    }

    //Welcome INTRO
    public void setIsFirstTimeLaunch(String isFirstTimeLaunch) {
        editor.putString(IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch);
        editor.commit();
    }

    public String getFirstTimeLaunch() {
        return sharedPreferences.getString(IS_FIRST_TIME_LAUNCH,"");
    }

    public void setLoggedIn(String loggedIn) {
        editor.putString(LOGGED_IN, loggedIn);
        editor.commit();
    }


    public String getLoggedIn() {
        return sharedPreferences.getString(LOGGED_IN,"false");
    }

    //Last Usage
    public void setLastUssage(long lastUsage) {
        editor.putLong(LAST_USAGE, lastUsage);
        editor.commit();
    }

    public long getLastUsage() {
        return sharedPreferences.getLong(LAST_USAGE,1506835673759L);
    }


    //###Sample Implementations

    public void savePlaceObj(String placeObjStr) {
        editor.putString(PLACE_OBJ, placeObjStr);
        editor.commit();
    }



    public String getPlaceObj() {
        return sharedPreferences.getString(PLACE_OBJ, "");
    }

    public void removePlaceObj() {
        editor.remove(PLACE_OBJ);
        editor.commit();
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }

}