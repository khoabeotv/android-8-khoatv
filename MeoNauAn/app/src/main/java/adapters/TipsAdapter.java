package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import teambandau.meonauan.MainActivity;
import teambandau.meonauan.R;
import databases.models.Tips;

/**
 * Created by KhoaBeo on 4/22/2017.
 */

public class TipsAdapter extends BaseAdapter {

  private List<Tips> tipsList;
  private LayoutInflater inflater;
  private Context context;

  public TipsAdapter(Context context, List<Tips> memos) {
    this.tipsList = memos;
    this.context = context;
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    return tipsList.size();
  }

  @Override
  public Object getItem(int position) {
    return tipsList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    view = inflater.inflate(R.layout.item_lv, null, false);
    ImageView imageView = (ImageView) view.findViewById(R.id.tips_im);
    TextView tvTitle = (TextView) view.findViewById(R.id.tips_title);
    TextView tvDes = (TextView) view.findViewById(R.id.tips_des);

    tvTitle.setText(tipsList.get(position).getTitle());
    tvDes.setText(tipsList.get(position).getDes());

//    try {
//      URL url = new URL(tipsList.get(position).getImage());
//      Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//      imageView.setImageBitmap(bmp);
//    } catch (MalformedURLException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    Picasso.with(context).load(tipsList.get(position).getImage()).into(imageView);

    return view;
  }
}
