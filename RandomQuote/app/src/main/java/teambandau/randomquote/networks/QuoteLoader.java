package teambandau.randomquote.networks;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by KhoaBeo on 5/16/2017.
 */

public class QuoteLoader extends AsyncTask<Void, Void, String> {

  private final String QUOTE_URL = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand";
  private TextView textView;

  public QuoteLoader(TextView textView) {
    this.textView = textView;
  }

  @Override
  protected String doInBackground(Void... params) {
    try {
      URL url = new URL(QUOTE_URL);
      InputStream inputStream = url.openStream();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      StringBuilder stringBuilder = new StringBuilder();
      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }

      String result = stringBuilder.toString();
      JSONArray jsonArray = new JSONArray(result);
      JSONObject jsonObject = jsonArray.getJSONObject(0);
      String content = jsonObject.getString("content");
      return content;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  protected void onPostExecute(String s) {
    if (s != null) {
      String temp = Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT).toString();
      Log.d("khoa", temp);
      temp = temp.substring(0, temp.length() - 2);
      textView.setText("\"" + temp + "\"");
    }
  }
}
