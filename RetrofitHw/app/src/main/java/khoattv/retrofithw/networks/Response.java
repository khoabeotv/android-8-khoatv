package khoattv.retrofithw.networks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KhoaBeo on 5/27/2017.
 */

public class Response {
  @SerializedName("access_token")
  private String accessToken;

  public Response(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }
}
