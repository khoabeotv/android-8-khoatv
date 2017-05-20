package teambandau.randomquote.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import teambandau.randomquote.R;
import teambandau.randomquote.fragments.QuoteFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    changeFragment(new QuoteFragment(), false);
  }

  public void changeFragment(Fragment fragment, boolean addToBackStack) {
    if (addToBackStack) {
      getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();
    } else {
      getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
    }
  }
}
