package teambandau.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import teambandau.truyenratngan.MainActivity;
import teambandau.truyenratngan.R;
import teambandau.truyenratngan.StoryApplication;
import teambandau.truyenratngan.adapters.StoryAdapter;
import teambandau.truyenratngan.databases.models.Story;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryListFragment extends Fragment implements AdapterView.OnItemClickListener {

  public static final List<Story> stories = StoryApplication.getInstance().getStoryDatabase().loadAllStories();
  private StoryAdapter storyAdapter;
  private ListView lvStory;

  public StoryListFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_story_list, container, false);
    lvStory = (ListView) view.findViewById(R.id.story_list);
    setUpUI();
    return view;
  }

  private void setUpUI() {
    storyAdapter = new StoryAdapter(stories);
    lvStory.setAdapter(storyAdapter);
    lvStory.setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    ((MainActivity) getActivity()).changeScreen(new StoryDetailFragment().setStory(stories.get(position)), true);
  }
}
