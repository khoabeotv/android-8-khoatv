package teambandau.truyenratngan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import teambandau.truyenratngan.adapters.StoryAdapter;
import teambandau.truyenratngan.databases.StoryDatabase;
import teambandau.truyenratngan.databases.models.Story;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  public static final List<Story> stories = StoryApplication.getInstance().getStoryDatabase().loadAllStories();
  private StoryAdapter storyAdapter;
  private ListView lvStory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    lvStory = (ListView) findViewById(R.id.story_list);

//    loadData();
    setUpUI();
  }

  private void setUpUI() {
    storyAdapter = new StoryAdapter(stories);
    lvStory.setAdapter(storyAdapter);
    lvStory.setOnItemClickListener(this);
  }

//  private void loadData() {
//    StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
//    stories = storyDatabase.loadAllStories();
//  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent intent = new Intent(MainActivity.this, StoryDetailActivity.class);
//    intent.putExtra("Story", stories.get(position));
    intent.putExtra("StoryPosition", position);
    startActivity(intent);
  }
}
