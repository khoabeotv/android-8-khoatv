package khoattv.freemusic.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import khoattv.freemusic.R;
import khoattv.freemusic.databases.model.TopSongsModel;
import khoattv.freemusic.events.OnClickMiniPlayer;
import khoattv.freemusic.managers.MusicManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayerFragment extends Fragment {

  @BindView(R.id.iv_previous)
  ImageView ivPrev;
  @BindView(R.id.iv_next)
  ImageView ivNext;
  @BindView(R.id.iv_main)
  ImageView ivMain;
  @BindView(R.id.iv_blur)
  ImageView ivBlur;
  @BindView(R.id.tv_time)
  TextView tvTime;
  @BindView(R.id.tv_time_max)
  TextView tvTimeMax;
  @BindView(R.id.fab_main)
  FloatingActionButton fabPlay;
  @BindView(R.id.tv_main_artist)
  TextView tvArtist;
  @BindView(R.id.tv_main_song)
  TextView tvSong;
  @BindView(R.id.sb_main_1)
  SeekBar sb1;
  @BindView(R.id.sb_main_2)
  SeekBar sb2;
  public MainPlayerFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_main_player, container, false);
    ButterKnife.bind(this, view);
    sb1.setPadding(0, 0, 0, 0);
    sb2.setPadding(0, 0, 0, 0);
    EventBus.getDefault().register(this);
    return view;
  }

  @Subscribe(sticky = true)
  public void onReceivedTopSong(OnClickMiniPlayer onClickMiniPlayer) {
    TopSongsModel topSongsModel = onClickMiniPlayer.getTopSongsModel();
    updateUIMainPLayer(topSongsModel);
  }

  private void updateUIMainPLayer(TopSongsModel topSongsModel) {
    tvArtist.setText(topSongsModel.getArtist());
    tvSong.setText(topSongsModel.getName());
    Picasso.with(getActivity()).load(topSongsModel.getLargeIm()).transform(new BlurTransformation(getActivity(), 10)).into(ivBlur);
    Picasso.with(getActivity()).load(topSongsModel.getLargeIm()).into(ivMain);

    MusicManager.updateSongRealTime(fabPlay, sb1, sb2, tvTime, tvTimeMax);
    fabPlay.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MusicManager.playOrPause();
      }
    });
  }
}
