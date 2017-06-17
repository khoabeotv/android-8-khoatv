package khoattv.freemusic.events;

import khoattv.freemusic.databases.model.MusicTypesModel;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class OnClickMusicTypeModel {
  private MusicTypesModel musicTypesModel;

  public OnClickMusicTypeModel(MusicTypesModel musicTypesModel) {
    this.musicTypesModel = musicTypesModel;
  }

  public MusicTypesModel getMusicTypesModel() {
    return musicTypesModel;
  }

  public void setMusicTypesModel(MusicTypesModel musicTypesModel) {
    this.musicTypesModel = musicTypesModel;
  }
}
