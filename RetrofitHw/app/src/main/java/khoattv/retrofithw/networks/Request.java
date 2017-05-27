package khoattv.retrofithw.networks;

/**
 * Created by KhoaBeo on 5/27/2017.
 */

public class Request {
  private String username;
  private String password;

  public Request(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
