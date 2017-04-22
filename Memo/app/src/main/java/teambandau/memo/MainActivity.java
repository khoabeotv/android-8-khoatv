package teambandau.memo;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.MainAdapter;
import memo.Memo;

public class MainActivity extends AppCompatActivity {

  private List<Memo> memos = new ArrayList<>();
  private ListView listView;
  private MainAdapter mainAdapter;
  private FloatingActionButton addNewBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    listView = (ListView) findViewById(R.id.memos_lv);
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    memos.add(new Memo("aaaa","bbb", 1));
    mainAdapter = new MainAdapter(this, memos);
    listView.setAdapter(mainAdapter);

//    addNewBtn = (FloatingActionButton) findViewById(R.id.fab);
//    addNewBtn.setPadding(0, 0, 0, 0);
  }
}
