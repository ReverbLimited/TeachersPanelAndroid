package reverb.smartstudy.teacher.responseModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 08/01/18.
 */

public class CourseScheduleWithAttendanceResponseModel {

    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = null;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("msg")
    @Expose
    private String msg;

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
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

    public static class Schedule {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("toatPresentStudentNumber")
        @Expose
        private Integer toatPresentStudentNumber;
        @SerializedName("classDay")
        @Expose
        private String classDay;
        @SerializedName("classTime")
        @Expose
        private String classTime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getToatPresentStudentNumber() {
            return toatPresentStudentNumber;
        }

        public void setToatPresentStudentNumber(Integer toatPresentStudentNumber) {
            this.toatPresentStudentNumber = toatPresentStudentNumber;
        }

        public String getClassDay() {
            return classDay;
        }

        public void setClassDay(String classDay) {
            this.classDay = classDay;
        }

        public String getClassTime() {
            return classTime;
        }

        public void setClassTime(String classTime) {
            this.classTime = classTime;
        }

    }
}
