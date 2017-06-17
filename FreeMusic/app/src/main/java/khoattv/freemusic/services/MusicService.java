package khoattv.freemusic.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import khoattv.freemusic.R;
import khoattv.freemusic.managers.MusicManager;

/**
 * Created by KhoaBeo on 6/13/2017.
 */

public class MusicService extends Service {
  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (intent != null) {
      if (MusicManager.hybridMediaPlayer.isPlaying()) {
        MusicManager.hybridMediaPlayer.pause();
        MusicNotification.remoteViews.setImageViewResource(R.id.iv_play_nf, android.R.drawable.ic_media_play);
      } else {
        MusicManager.hybridMediaPlayer.play();
        MusicNotification.remoteViews.setImageViewResource(R.id.iv_play_nf, android.R.drawable.ic_media_pause);
      }
      MusicNotification.notificationManager.notify(MusicNotification.NOTIFICATION_ID, MusicNotification.builder.build());
    }
    return super.onStartCommand(intent, flags, startId);
  }
}
