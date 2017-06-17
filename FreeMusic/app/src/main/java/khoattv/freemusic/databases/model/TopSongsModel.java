package khoattv.freemusic.databases.model;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class TopSongsModel {
  private String name;
  private String smallIm;
  private String largeIm;
  private String artist;
  private String linkSource;

  public TopSongsModel(String name, String smallIm, String largeIm, String artist) {
    this.name = name;
    this.smallIm = smallIm;
    this.largeIm = largeIm;
    this.artist = artist;
  }

  public String getLinkSource() {
    return linkSource;
  }

  public void setLinkSource(String linkSource) {
    this.linkSource = linkSource;
  }

  public String getName() {
    return name;
  }

  public String getSmallIm() {
    return smallIm;
  }

  public String getLargeIm() {
    return largeIm;
  }

  public String getArtist() {
    return artist;
  }
}
