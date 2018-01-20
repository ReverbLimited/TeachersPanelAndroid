package reverb.smartstudy.teacher.responseModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 9/3/2017.
 */



public class SchoolResponseModel {

    @SerializedName("schoolname")
    @Expose
    public String schoolname;
    @SerializedName("schoolid")
    @Expose
    public String schoolid;
    @SerializedName("baseurl")
    @Expose
    public String baseurl;

    public String getWebappurl() {
        return webappurl;
    }

    @SerializedName("webappurl")
    @Expose
    public String webappurl;
    public String getSchoolname() {
        return schoolname;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public String getBaseurl() {
        return baseurl;
    }







}
