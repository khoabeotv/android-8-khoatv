package khoattv.freemusic.databases.model;

/**
 * Created by KhoaBeo on 5/28/2017.
 */

public class MusicTypesModel {
  private String id;
  private String key;
  private int idImage;

  public MusicTypesModel(String id, String key, int idImage) {
    this.id = id;
    this.key = key;
    this.idImage = idImage;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public int getIdImage() {
    return idImage;
  }

  public void setIdImage(int idImage) {
    this.idImage = idImage;
  }
}
