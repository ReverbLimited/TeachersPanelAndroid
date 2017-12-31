package reverb.smartstudy.teacher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 10/12/17.
 */

public class HomeWorkListPojoModel {
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("error")
    @Expose
    private Boolean error;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public static class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("task")
        @Expose
        private String task;
        @SerializedName("filepath")
        @Expose
        private String filepath;
        @SerializedName("courses_id")
        @Expose
        private Integer coursesId;
        @SerializedName("teachers_id")
        @Expose
        private Integer teachersId;
        @SerializedName("deadline")
        @Expose
        private String deadline;
        @SerializedName("coursename")
        @Expose
        private String coursename;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public Integer getCoursesId() {
            return coursesId;
        }

        public void setCoursesId(Integer coursesId) {
            this.coursesId = coursesId;
        }

        public Integer getTeachersId() {
            return teachersId;
        }

        public void setTeachersId(Integer teachersId) {
            this.teachersId = teachersId;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getCoursename() {
            return coursename;
        }

        public void setCoursename(String coursename) {
            this.coursename = coursename;
        }

    }

}
