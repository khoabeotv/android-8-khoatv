package khoattv.freemusic.networks.search_song;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class Source {
  @SerializedName("128")
  private String linkSource;

  public Source(String linkSource) {
    this.linkSource = linkSource;
  }

  public String getLinkSource() {
    return linkSource;
  }
}
