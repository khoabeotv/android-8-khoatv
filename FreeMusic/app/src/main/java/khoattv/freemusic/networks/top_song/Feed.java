package khoattv.freemusic.networks.top_song;

import java.util.List;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class Feed {
  private List<EntryObject> entry;

  public Feed(List<EntryObject> entry) {
    this.entry = entry;
  }

  public List<EntryObject> getEntry() {
    return entry;
  }
}
