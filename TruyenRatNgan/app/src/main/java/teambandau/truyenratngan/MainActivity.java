package teambandau.truyenratngan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import teambandau.truyenratngan.fragments.StoryListFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    displayStartScreen();
  }

  private void displayStartScreen() {
    //1. Create fragment
    StoryListFragment storyListFragment = new StoryListFragment();
    changeScreen(storyListFragment, false);
  }

  public void changeScreen(Fragment fragment, boolean addToBackStack) {
    //2. Create a transaction
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fl_main, fragment);
    if (addToBackStack)
      fragmentTransaction.addToBackStack(null);

    //3. Commit
    fragmentTransaction.commit();
  }
}
