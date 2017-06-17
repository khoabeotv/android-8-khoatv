package khoattv.freemusic.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import khoattv.freemusic.R;
import khoattv.freemusic.adapters.TopSongsAdapter;
import khoattv.freemusic.databases.model.MusicTypesModel;
import khoattv.freemusic.databases.model.TopSongsModel;
import khoattv.freemusic.events.OnClickMusicTypeModel;
import khoattv.freemusic.events.OnClickTopSong;
import khoattv.freemusic.managers.MusicManager;
import khoattv.freemusic.managers.ScreenManager;
import khoattv.freemusic.networks.RetrofitFactory;
import khoattv.freemusic.networks.top_song.EntryObject;
import khoattv.freemusic.networks.top_song.GetTopSongsService;
import khoattv.freemusic.networks.top_song.MainObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongsFragment extends Fragment implements View.OnClickListener {

  @BindView(R.id.fab)
  FloatingActionButton floatingActionButton;
  @BindView(R.id.iv_top_songs)
  ImageView ivTopSongs;
  @BindView(R.id.rv_top_songs)
  RecyclerView rvTopSongs;
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  private List<TopSongsModel> topSongsModels = new ArrayList<>();

  public TopSongsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_top_songs, container, false);
    setupUI(view);
    return view;
  }

  @Subscribe(sticky = true)
  public void onReceivedMusicType(OnClickMusicTypeModel onClickMusicTypeModel) {
    MusicTypesModel musicTypesModel = onClickMusicTypeModel.getMusicTypesModel();
    ivTopSongs.setImageResource(musicTypesModel.getIdImage());
    toolbar.setTitle(musicTypesModel.getKey().toUpperCase());
    loadData(musicTypesModel);
  }

  private void loadData(MusicTypesModel musicTypesModel) {
    GetTopSongsService getTopSongsService = RetrofitFactory.createService(GetTopSongsService.class);
    getTopSongsService.getTopSongs(musicTypesModel.getId()).enqueue(new Callback<MainObject>() {
      @Override
      public void onResponse(Call<MainObject> call, Response<MainObject> response) {
        for (EntryObject entryObject: response.body().getFeed().getEntry()) {
          topSongsModels.add(new TopSongsModel(
                  entryObject.getNameObject().getLabel(),
                  entryObject.getImageObjects().get(1).getLabel(),
                  entryObject.getImageObjects().get(2).getLabel(),
                  entryObject.getArtistObject().getLabel()
          ));
        }
        rvTopSongs.getAdapter().notifyDataSetChanged();
      }

      @Override
      public void onFailure(Call<MainObject> call, Throwable t) {

      }
    });
  }

  public void setupUI(View view) {
    ButterKnife.bind(this, view);
    rvTopSongs.setAdapter(new TopSongsAdapter(topSongsModels, getActivity()).setOnItemClickListener(this));
    rvTopSongs.setLayoutManager(new LinearLayoutManager(getActivity()));

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
    rvTopSongs.addItemDecoration(dividerItemDecoration);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ScreenManager.backFragment(getFragmentManager());
      }
    });
    EventBus.getDefault().register(this);
  }

  @Override
  public void onClick(View v) {
    TopSongsModel topSongsModel = topSongsModels.get(rvTopSongs.getChildLayoutPosition(v));
    EventBus.getDefault().post(new OnClickTopSong(topSongsModel));
  }
}
