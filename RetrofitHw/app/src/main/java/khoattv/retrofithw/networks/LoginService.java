package khoattv.retrofithw.networks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by KhoaBeo on 5/27/2017.
 */

public interface LoginService {
  @POST("login")
  Call<Response> login(@Body Request request);
}
