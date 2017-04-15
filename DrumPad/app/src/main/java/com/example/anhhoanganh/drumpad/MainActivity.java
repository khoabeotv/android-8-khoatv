package com.example.anhhoanganh.drumpad;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = "MainActivity";

  private List<KeyInfo> keyInfos = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    for (int i = 0; i < 12; i++) {
      String imageId = "im" + (i + 1);
      int resID = getResources().getIdentifier(imageId, "id", getPackageName());
      ImageView key = ((ImageView) findViewById(resID));
      if (i < 3) {
        new KeyInfo(key, KeyColor.BLUE, null);
      } else if (i < 6) {
        new KeyInfo(key, KeyColor.GREEN, null);
      } else if (i < 9) {
        new KeyInfo(key, KeyColor.ORANGE, null);
      } else if (i < 12) {
        new KeyInfo(key, KeyColor.PURPLE, null);
      }
    }
  }

  private enum KeyColor {
    BLUE, GREEN, ORANGE, PURPLE;
  }

  private class KeyInfo {
    private ImageView ivKey;
    private Integer pointerId;
    private KeyColor keyColor;
    private MediaPlayer mediaPlayer;

    public KeyInfo(ImageView ivKey, KeyColor keyColor, Integer pointerId) {
      this.ivKey = ivKey;
      this.pointerId = pointerId;
      this.keyColor = keyColor;
      this.mediaPlayer = null;
      keyInfos.add(this);
    }

    public ImageView getIvKey() {
      return ivKey;
    }

    public Integer getPointerId() {
      return pointerId;
    }

    public KeyColor getKeyColor() {
      return keyColor;
    }

    public void setPointerId(Integer pointerId) {
      this.pointerId = pointerId;
    }

    public MediaPlayer getMediaPlayer() {
      return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
      this.mediaPlayer = mediaPlayer;
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int lastPointerIndex = MotionEventCompat.getActionIndex(event);
    int pointerAction = event.getActionMasked();

    if (pointerAction == MotionEvent.ACTION_MOVE) {
      for (int pIndex = 0; pIndex < event.getPointerCount(); pIndex++) {
        int pId = event.getPointerId(pIndex);
        float pX = event.getX(pIndex);
        float pY = event.getY(pIndex);

        for (int i = 0; i < keyInfos.size(); i++) {
          KeyInfo keyInfo = keyInfos.get(i);
          if (keyInfo.getPointerId() != null) {
            if (!isInside(pX, pY, keyInfo.getIvKey())) {
              if (keyInfo.getPointerId() == pId) {
                keyInfo.setPointerId(null);
                setPressed(keyInfo, false);
              }
            }
          }
        }
        pressing(pX, pY, pId);
      }
    } else if (pointerAction == MotionEvent.ACTION_DOWN
            || pointerAction == MotionEvent.ACTION_POINTER_DOWN) {
      pressing(event.getX(lastPointerIndex), event.getY(lastPointerIndex), event.getPointerId(lastPointerIndex));
    } else if (pointerAction == MotionEvent.ACTION_POINTER_UP
            || pointerAction == MotionEvent.ACTION_UP) {
      KeyInfo keyInfo = findPressedKeyInfo(event.getX(lastPointerIndex), event.getY(lastPointerIndex));
      if (keyInfo != null) {
        keyInfo.setPointerId(null);
        setPressed(keyInfo, false);
      }
    }

    return super.onTouchEvent(event);
  }

  private void pressing(float pX, float pY, int pId) {
    KeyInfo pressedKeyInfo = findPressedKeyInfo(pX, pY);
    if (pressedKeyInfo != null) {
      setPressed(pressedKeyInfo, true);
      if (pressedKeyInfo.getPointerId() == null) {
        pressedKeyInfo.setPointerId(pId);

        if (pressedKeyInfo.getMediaPlayer() != null) {
          pressedKeyInfo.getMediaPlayer().release();
        }

        MediaPlayer player = new MediaPlayer();
        pressedKeyInfo.setMediaPlayer(player);
        try {
          AssetFileDescriptor afd = getAssets().openFd("d" + keyInfos.indexOf(pressedKeyInfo) + ".wav");
          player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
          player.prepare();
          player.start();
          final MediaPlayer finalPlayer = player;
          player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
              finalPlayer.release();
            }
          });
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private KeyInfo findPressedKeyInfo(float pX, float pY) {
    for (int i = 0; i < keyInfos.size(); i++) {
      KeyInfo keyInfo = keyInfos.get(i);
      if (isInside(pX, pY, keyInfo.getIvKey()))
        return keyInfo;
    }
    return null;
  }

  private boolean isInside(float pointerX, float pointerY, View view) {
    int[] location = new int[2];
    view.getLocationOnScreen(location);
    int left = location[0];
    int top = location[1];
    int right = left + view.getWidth();
    int bottom = top + view.getHeight();
    return (pointerX > left && pointerX < right)
            && (pointerY > top && pointerY < bottom);
  }

  private void setPressed(KeyInfo keyInfo, boolean isPressed) {
    ImageView imageView = keyInfo.getIvKey();
    if (isPressed) {
      switch (keyInfo.getKeyColor()) {
        case BLUE:
          imageView.setImageResource(R.drawable.pad_blue_highlighted);
          break;
        case GREEN:
          imageView.setImageResource(R.drawable.pad_green_highlighted);
          break;
        case ORANGE:
          imageView.setImageResource(R.drawable.pad_orange_highlighted);
          break;
        case PURPLE:
          imageView.setImageResource(R.drawable.pad_purple_highlighted);
          break;
      }
    } else {
      switch (keyInfo.getKeyColor()) {
        case BLUE:
          imageView.setImageResource(R.drawable.pad_blue);
          break;
        case GREEN:
          imageView.setImageResource(R.drawable.pad_green);
          break;
        case ORANGE:
          imageView.setImageResource(R.drawable.pad_orange);
          break;
        case PURPLE:
          imageView.setImageResource(R.drawable.pad_purple);
          break;
      }
    }
  }
}
