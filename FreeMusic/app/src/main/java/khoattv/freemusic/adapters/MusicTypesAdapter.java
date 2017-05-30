package khoattv.freemusic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import khoattv.freemusic.R;
import khoattv.freemusic.databases.model.MusicTypesModel;
import khoattv.freemusic.networks.MusicType;

/**
 * Created by KhoaBeo on 5/28/2017.
 */

public class MusicTypesAdapter extends RecyclerView.Adapter<MusicTypesAdapter.MusicTypesViewHolder> {

  private List<MusicTypesModel> musicTypesModels;
  private Context context;

  public MusicTypesAdapter(List<MusicTypesModel> musicTypesModels, Context context) {
    this.musicTypesModels = musicTypesModels;
    this.context = context;
  }

  @Override
  public MusicTypesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.item_rv_music_types, parent, false);
    MusicTypesViewHolder musicTypesViewHolder = new MusicTypesViewHolder(view);
    return musicTypesViewHolder;
  }

  @Override
  public void onBindViewHolder(MusicTypesViewHolder holder, int position) {
    holder.setData(musicTypesModels.get(position));
  }

  @Override
  public int getItemCount() {
    return musicTypesModels.size();
  }

  public class MusicTypesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.im_music_type)
    ImageView imageView;
    @BindView(R.id.tv_music_type)
    TextView textView;

    public MusicTypesViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void setData(MusicTypesModel data) {
      textView.setText(data.getKey());
      Picasso.with(context).load(data.getIdImage()).into(imageView);
    }
  }
}
