package khoattv.freemusic.networks.search_song;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class DocObject {
  private String title;
  private String artist;
  private Source source;

  public DocObject(String title, String artist, Source source) {
    this.title = title;
    this.artist = artist;
    this.source = source;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public Source getSource() {
    return source;
  }
}
