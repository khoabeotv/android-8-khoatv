package khoattv.retrofithw;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import khoattv.retrofithw.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    displayStartScreen();
  }

  private void displayStartScreen() {
    LoginFragment storyListFragment = new LoginFragment();
    changeScreen(storyListFragment, false);
  }

  public void changeScreen(Fragment fragment, boolean addToBackStack) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fl_main, fragment);
    if (addToBackStack)
      fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }
}
