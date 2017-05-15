package teambandau.testimagebasicdownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

  String nyc = "http://img.truyen8.mobi/2014/0123/nguoi_yeu_cu_co_nguoi_yeu_moi_5.jpg";
  ImageView imageView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    imageView = (ImageView) findViewById(R.id.image);
    downloadImage();
  }

  private void downloadImage() {
    //1: Open connection
    try {
      URL url = new URL(nyc);
      //2: Create stream
      InputStream inputStream = url.openStream();
      //3: Convert to bitmap
      Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
      //4: Set image for imageView
      imageView.setImageBitmap(bitmap);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
