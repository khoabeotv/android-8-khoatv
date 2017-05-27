package khoattv.freemusic.networks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KhoaBeo on 5/23/2017.
 */

public class MusicType {
  private String id;

  @SerializedName("translation_key")
  private String translationKey;

  public MusicType(String id, String translationKey) {
    this.id = id;
    this.translationKey = translationKey;
  }

  public String getId() {
    return id;
  }

  public String getTranslationKey() {
    return translationKey;
  }
}
