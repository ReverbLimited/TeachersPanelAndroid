package reverb.smartstudy.teacher.responseModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mdjahirulislam on 17/01/18.
 */

public class ManualAttendanceInsertResponseModel {


    @SerializedName("totalPresent")
    @Expose
    private Integer totalPresent;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(Integer totalPresent) {
        this.totalPresent = totalPresent;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ManualAttendanceInsertResponseModel{" +
                "totalPresent=" + totalPresent +
                ", error=" + error +
                ", msg='" + msg + '\'' +
                '}';
    }
}

