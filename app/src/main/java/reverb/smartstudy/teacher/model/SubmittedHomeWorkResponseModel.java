package reverb.smartstudy.teacher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mdjahirulislam on 30/12/17.
 */

public class SubmittedHomeWorkResponseModel {
    @SerializedName("data")
    @Expose
    private List<SubmittedStudentList> data = null;
    @SerializedName("error")
    @Expose
    private Boolean error;

    public List<SubmittedStudentList> getData() {
        return data;
    }

    public void setData(List<SubmittedStudentList> data) {
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }


    public static class SubmittedStudentList {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("filepath")
        @Expose
        private Object filepath;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("homeworks_id")
        @Expose
        private Integer homeworksId;
        @SerializedName("students_id")
        @Expose
        private Integer studentsId;
        @SerializedName("attempts")
        @Expose
        private Integer attempts;
        @SerializedName("resubmit")
        @Expose
        private Integer resubmit;
        @SerializedName("correction")
        @Expose
        private String correction;
        @SerializedName("correctionfile")
        @Expose
        private String correctionfile;

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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Object getFilepath() {
            return filepath;
        }

        public void setFilepath(Object filepath) {
            this.filepath = filepath;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Integer getHomeworksId() {
            return homeworksId;
        }

        public void setHomeworksId(Integer homeworksId) {
            this.homeworksId = homeworksId;
        }

        public Integer getStudentsId() {
            return studentsId;
        }

        public void setStudentsId(Integer studentsId) {
            this.studentsId = studentsId;
        }

        public Integer getAttempts() {
            return attempts;
        }

        public void setAttempts(Integer attempts) {
            this.attempts = attempts;
        }

        public Integer getResubmit() {
            return resubmit;
        }

        public void setResubmit(Integer resubmit) {
            this.resubmit = resubmit;
        }

        public String getCorrection() {
            return correction;
        }

        public void setCorrection(String correction) {
            this.correction = correction;
        }

        public String getCorrectionfile() {
            return correctionfile;
        }

        public void setCorrectionfile(String correctionfile) {
            this.correctionfile = correctionfile;
        }

        @Override
        public String toString() {
            return "SubmittedStudentList{" +
                    "id=" + id +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", text='" + text + '\'' +
                    ", filepath=" + filepath +
                    ", status='" + status + '\'' +
                    ", comment='" + comment + '\'' +
                    ", homeworksId=" + homeworksId +
                    ", studentsId=" + studentsId +
                    ", attempts=" + attempts +
                    ", resubmit=" + resubmit +
                    ", correction='" + correction + '\'' +
                    ", correctionfile='" + correctionfile + '\'' +
                    '}';
        }
    }
}
