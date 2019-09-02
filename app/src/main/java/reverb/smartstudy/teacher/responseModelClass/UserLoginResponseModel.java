package reverb.smartstudy.teacher.responseModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 9/3/2017.
 */

public class UserLoginResponseModel {
    @SerializedName("id")
    @Expose
    public String userid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("username")
    @Expose
    public String username;

    public String getName() {
        return name;
    }


    public String getUserid() {
        return userid;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }



}
