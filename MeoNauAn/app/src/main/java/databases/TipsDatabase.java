package databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import databases.models.Tips;

/**
 * Created by KhoaBeo on 4/22/2017.
 */

public class TipsDatabase extends SQLiteAssetHelper{

  private static final String DATABASE_NAME = "db.db";
  private static final int DATABASE_VERSION = 1;

  private static final String TIPS_TITLE = "title";
  private static final String TIPS_IMAGE = "image";
  private static final String TIPS_DES = "des";
  private static final String TIPS_IS_FAVORITE = "is_favorite";

  private static final String[] TIPS_ALL_COLUMNS = new String[]{
          TIPS_TITLE,
          TIPS_IMAGE,
          TIPS_DES,
          TIPS_IS_FAVORITE
  };

  public TipsDatabase(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public List<Tips> loadAllTips() {
    List<Tips> tipsList = new ArrayList<>();

    // Get readable database
    SQLiteDatabase db = getReadableDatabase();

    // Query => cursor
    Cursor cursor = db.query("main_table", TIPS_ALL_COLUMNS, null, null, null, null, null);

    // Go thorough rows
    while(cursor.moveToNext()) {
      String title = cursor.getString(cursor.getColumnIndex(TIPS_TITLE));
      String image = cursor.getString(cursor.getColumnIndex(TIPS_IMAGE));
      String des = cursor.getString(cursor.getColumnIndex(TIPS_DES));
      boolean isFavorite = cursor.getInt(cursor.getColumnIndex(TIPS_IS_FAVORITE)) != 0;

      Tips Tips = new Tips(title, image, des, isFavorite);
      tipsList.add(Tips);
    }

    cursor.close();
    db.close();

    return tipsList;
  }
}
