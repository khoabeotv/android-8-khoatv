package khoattv.freemusic.utils;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by KhoaBeo on 6/13/2017.
 */

public class Utils {
  public static String convertTime(int time) {
    return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time),
            TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MILLISECONDS.toMinutes(time) * 60);
  }
}
