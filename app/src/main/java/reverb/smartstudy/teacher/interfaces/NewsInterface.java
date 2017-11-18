package reverb.smartstudy.teacher.interfaces;

import reverb.smartstudy.teacher.model.News;
import reverb.smartstudy.teacher.model.NewsRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by HP on 9/6/2017.
 */

public interface NewsInterface {
    @POST("api/news")
    Call<ArrayList<News>> getnews(@Body NewsRequest newsRequest);

}
