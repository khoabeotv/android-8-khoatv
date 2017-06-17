package khoattv.freemusic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import khoattv.freemusic.R;

import java.util.List;

import butterknife.BindView;
import khoattv.freemusic.databases.model.TopSongsModel;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.TopSongsViewHolder> {

  private List<TopSongsModel> topSongsModels;
  private Context context;
  private View.OnClickListener onClickListener;

  public TopSongsAdapter(List<TopSongsModel> topSongsModels, Context context) {
    this.topSongsModels = topSongsModels;
    this.context = context;
  }

  public TopSongsAdapter setOnItemClickListener(View.OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
    return this;
  }

  @Override
  public TopSongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.item_top_songs, parent, false);
    TopSongsViewHolder topSongsViewHolder = new TopSongsViewHolder(view);
    view.setOnClickListener(onClickListener);
    return topSongsViewHolder;
  }

  @Override
  public void onBindViewHolder(TopSongsViewHolder holder, int position) {
    holder.setData(topSongsModels.get(position));
  }

  @Override
  public int getItemCount() {
    return topSongsModels.size();
  }

  public class TopSongsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_song)
    ImageView imageView;
    @BindView(R.id.tv_song_name)
    TextView tvName;
    @BindView(R.id.tv_artist)
    TextView tvArtist;
    View view;

    public TopSongsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.view = itemView;
    }

    public void setData(TopSongsModel data) {
      if (data != null) {
        Picasso.with(context).load(data.getSmallIm()).transform(new CropCircleTransformation()).into(imageView);
        tvName.setText(data.getName());
        tvArtist.setText(data.getArtist());
        view.setTag(data);
      }
    }
  }
}
