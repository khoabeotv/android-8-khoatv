package teambandau.truyenratngan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import teambandau.truyenratngan.databases.StoryDatabase;
import teambandau.truyenratngan.databases.models.Story;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
    List<Story> stories = storyDatabase.loadAllStories();
  }
}
