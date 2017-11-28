package reverb.smartstudy.teacher.interfaces;

import reverb.smartstudy.teacher.model.CourseResponseModel;
import reverb.smartstudy.teacher.model.News;
import reverb.smartstudy.teacher.model.UserRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by HP on 9/6/2017.
 */

public interface ConnectionApi {
    @POST("api/news")
    Call<ArrayList<News>> getNews(@Body UserRequest userRequest);

    @POST("api/teacher/courselist")
    Call<CourseResponseModel> getCourse(@Body UserRequest userRequest);

}
