package teambandau.truyenratngan;

import android.app.Application;
import android.util.Log;

import teambandau.truyenratngan.databases.StoryDatabase;

/**
 * Created by KhoaBeo on 4/18/2017.
 */

public class StoryApplication extends Application {

  private static final String TAG = "StoryApplication";
  private StoryDatabase storyDatabase;

  private static StoryApplication instance;

  public static StoryApplication getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    storyDatabase = new StoryDatabase(this);
    instance = this;
    super.onCreate();
  }

  public StoryDatabase getStoryDatabase() {
    return storyDatabase;
  }
}
