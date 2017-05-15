package teambandau.truyenratngan.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by KhoaBeo on 5/16/2017.
 */

public class Util {
  public static void makeFit(final TextView textView) {
    textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
      @Override
      public void onGlobalLayout() {
        textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        int maxLines = textView.getHeight() / textView.getLineHeight();
        if (textView.getLineCount() > maxLines) {
          textView.setMaxLines(maxLines);
        }
      }
    });
  }

}
