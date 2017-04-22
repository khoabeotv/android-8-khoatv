package teambandau.meonauan;

import android.app.Application;
import databases.TipsDatabase;

/**
 * Created by KhoaBeo on 4/22/2017.
 */

public class TipsApplication extends Application {
  private static final String TAG = "TipsApplication";
  private TipsDatabase TipsDatabase;

  private static TipsApplication instance;

  public static TipsApplication getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    TipsDatabase = new TipsDatabase(this);
    instance = this;
    super.onCreate();
  }

  public TipsDatabase getTipsDatabase() {
    return TipsDatabase;
  }
}
