package teambandau.truyenratngan.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import teambandau.truyenratngan.databases.models.Chapter;
import teambandau.truyenratngan.databases.models.Story;

/**
 * Created by KhoaBeo on 4/18/2017.
 */

public class StoryDatabase extends SQLiteAssetHelper {

  private static final String DATABASE_NAME = "story.db";
  private static final int DATABASE_VERSION = 1;

  private static final String STORY_ID = "id";
  private static final String STORY_TITLE = "title";
  private static final String STORY_IMAGE = "image";
  private static final String STORY_DES = "description";
  private static final String STORY_IS_FAVORITE = "is_favorite";
  private static final String STORY_LAST_CHAPTER_NO = "last_chapter_no";

  private static final String CHAPTER_ID = "id";
  private static final String CHAPTER_TITLE = "title";
  private static final String CHAPTER_CONTENT = "content";

  private static final String[] STORY_ALL_COLUMNS = new String[]{
          STORY_ID,
          STORY_TITLE,
          STORY_IMAGE,
          STORY_DES,
          STORY_IS_FAVORITE,
          STORY_LAST_CHAPTER_NO
  };

  private static final String[] CHAPTER_ALL_COLUMS = new String[]{
          CHAPTER_ID,
          CHAPTER_TITLE,
          CHAPTER_CONTENT
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
    while (cursor.moveToNext()) {
      int id = cursor.getInt(cursor.getColumnIndex(STORY_ID));
      String title = cursor.getString(cursor.getColumnIndex(STORY_TITLE));
      String image = cursor.getString(cursor.getColumnIndex(STORY_IMAGE));
      String des = cursor.getString(cursor.getColumnIndex(STORY_DES));
      int lastChapterNo = cursor.getInt(cursor.getColumnIndex(STORY_LAST_CHAPTER_NO));
      boolean isFavorite = cursor.getInt(cursor.getColumnIndex(STORY_IS_FAVORITE)) != 0;

      Story story = new Story(id, title, image, des, isFavorite, lastChapterNo);
      stories.add(story);
    }

    cursor.close();
    db.close();

    return stories;
  }

  public void updateLastChapterNo(int storyId, int lastChapterNo) {
    SQLiteDatabase db = getReadableDatabase();

    ContentValues contentValues = new ContentValues();
    contentValues.put(STORY_LAST_CHAPTER_NO, lastChapterNo);

    db.update("tbl_story", contentValues, "id=" + storyId, null);
  }

  public int getChapterCount(Story story) {
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM tbl_chapter where novel_id=?", new String[]{
            String.valueOf(story.getId())
    });

    cursor.moveToFirst();
    int chapterCount = cursor.getInt(0);
    cursor.close();
    return chapterCount;
  }

  public List<Integer> loadChapterIds(Story story) {
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT id FROM tbl_chapter where novel_id=?", new String[]{
            String.valueOf(story.getId())
    });
    List<Integer> chapterIds = new ArrayList<>();
    while (cursor.moveToNext()) {
      chapterIds.add(cursor.getInt(0));
    }
    cursor.close();
    return chapterIds;
  }

  public Chapter getChapter(int chapterId) {
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.query("tbl_chapter", CHAPTER_ALL_COLUMS, "id=?",
            new String[]{String.valueOf(chapterId)},
            null, null, null);
    cursor.moveToFirst();
    String title = cursor.getString(cursor.getColumnIndex(CHAPTER_TITLE));
    String content = cursor.getString(cursor.getColumnIndex(CHAPTER_CONTENT));

    cursor.close();
    return new Chapter(chapterId, title, content);
  }
}
