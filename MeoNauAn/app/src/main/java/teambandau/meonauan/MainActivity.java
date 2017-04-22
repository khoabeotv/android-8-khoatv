package teambandau.meonauan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import adapters.TipsAdapter;
import databases.models.Tips;

public class MainActivity extends AppCompatActivity {

  private ListView lvTips;
  private TipsAdapter tipsAdapter;
  private List<Tips> tipsList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    lvTips = (ListView) findViewById(R.id.lv_tips);
    tipsList = TipsApplication.getInstance().getTipsDatabase().loadAllTips();
    tipsAdapter = new TipsAdapter(this, tipsList);
    lvTips.setAdapter(tipsAdapter);
  }
}
