package reverb.smartstudy.teacher.model;

/**
 * Created by HP on 9/4/2017.
 */

public class UserRequest {

    private  String activationcode;
    private String username;
    private String password;

    private String courseID;
    private String homeworkID;
    private String date;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getHomeworkID() {
        return homeworkID;
    }

    public void setHomeworkID(String homeworkID) {
        this.homeworkID = homeworkID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivationcode() {
        return activationcode;
    }

    public void setActivationcode(String activationcode) {
        this.activationcode = activationcode;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", courseID='" + courseID + '\'' +
                ", homeworkID='" + homeworkID + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
