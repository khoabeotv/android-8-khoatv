package khoattv.freemusic.events;

import khoattv.freemusic.databases.model.TopSongsModel;

/**
 * Created by KhoaBeo on 6/6/2017.
 */

public class OnClickTopSong {
  private TopSongsModel topSongsModel;

  public OnClickTopSong(TopSongsModel topSongsModel) {
    this.topSongsModel = topSongsModel;
  }

  public TopSongsModel getTopSongsModel() {
    return topSongsModel;
  }
}
