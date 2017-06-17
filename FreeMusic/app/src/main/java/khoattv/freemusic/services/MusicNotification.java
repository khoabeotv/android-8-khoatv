package khoattv.freemusic.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import khoattv.freemusic.R;
import khoattv.freemusic.activities.MainActivity;
import khoattv.freemusic.databases.model.TopSongsModel;

/**
 * Created by KhoaBeo on 6/13/2017.
 */

public class MusicNotification {

  public static NotificationCompat.Builder builder;
  public static NotificationManager notificationManager;
  public static RemoteViews remoteViews;
  public static final int NOTIFICATION_ID = 1;

  public static void setupNotification(Context context, TopSongsModel topSongsModel) {
    Intent intent = new Intent(context, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

    remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
    remoteViews.setTextViewText(R.id.tv_song_name_nf, topSongsModel.getName());
    remoteViews.setTextViewText(R.id.tv_artist_nf, topSongsModel.getArtist());
    remoteViews.setImageViewResource(R.id.iv_play_nf, android.R.drawable.ic_media_pause);

    setOnPlayPauseNotification(context);

    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    builder = new NotificationCompat.Builder(context)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_music_note_black_24dp)
            .setContent(remoteViews)
            .setContentIntent(pendingIntent);

    notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(NOTIFICATION_ID, builder.build());
  }

  public static void setOnPlayPauseNotification(Context context) {
    Intent intent = new Intent(context, MusicService.class);
    PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    remoteViews.setOnClickPendingIntent(R.id.iv_play_nf, pendingIntent);
  }

  public static void updateNotification(boolean playing) {
    if (playing) {
      remoteViews.setImageViewResource(R.id.iv_play_nf, android.R.drawable.ic_media_pause);
    } else {
      remoteViews.setImageViewResource(R.id.iv_play_nf, android.R.drawable.ic_media_play);
    }
    notificationManager.notify(NOTIFICATION_ID, builder.build());
  }

}
