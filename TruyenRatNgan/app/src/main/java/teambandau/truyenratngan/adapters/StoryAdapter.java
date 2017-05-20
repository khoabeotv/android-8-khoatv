package teambandau.truyenratngan.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import teambandau.truyenratngan.networks.ImageLoader;
import teambandau.truyenratngan.R;
import teambandau.truyenratngan.databases.models.Story;
import teambandau.truyenratngan.utils.Util;

/**
 * Created by KhoaBeo on 5/7/2017.
 */

public class StoryAdapter extends BaseAdapter {

  private List<Story> stories;

  public StoryAdapter(List<Story> stories) {
    this.stories = stories;
  }

  @Override
  public int getCount() {
    return stories.size();
  }

  @Override
  public Object getItem(int position) {
    return stories.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final Story story = stories.get(position);

    if (convertView == null) {
      LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
      convertView = layoutInflater.inflate(R.layout.item_story, parent, false);
    }

    TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
    TextView tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
    ImageView ivStory = (ImageView) convertView.findViewById(R.id.iv_story);
    ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.pb_loading);
    final ImageView ivFav = (ImageView) convertView.findViewById(R.id.iv_fav);

    tvTitle.setText(story.getTitle());
    Util.makeFit(tvDescription);
    tvDescription.setText(story.getDes());
    if (story.isFavorite()) {
      ivFav.setImageResource(R.drawable.ic_favorite_black_24dp);
    } else {
      ivFav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }

    ivFav.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (story.isFavorite()) {
          ivFav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
          story.setFavorite(false);
        } else {
          ivFav.setImageResource(R.drawable.ic_favorite_black_24dp);
          story.setFavorite(true);
        }
      }
    });

    new ImageLoader().setView(ivStory, progressBar).loadingImage(story.getImage());

    return convertView;
  }
}
