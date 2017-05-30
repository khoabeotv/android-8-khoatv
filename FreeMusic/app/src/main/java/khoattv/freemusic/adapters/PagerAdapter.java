package khoattv.freemusic.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import khoattv.freemusic.fragments.DownloadFragment;
import khoattv.freemusic.fragments.FavoriteFragment;
import khoattv.freemusic.fragments.MusicTypesFragment;

/**
 * Created by KhoaBeo on 5/28/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

  private int countTabs;

  public PagerAdapter(FragmentManager fm, int countTabs) {
    super(fm);
    this.countTabs = countTabs;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new MusicTypesFragment();
      case 1:
        return new FavoriteFragment();
      case 2:
        return new DownloadFragment();
      default:
        return new MusicTypesFragment();
    }
  }

  @Override
  public int getCount() {
    return countTabs;
  }
}
