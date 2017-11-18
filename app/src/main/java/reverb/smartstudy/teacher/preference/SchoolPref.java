package reverb.smartstudy.teacher.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SchoolPref {
    private static SchoolPref schoolPref = new SchoolPref();
    private static SharedPreferences schoolPreferences;
    private static SharedPreferences.Editor schooleditor;

    private static final String PLACE_OBJ = "place_obj";

    public static String getSchoolname() {
        return schoolPreferences.getString(SCHOOLNAME_,"");
    }

    public void  setSchoolname(String schoolname) {
        schooleditor.putString(SCHOOLNAME_, schoolname);
        schooleditor.commit();
    }

    public static String getSchoolbaseurl() {
        String a=schoolPreferences.getString(SCHOOLBASEURL_,"");
       return schoolPreferences.getString(SCHOOLBASEURL_,"");

    }

    public void setSchoolbaseurl(String schoolbaseurl) {
        schooleditor.putString(SCHOOLBASEURL_, schoolbaseurl);
        schooleditor.commit();
    }

    public static String getWebAppurl() {
        String a=schoolPreferences.getString(SCHOOLWEBAPPURL_,"");
        return schoolPreferences.getString(SCHOOLWEBAPPURL_,"");

    }

    public void setWebAppurl(String schoolwebappurl) {
        schooleditor.putString(SCHOOLWEBAPPURL_, schoolwebappurl);
        schooleditor.commit();
    }

    private static final String TOKEN= "token";
    public String getToken() {
        return schoolPreferences.getString(TOKEN,"");
    }

    public void setToken(String token) {
        schooleditor.putString(TOKEN, token);
        schooleditor.commit();
    }

    private static final String TOKENCHANGE= "tokenchange";
    public String getTokenChange() {
        return schoolPreferences.getString(TOKENCHANGE,"false");
    }

    public void setTokenchange(String tokenchange) {
        schooleditor.putString(TOKENCHANGE, tokenchange);
        schooleditor.commit();
    }

    private static final String SCHOOLNAME_ = "schoolname";
    private static final String SCHOOLBASEURL_ = "schoolbaseurl";
    private static final String SCHOOLWEBAPPURL_ = "schoolwebappurl";
    private SchoolPref() {} //prevent creating multiple instances by making the constructor private

    //The context passed into the getInstance should be application level context.
    public static SchoolPref getInstance(Context context) {
        if (schoolPreferences == null) {
            schoolPreferences = context.getSharedPreferences("schoolPref", Activity.MODE_PRIVATE);
            schooleditor = schoolPreferences.edit();
        }
        return schoolPref;
    }








    public void removePlaceObj() {
        schooleditor.remove(PLACE_OBJ);
        schooleditor.commit();
    }



    public void clearAll() {
        schooleditor.clear();
        schooleditor.commit();
    }

}