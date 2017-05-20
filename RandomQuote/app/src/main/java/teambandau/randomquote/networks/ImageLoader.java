package teambandau.randomquote.networks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import teambandau.randomquote.RandomQuoteApplication;

/**
 * Created by KhoaBeo on 5/15/2017.
 */

public class ImageLoader extends AsyncTask<Void, Void, Bitmap> {

  private static final String IMAGE_LANDSCAPE_URl = "https://source.unsplash.com/random/300x500";
  private static final String IMAGE_PORTRAIT_URL = "https://source.unsplash.com/random/500x300";

  private ImageView imageView;
  private ProgressBar progressBar;
  private String imageUrl;

  public ImageLoader() {
  }

  @Override
  protected Bitmap doInBackground(Void... params) {
    try {
      URL url = new URL(imageUrl);
      InputStream inputStream = url.openStream();
      return BitmapFactory.decodeStream(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public ImageLoader setView(ImageView imageView, ProgressBar progressBar) {
    this.imageView = imageView;
    this.progressBar = progressBar;
    imageView.setVisibility(View.GONE);
    progressBar.setVisibility(View.VISIBLE);
    Display display = ((WindowManager) RandomQuoteApplication.getInstance().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int rotation = display.getRotation();
    if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
      imageUrl = IMAGE_LANDSCAPE_URl;
    } else {
      imageUrl = IMAGE_PORTRAIT_URL;
    }
    return this;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override
  protected void onPostExecute(Bitmap bitmap) {
    if (bitmap != null) {
      imageView.setImageBitmap(bitmap);
      progressBar.setVisibility(View.GONE);
      imageView.setVisibility(View.VISIBLE);
    }
  }
}
