package khoattv.freemusic.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import khoattv.freemusic.R;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public class ScreenManager {
  public static void openFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID, boolean addToBackStack, boolean mainPlayer) {
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    if (mainPlayer) {
      fragmentTransaction.setCustomAnimations(R.anim.enter_from_bottom, 0, 0, R.anim.exit_to_bottom);
    } else {
      fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
    }
    fragmentTransaction.replace(layoutID, fragment);
    if (addToBackStack)
      fragmentTransaction.addToBackStack(null);

    fragmentTransaction.commit();
  }

  public static void backFragment(FragmentManager fragmentManager) {
    fragmentManager.popBackStackImmediate();
  }
}
