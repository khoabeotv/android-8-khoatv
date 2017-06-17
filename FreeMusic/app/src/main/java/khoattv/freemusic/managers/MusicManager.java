package khoattv.freemusic.managers;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import khoattv.freemusic.databases.model.TopSongsModel;
import khoattv.freemusic.events.LoadUiPlayer;
import khoattv.freemusic.networks.RetrofitFactory;
import khoattv.freemusic.networks.search_song.DocObject;
import khoattv.freemusic.networks.search_song.GetSearchSongService;
import khoattv.freemusic.networks.search_song.MainObject;
import khoattv.freemusic.services.MusicNotification;
import khoattv.freemusic.utils.FuzzyMatch;
import khoattv.freemusic.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class MusicManager {

  public static HybridMediaPlayer hybridMediaPlayer;
  private static boolean prepared = false;
  private static boolean tracking = false;

  public static void loadSearchSong(final TopSongsModel songModel, final Context context) {
    GetSearchSongService getSearchSongService = RetrofitFactory.createService(GetSearchSongService.class);
    final String dataSong = songModel.getName() + " " + songModel.getArtist();
    getSearchSongService.getSearchSong("{\"q\":\"" + dataSong + "\", \"sort\":\"hot\", \"start\":\"0\", \"length\":\"10\"}")
            .enqueue(new Callback<MainObject>() {
              @Override
              public void onResponse(Call<MainObject> call, Response<MainObject> response) {
                if (response.code() == 200 && response.body().getDocs().size() != 0) {
                  List<Integer> ratioList = new ArrayList<>();
                  for (DocObject docObject: response.body().getDocs()) {
                    int ratio = FuzzyMatch.getRatio(dataSong, docObject.getTitle() + " " + docObject.getArtist(), false);
                    ratioList.add(ratio);
                  }

                  for (int i = 0; i < ratioList.size(); i++) {
                    if (ratioList.get(i) == Collections.max(ratioList)) {
                      songModel.setLinkSource(response.body().getDocs().get(i).getSource().getLinkSource());
                      MusicManager.playMusic(context, songModel);
                      break;
                    }
                  }
                } else {
                  Toast.makeText(context, "Not found!", Toast.LENGTH_SHORT).show();
                }
              }

              @Override
              public void onFailure(Call<MainObject> call, Throwable t) {

              }
            });
  }

  public static void playMusic(final Context context, final TopSongsModel topSongsModel) {
    if (hybridMediaPlayer != null) {
      hybridMediaPlayer.release();
    }
    hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
    hybridMediaPlayer.setDataSource(topSongsModel.getLinkSource());
    hybridMediaPlayer.prepare();
    hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
      @Override
      public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
        prepared = true;
        MusicNotification.setupNotification(context, topSongsModel);
        hybridMediaPlayer.play();
        EventBus.getDefault().post(new LoadUiPlayer(topSongsModel));
      }
    });
  }

  public static void playOrPause() {
    if (prepared) {
      if (hybridMediaPlayer.isPlaying()) {
        hybridMediaPlayer.pause();
      } else {
        hybridMediaPlayer.play();
      }
    }
    MusicNotification.updateNotification(hybridMediaPlayer.isPlaying());
  }

  public static void updateSongRealTime(final FloatingActionButton floatingActionButton, final SeekBar seekBar1, final SeekBar seekBar2, final TextView tvTime, final TextView tvTimeMax) {
    final Handler handler = new Handler();
    final Runnable runnable = new Runnable() {
      @Override
      public void run() {
        if (!tracking) {
          if (hybridMediaPlayer.isPlaying()) {
            floatingActionButton.setImageResource(android.R.drawable.ic_media_pause);
          } else {
            floatingActionButton.setImageResource(android.R.drawable.ic_media_play);
          }

          seekBar1.setMax(hybridMediaPlayer.getDuration());
          seekBar1.setProgress(hybridMediaPlayer.getCurrentPosition());

          if (seekBar2 != null) {
            seekBar2.setMax(hybridMediaPlayer.getDuration());
            seekBar2.setProgress(hybridMediaPlayer.getCurrentPosition());
          }

          if (tvTime != null) {
            tvTime.setText(Utils.convertTime(hybridMediaPlayer.getCurrentPosition()));
            tvTimeMax.setText(Utils.convertTime(hybridMediaPlayer.getDuration()));
          }
        }
        handler.postDelayed(this, 1000);
      }
    };
    runnable.run();

    final int[] currentProgress = {0};
    seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        tracking = false;
        MusicManager.hybridMediaPlayer.seekTo(currentProgress[0]);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        tracking = true;
      }

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(MusicManager.hybridMediaPlayer != null && fromUser){
          currentProgress[0] = progress;
          if (seekBar2 != null) {
            seekBar2.setProgress(progress);
          }
        }
      }
    });

    if (seekBar2 != null) {
      seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
          tracking = false;
          MusicManager.hybridMediaPlayer.seekTo(currentProgress[0]);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
          tracking = true;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          if(MusicManager.hybridMediaPlayer != null && fromUser){
            currentProgress[0] = progress;
            seekBar1.setProgress(progress);
          }
        }
      });
    }
  }
}
