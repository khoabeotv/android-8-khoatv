package com.example.khoabeo.tidypiano;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = "MainActivity";

  private List<ImageView> ivBlackKeys = new ArrayList<>();
  private List<ImageView> ivWhiteKeys = new ArrayList<>();
  private List<PressedKeyInfo> pressedKeyInfos;

  private class PressedKeyInfo {
    private ImageView ivKey;
    private int pointerId;

    public PressedKeyInfo(ImageView ivKey, int pointerId) {
      this.ivKey = ivKey;
      this.pointerId = pointerId;
    }

    public ImageView getIvKey() {
      return ivKey;
    }

    public int getPointerId() {
      return pointerId;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    for (int i = 0; i < 5; i++) {
      String imageId = "iv_black_key_" + (i + 1);
      int resID = getResources().getIdentifier(imageId, "id", getPackageName());
      ivBlackKeys.add((ImageView) findViewById(resID));
    }

    for (int i = 0; i < 7; i++) {
      String imageId = "iv_white_key_" + (i + 1);
      int resID = getResources().getIdentifier(imageId, "id", getPackageName());
      ivWhiteKeys.add((ImageView) findViewById(resID));
    }

    pressedKeyInfos = new ArrayList<>();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int lastPointerIndex = MotionEventCompat.getActionIndex(event);
    int pointerAction = event.getActionMasked();

    if (pointerAction == MotionEvent.ACTION_MOVE
            || pointerAction == MotionEvent.ACTION_DOWN
            || pointerAction == MotionEvent.ACTION_POINTER_DOWN) {

      for (int pIndex = 0; pIndex < event.getPointerCount(); pIndex++) {
        int pId = event.getPointerId(pIndex);
        float pX = event.getX(pIndex);
        float pY = event.getY(pIndex);

        for (int i = 0; i < pressedKeyInfos.size(); i++) {
          PressedKeyInfo pressedKeyInfo = pressedKeyInfos.get(i);
          if (!isInside(pX, pY, pressedKeyInfo.getIvKey())) {
            if (pressedKeyInfo.getPointerId() == pId) {
              pressedKeyInfos.remove(i);
              setPressed(pressedKeyInfo.getIvKey(), false);
            }
          }
        }

        ImageView pressedKey = findPressedKey(pX, pY);
        if (pressedKey != null) {
          if (!containsKeyInfoWith(pressedKey)) {
            pressedKeyInfos.add(new PressedKeyInfo(pressedKey, pId));
            setPressed(pressedKey, true);
          }
        }
      }
    } else if (pointerAction == MotionEvent.ACTION_POINTER_UP
            || pointerAction == MotionEvent.ACTION_UP) {
      ImageView pressedKey = findPressedKey(event.getX(lastPointerIndex), event.getY(lastPointerIndex));
      for (int i = 0; i < pressedKeyInfos.size(); i++) {
        if (pressedKeyInfos.get(i).getIvKey() == pressedKey)
          pressedKeyInfos.remove(i);
          setPressed(pressedKey, false);
      }
    }
    return super.onTouchEvent(event);
  }

  private boolean containsKeyInfoWith(ImageView ivKey) {
    for (PressedKeyInfo pressedKeyInfo : pressedKeyInfos) {
      if (pressedKeyInfo.getIvKey() == ivKey)
        return true;
    }
    return false;
  }

  private void setPressed(ImageView ivKey, boolean isPressed) {
    if (isPressed) {
      if (ivBlackKeys.contains(ivKey)) {
        ivKey.setImageResource(R.drawable.pressed_black_key);
      } else {
        ivKey.setImageResource(R.drawable.pressed_white_key);
      }
    } else {
      if (ivBlackKeys.contains(ivKey)) {
        ivKey.setImageResource(R.drawable.default_black_key);
      } else {
        ivKey.setImageResource(R.drawable.default_white_key);
      }
    }
  }


  private ImageView findPressedKey(float pointerX, float pointerY) {

    for (int i = 0; i < ivBlackKeys.size(); i++) {
      if (isInside(pointerX, pointerY, ivBlackKeys.get(i))) {
        return ivBlackKeys.get(i);
      }
    }

    for (int i = 0; i < ivWhiteKeys.size(); i++) {
      if (isInside(pointerX, pointerY, ivWhiteKeys.get(i))) {
        return ivWhiteKeys.get(i);
      }
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

}
