package khoattv.freemusic.networks.music_type;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import khoattv.freemusic.fragments.MusicTypesFragment;

/**
 * Created by KhoaBeo on 5/23/2017.
 */

public class MusicType {
  private String id;

  @SerializedName("name")
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
