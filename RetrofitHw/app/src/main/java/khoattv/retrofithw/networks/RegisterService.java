package khoattv.retrofithw.networks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by KhoaBeo on 5/28/2017.
 */

public interface RegisterService {
  @POST("register")
  Call<Response> register(@Body Request request);
}
