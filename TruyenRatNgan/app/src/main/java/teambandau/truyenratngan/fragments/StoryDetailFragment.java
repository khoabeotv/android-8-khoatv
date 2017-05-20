package teambandau.truyenratngan.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teambandau.truyenratngan.R;
import teambandau.truyenratngan.adapters.ChapterAdapter;
import teambandau.truyenratngan.databases.models.Story;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment extends Fragment {

  private ViewPager vpChapter;
  private Story story;


  public StoryDetailFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_story_detial, container, false);
    vpChapter = (ViewPager) view.findViewById(R.id.vp_chapter);
    setUpUI();
    return view;
  }

  public StoryDetailFragment setStory(Story story) {
    this.story = story;
    return this;
  }

  private void setUpUI() {
    vpChapter.setAdapter(new ChapterAdapter(getFragmentManager()).setStory(story));
    vpChapter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        story.setLastChapterNo(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
    if (story.getLastChapterNo() != -1) {
      vpChapter.setCurrentItem(story.getLastChapterNo());
    }
  }
}
