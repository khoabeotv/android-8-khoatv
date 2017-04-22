package teambandau.truyenratngan.databases.models;

/**
 * Created by KhoaBeo on 4/18/2017.
 */

public class Story {

  private String title;
  private String image;
  private String des;
  private Boolean isFavorite;

  public Story(String title, String image, String des, Boolean isFavorite) {
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

  @Override
  public String toString() {
    return "Story{" +
            "title='" + title + '\'' +
            ", image='" + image + '\'' +
            ", des='" + des + '\'' +
            ", isFavorite=" + isFavorite +
            '}';
  }
}
