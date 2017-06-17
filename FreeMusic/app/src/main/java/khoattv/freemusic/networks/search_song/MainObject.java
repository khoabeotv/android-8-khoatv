package khoattv.freemusic.networks.search_song;

import java.util.List;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class MainObject {
  private List<DocObject> docs;

  public MainObject(List<DocObject> docs) {
    this.docs = docs;
  }

  public List<DocObject> getDocs() {
    return docs;
  }
}
