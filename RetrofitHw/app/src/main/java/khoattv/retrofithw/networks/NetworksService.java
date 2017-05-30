package khoattv.retrofithw.networks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by KhoaBeo on 5/27/2017.
 */

public interface NetworksService {
  @POST("login")
  Call<Response> login(@Body Request request);

  @POST("register")
  Call<Response> register(@Body Request request);

  @GET("task")
  Call<List<Task>> getAllTask(@Header("Authorization") String token);
}
