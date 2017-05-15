package teambandau.truyenratngan;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import teambandau.truyenratngan.adapters.ChapterAdapter;
import teambandau.truyenratngan.databases.models.Story;

public class StoryDetailActivity extends AppCompatActivity {

  private ViewPager vpChapter;
  private Story story;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_story_detail);
    vpChapter = (ViewPager) findViewById(R.id.vp_chapter);
    getStory();
    setUpUI();
  }

  private void setUpUI() {
    vpChapter.setAdapter(new ChapterAdapter(getSupportFragmentManager()).setStory(story));
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


  public void getStory() {
    Intent intent = getIntent();
    story = MainActivity.stories.get(intent.getIntExtra("StoryPosition", 0));
  }
}
