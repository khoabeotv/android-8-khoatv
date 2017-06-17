package khoattv.freemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import khoattv.freemusic.R;
import khoattv.freemusic.adapters.MusicTypesAdapter;
import khoattv.freemusic.databases.model.MusicTypesModel;
import khoattv.freemusic.events.OnClickMusicTypeModel;
import khoattv.freemusic.managers.ScreenManager;
import khoattv.freemusic.networks.music_type.ITunesType;
import khoattv.freemusic.networks.music_type.MediaType;
import khoattv.freemusic.networks.music_type.MusicType;
import khoattv.freemusic.networks.music_type.MusicTypesService;
import khoattv.freemusic.networks.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicTypesFragment extends Fragment implements View.OnClickListener {

  @BindView(R.id.rv_music_types)
  RecyclerView recyclerView;
  private List<MusicTypesModel> musicTypesModels;


  public MusicTypesFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_music_types, container, false);
    musicTypesModels = new ArrayList<>();
    setupUI(view);
    loadData();
    return view;
  }

  private void loadData() {
    MusicTypesService musicTypesService = RetrofitFactory.getInstance().createService(MusicTypesService.class);
    musicTypesService.getMediaTypes().enqueue(new Callback<ITunesType>() {
      @Override
      public void onResponse(Call<ITunesType> call, Response<ITunesType> response) {
        List<MusicType> musicTypes = response.body().getMediaType().getSubgenres().getListMusicTypes();

        for (MusicType musicType : musicTypes) {
          musicTypesModels.add(new MusicTypesModel(
                          musicType.getId(),
                          musicType.getTranslationKey(),
                          getResources().getIdentifier("genre_x2_" + musicType.getId(), "raw", getActivity().getPackageName())
                  )
          );
        }
        recyclerView.getAdapter().notifyDataSetChanged();

      }

      @Override
      public void onFailure(Call<ITunesType> call, Throwable t) {
        Toast.makeText(getActivity(), "No Connection!", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void setupUI(View view) {
    ButterKnife.bind(this, view);
    recyclerView.setAdapter(new MusicTypesAdapter(musicTypesModels, getActivity()));
    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return (position % 3 == 0 ? 2 : 1);
      }
    });
    recyclerView.setLayoutManager(gridLayoutManager);
    ((MusicTypesAdapter) recyclerView.getAdapter()).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    MusicTypesModel musicTypesModel = (MusicTypesModel) v.getTag();
    ScreenManager.openFragment(getFragmentManager(), new TopSongsFragment(), R.id.rl_main, true, false);
    EventBus.getDefault().postSticky(new OnClickMusicTypeModel(musicTypesModel));
  }
}
