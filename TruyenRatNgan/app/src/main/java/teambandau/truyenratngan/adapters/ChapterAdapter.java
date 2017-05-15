package teambandau.truyenratngan.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import teambandau.truyenratngan.StoryApplication;
import teambandau.truyenratngan.databases.StoryDatabase;
import teambandau.truyenratngan.databases.models.Story;
import teambandau.truyenratngan.fragments.ChapterFragment;

/**
 * Created by KhoaBeo on 5/16/2017.
 */

public class ChapterAdapter extends FragmentPagerAdapter{

  private Story story;
  private StoryDatabase storyDatabase;
  private List<Integer> chapterIds;

  public ChapterAdapter(FragmentManager fm) {
    super(fm);
    storyDatabase = StoryApplication.getInstance().getStoryDatabase();
  }

  public ChapterAdapter setStory(Story story) {
    this.story = story;
    this.chapterIds = storyDatabase.loadChapterIds(story);
    return this;
  }

  @Override
  public Fragment getItem(int position) {
    return new ChapterFragment().setChapterId(chapterIds.get(position));
  }

  @Override
  public int getCount() {
    return storyDatabase.getChapterCount(story);
  }
}
