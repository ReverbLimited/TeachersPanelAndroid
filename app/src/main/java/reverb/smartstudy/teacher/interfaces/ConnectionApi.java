package reverb.smartstudy.teacher.interfaces;

import reverb.smartstudy.teacher.model.InsertManualAttendanceModel;
import reverb.smartstudy.teacher.responseModelClass.CourseResponseModel;
import reverb.smartstudy.teacher.responseModelClass.CourseScheduleWithAttendanceResponseModel;
import reverb.smartstudy.teacher.responseModelClass.DateWiseStudentAttendanceListResponseModel;
import reverb.smartstudy.teacher.responseModelClass.HomeWorkListPojoModel;
import reverb.smartstudy.teacher.responseModelClass.ManualAttendanceInsertResponseModel;
import reverb.smartstudy.teacher.responseModelClass.NewsResponseModel;
import reverb.smartstudy.teacher.responseModelClass.SchoolResponseModel;
import reverb.smartstudy.teacher.responseModelClass.SubmittedHomeWorkResponseModel;
import reverb.smartstudy.teacher.model.UserRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import reverb.smartstudy.teacher.responseModelClass.CourseStudentAttendanceModel;
import reverb.smartstudy.teacher.responseModelClass.UserLoginResponseModel;


/**
 * Created by HP on 9/6/2017.
 */

public interface ConnectionApi {


    @POST("api/checkactivationcode")
    Call<SchoolResponseModel> checkActivation(@Body UserRequest activationRequest);

    @POST("api/login")
    Call<UserLoginResponseModel> login(@Body UserRequest loginRequest);



    @POST("api/news")
    Call<ArrayList<NewsResponseModel>> getNews(@Body UserRequest userRequest);

    @POST("api/teacher/courselist")
    Call<CourseResponseModel> getCourse(@Body UserRequest userRequest);

    @POST("api/teacher/courseHomeworkList")
    Call<HomeWorkListPojoModel> getHomeWorkList(@Body UserRequest userRequest);

    @POST("api/teacher/submittedhomeworklist")
    Call<SubmittedHomeWorkResponseModel> getHomeWorkSubmittedList(@Body UserRequest userRequest);

    @POST("api/teacher/courseStudentsAttendance")
    Call<CourseStudentAttendanceModel> getCourseStudentsAttendance(@Body UserRequest userRequest);

    @POST("api/teacher/courseScheduleWithAttendance")
    Call<CourseScheduleWithAttendanceResponseModel> getCourseScheduleWithAttendance(@Body UserRequest userRequest);

    @POST("api/teacher/courseAttendanceByDate")
    Call<DateWiseStudentAttendanceListResponseModel> getDateWiseStudentAttendance(@Body UserRequest userRequest);


    @POST("api/teacher/attendance/bulkAttendaneEntry")
    Call<ManualAttendanceInsertResponseModel> setInsertManualAttendance(@Body InsertManualAttendanceModel insertManualAttendanceModel);


}
