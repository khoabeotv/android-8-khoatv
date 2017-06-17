package khoattv.freemusic.networks.music_type;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KhoaBeo on 6/17/2017.
 */

public class ITunesType {
  @SerializedName("34")
  private MediaType mediaType;

  public ITunesType(MediaType mediaType) {
    this.mediaType = mediaType;
  }

  public MediaType getMediaType() {
    return mediaType;
  }
}
