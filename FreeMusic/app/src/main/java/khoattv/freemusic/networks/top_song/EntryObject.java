package khoattv.freemusic.networks.top_song;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class EntryObject {
  @SerializedName("im:name")
  private NameObject nameObject;
  @SerializedName("im:image")
  private List<ImageObject> imageObjects;
  @SerializedName("im:artist")
  private ArtistObject artistObject;

  public EntryObject(NameObject nameObject, List<ImageObject> imageObjects, ArtistObject artistObject) {
    this.nameObject = nameObject;
    this.imageObjects = imageObjects;
    this.artistObject = artistObject;
  }

  public NameObject getNameObject() {
    return nameObject;
  }

  public List<ImageObject> getImageObjects() {
    return imageObjects;
  }

  public ArtistObject getArtistObject() {
    return artistObject;
  }
}
