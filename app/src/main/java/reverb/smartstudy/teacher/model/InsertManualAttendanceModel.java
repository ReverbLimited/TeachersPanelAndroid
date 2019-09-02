package reverb.smartstudy.teacher.model;

import java.util.List;

/**
 * Created by mdjahirulislam on 30/12/17.  it is not need just now
 */

public class InsertManualAttendanceModel {

    private String username;
    private String password;

    private String courseID;
    private String presentDate;
    private String presentTime;
    private String delay;
    private List<Attendance> attendance = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(String presentDate) {
        this.presentDate = presentDate;
    }

    public String getPresentTime() {
        return presentTime;
    }

    public void setPresentTime(String presentTime) {
        this.presentTime = presentTime;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    public static class Attendance {


        private String userid;

        private String present;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPresent() {
            return present;
        }

        public void setPresent(String present) {
            this.present = present;
        }

        @Override
        public String toString() {
            return "Attendance{" +
                    "userid='" + userid + '\n' +
                    ", present='" + present + '\n' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "InsertManualAttendanceModel{" +
                "username='" + username + '\n' +
                ", password='" + password + '\n' +
                ", courseID='" + courseID + '\n' +
                ", presentDate='" + presentDate + '\n' +
                ", presentTime='" + presentTime + '\n' +
                ", delay='" + delay + '\n' +
                ", attendance=" + attendance.toString() +
                '}';
    }
}
