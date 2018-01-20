package reverb.smartstudy.teacher.responseModelClass;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 9/3/2017.
 */

public class NewsResponseModel {

    @SerializedName("id")
    @Expose
    public String newsid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("created_at")
    @Expose
    public String created_at;
    @SerializedName("updated_at")
    @Expose
    public String updated_at;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("active")
    @Expose
    public String active;
    @SerializedName("target")
    @Expose
    public String target;


    public String getNewsid() {
        return newsid;
    }

    public String getName() {
        return name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getActive() {
        return active;
    }

    public String getTarget() {
        return target;
    }
}
