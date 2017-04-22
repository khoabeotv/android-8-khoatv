package databases.models;

/**
 * Created by KhoaBeo on 4/22/2017.
 */

public class Tips {
  private String title;
  private String image;
  private String des;
  private Boolean isFavorite;

  public Tips(String title, String image, String des, Boolean isFavorite) {
    this.title = title;
    this.image = image;
    this.des = des;
    this.isFavorite = isFavorite;
  }

  public String getTitle() {
    return title;
  }

  public String getImage() {
    return image;
  }

  public String getDes() {
    return des;
  }

  public Boolean getFavorite() {
    return isFavorite;
  }
}
