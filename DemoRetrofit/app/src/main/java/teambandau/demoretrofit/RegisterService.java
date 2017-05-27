package teambandau.demoretrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by KhoaBeo on 5/23/2017.
 */

public interface RegisterService {
  @POST("register")
  Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
