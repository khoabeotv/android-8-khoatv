package khoattv.freemusic.events;

import khoattv.freemusic.databases.model.TopSongsModel;

/**
 * Created by KhoaBeo on 6/11/2017.
 */

public class LoadUiPlayer {
  private TopSongsModel topSongsModel;

  public LoadUiPlayer(TopSongsModel topSongsModel) {
    this.topSongsModel = topSongsModel;
  }

  public TopSongsModel getTopSongsModel() {
    return topSongsModel;
  }
}
