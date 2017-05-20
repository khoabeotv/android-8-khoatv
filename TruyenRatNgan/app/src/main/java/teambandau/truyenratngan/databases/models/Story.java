package teambandau.truyenratngan.databases.models;

import java.io.Serializable;

import teambandau.truyenratngan.StoryApplication;
import teambandau.truyenratngan.adapters.StoryAdapter;

/**
 * Created by KhoaBeo on 4/18/2017.
 */

public class Story implements Serializable {

  private int id;
  private String title;
  private String image;
  private String des;
  private Boolean isFavorite;
  private int lastChapterNo;

  public Story(int id, String title, String image, String des, Boolean isFavorite, int lastChapterNo) {
    this.id = id;
    this.title = title;
    this.image = image;
    this.des = des;
    this.isFavorite = isFavorite;
    this.lastChapterNo = lastChapterNo;
  }

  public int getLastChapterNo() {
    return lastChapterNo;
  }

  public void setLastChapterNo(int lastChapterNo) {
    this.lastChapterNo = lastChapterNo;
    StoryApplication.getInstance().getStoryDatabase().updateLastChapterNo(id, lastChapterNo);
  }

  public int getId() {
    return id;
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

  public Boolean isFavorite() {
    return isFavorite;
  }

  public void setFavorite(Boolean favorite) {
    isFavorite = favorite;
    StoryApplication.getInstance().getStoryDatabase().updateFavorite(id, favorite);
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
