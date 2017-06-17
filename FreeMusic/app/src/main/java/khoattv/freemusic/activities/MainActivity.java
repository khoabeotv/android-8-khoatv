package khoattv.freemusic.activities;

import android.animation.Animator;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import khoattv.freemusic.R;
import khoattv.freemusic.adapters.PagerAdapter;
import khoattv.freemusic.databases.model.TopSongsModel;
import khoattv.freemusic.events.LoadUiPlayer;
import khoattv.freemusic.events.OnClickMiniPlayer;
import khoattv.freemusic.events.OnClickTopSong;
import khoattv.freemusic.fragments.MainPlayerFragment;
import khoattv.freemusic.managers.MusicManager;
import khoattv.freemusic.managers.ScreenManager;
import khoattv.freemusic.networks.music_type.MusicType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  @BindView(R.id.toolbar_main)
  Toolbar toolbar;
  @BindView(R.id.tab_layout)
  TabLayout tabLayout;
  @BindView(R.id.view_pager)
  ViewPager viewPager;
  @BindView(R.id.sb_mini)
  SeekBar seekBar;
  @BindView(R.id.rl_mini)
  RelativeLayout rlMini;
  @BindView(R.id.tv_artist_mini)
  TextView tvMiniAritst;
  @BindView(R.id.tv_song_name_mini)
  TextView tvMiniName;
  @BindView(R.id.fab_play)
  FloatingActionButton fabPlay;
  @BindView(R.id.iv_mini)
  ImageView ivMini;
  private TopSongsModel currentSong;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    EventBus.getDefault().register(this);
    setupUI();
  }

  private void setupUI() {
    ButterKnife.bind(this);
    toolbar.setTitle("Free Music");
    toolbar.setTitleTextColor(getResources().getColor(R.color.primary_text));

    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
    tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));

    tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.icon_selected), PorterDuff.Mode.SRC_IN);
    tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.icon_unselected), PorterDuff.Mode.SRC_IN);
    tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.icon_unselected), PorterDuff.Mode.SRC_IN);

    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), 3);
    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        tabLayout.getTabAt(tab.getPosition()).getIcon().setColorFilter(getResources().getColor(R.color.icon_selected), PorterDuff.Mode.SRC_IN);
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
        tabLayout.getTabAt(tab.getPosition()).getIcon().setColorFilter(getResources().getColor(R.color.icon_unselected), PorterDuff.Mode.SRC_IN);
      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
    rlMini.setVisibility(View.GONE);
    rlMini.setOnClickListener(this);
    fabPlay.setOnClickListener(this);
  }

  @Subscribe
  public void onTopSongClick(OnClickTopSong onClickTopSong) {
    TopSongsModel topSongsModel = onClickTopSong.getTopSongsModel();
    if (topSongsModel != null) {
      MusicManager.loadSearchSong(topSongsModel, this);
    }
  }

  @Subscribe
  public void loadUIPlayer(LoadUiPlayer loadUiPlayer) {
    TopSongsModel topSongsModel = loadUiPlayer.getTopSongsModel();
    currentSong = topSongsModel;
    if (topSongsModel != null) {
      if (rlMini.getVisibility() == View.GONE) {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(150);
        rlMini.startAnimation(animation);
      }
      rlMini.setVisibility(View.VISIBLE);
      Picasso.with(this).load(topSongsModel.getLargeIm()).transform(new CropCircleTransformation()).into(ivMini);
      tvMiniAritst.setText(topSongsModel.getArtist());
      tvMiniName.setText(topSongsModel.getName());
    }
    MusicManager.updateSongRealTime(fabPlay, seekBar, null, null, null);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.fab_play) {
      MusicManager.playOrPause();
    } else {
      EventBus.getDefault().postSticky(new OnClickMiniPlayer(currentSong));
      ScreenManager.openFragment(getSupportFragmentManager(), new MainPlayerFragment(), R.id.rl_activity_main, true, true);
    }
  }

  @Override
  public void onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
      super.onBackPressed();
    } else {
      moveTaskToBack(true);
    }
  }
}
