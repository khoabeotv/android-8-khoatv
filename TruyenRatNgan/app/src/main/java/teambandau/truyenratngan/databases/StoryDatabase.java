package teambandau.truyenratngan.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import teambandau.truyenratngan.databases.models.Story;

/**
 * Created by KhoaBeo on 4/18/2017.
 */

public class StoryDatabase extends SQLiteAssetHelper {

  private static final String DATABASE_NAME = "story.db";
  private static final int DATABASE_VERSION = 1;

  private static final String STORY_TITLE = "title";
  private static final String STORY_IMAGE = "image";
  private static final String STORY_DES = "des";
  private static final String STORY_IS_FAVORITE = "is_favorite";

  private static final String[] STORY_ALL_COLUMNS = new String[]{
          STORY_TITLE,
          STORY_IMAGE,
          STORY_DES,
          STORY_IS_FAVORITE
  };

  public StoryDatabase(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public List<Story> loadAllStories() {
    List<Story> stories = new ArrayList<>();

    // Get readable database
    SQLiteDatabase db = getReadableDatabase();

    // Query => cursor
    Cursor cursor = db.query("tbl_story", STORY_ALL_COLUMNS, null, null, null, null, null);

    // Go thorough rows
    while(cursor.moveToNext()) {
      String title = cursor.getString(cursor.getColumnIndex(STORY_TITLE));
      String image = cursor.getString(cursor.getColumnIndex(STORY_IMAGE));
      String des = cursor.getString(cursor.getColumnIndex(STORY_DES));
      boolean isFavorite = cursor.getInt(cursor.getColumnIndex(STORY_IS_FAVORITE)) != 0;

      Story story = new Story(title, image, des, isFavorite);
      stories.add(story);
    }

    cursor.close();
    db.close();

    return stories;
  }
}
