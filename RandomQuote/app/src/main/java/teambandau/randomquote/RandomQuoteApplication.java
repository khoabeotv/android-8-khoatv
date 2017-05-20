package teambandau.randomquote;

import android.app.Application;

/**
 * Created by KhoaBeo on 5/20/2017.
 */

public class RandomQuoteApplication extends Application {
  private static RandomQuoteApplication instance;

  public static RandomQuoteApplication getInstance() {
    return instance;
  };

  @Override
  public void onCreate() {
    instance = this;
    super.onCreate();
  }
}
