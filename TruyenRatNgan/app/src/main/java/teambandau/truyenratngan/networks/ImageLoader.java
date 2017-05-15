package teambandau.truyenratngan.networks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by KhoaBeo on 5/15/2017.
 */

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {

  private ImageView imageView;
  private ProgressBar progressBar;
  private String urlString;
  private String imageTag;

  public ImageLoader() {
  }

  public ImageLoader setView(ImageView imageView, ProgressBar progressBar) {
    this.imageView = imageView;
    this.progressBar = progressBar;
    this.imageTag = (imageView.getTag() == null) ? "" : imageView.getTag().toString();
    return this;
  }

  public void loadingImage(String urlString) {
    if (!urlString.equals(imageTag)) {
      imageView.setVisibility(View.GONE);
      progressBar.setVisibility(View.VISIBLE);
      execute(urlString);
    }
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override
  protected Bitmap doInBackground(String... params) {
    urlString = params[0];
    if (urlString.equals(imageTag)) {
      return null;
    }

    try {
      URL url = new URL(urlString);
      InputStream inputStream = url.openStream();
      return BitmapFactory.decodeStream(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onPostExecute(Bitmap bitmap) {
    if (bitmap != null) {
      imageView.setImageBitmap(bitmap);
      progressBar.setVisibility(View.GONE);
      imageView.setVisibility(View.VISIBLE);
      imageView.setTag(urlString);
    }
  }
}
