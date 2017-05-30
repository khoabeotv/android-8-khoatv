package khoattv.freemusic.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import khoattv.freemusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongsFragment extends Fragment {

  @BindView(R.id.fab)
  FloatingActionButton floatingActionButton;
  @BindView(R.id.iv_top_songs)
  ImageView ivTopSongs;
  @BindView(R.id.rv_top_songs)
  RecyclerView rvTopSongs;
  @BindView(R.id.tv_music_type)
  RecyclerView tvMusicTyps;


  public TopSongsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_top_songs, container, false);
  }

}
