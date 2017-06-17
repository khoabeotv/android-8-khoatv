package khoattv.freemusic.networks.music_type;

import java.util.List;

/**
 * Created by KhoaBeo on 5/23/2017.
 */

public class MediaType {
  private SubType subgenres;

  public MediaType(SubType subgenres) {
    this.subgenres = subgenres;
  }

  public SubType getSubgenres() {
    return subgenres;
  }
}
