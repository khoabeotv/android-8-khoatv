package teambandau.truyenratngan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import teambandau.truyenratngan.R;
import teambandau.truyenratngan.StoryApplication;
import teambandau.truyenratngan.databases.StoryDatabase;
import teambandau.truyenratngan.databases.models.Chapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment {

  private Chapter chapter;
  private TextView tvTitle;
  private WebView wvContent;
  private int chapterId;

  public ChapterFragment() {
    // Required empty public constructor
  }

  public ChapterFragment setChapterId(int chapterId) {
    this.chapterId = chapterId;
    return this;
  }

  public void getChapter() {
    StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
    chapter = storyDatabase.getChapter(chapterId);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_chapter, container, false);
    getChapter();
    findReferences(view);
    setUpUI();
    return view;
  }

  private void setUpUI() {
    tvTitle.setText(chapter.getTitle());
    wvContent.loadData(chapter.getContent(), "text/html; charset=utf-8", "utf-8");
  }

  private void findReferences(View view) {
    tvTitle = (TextView) view.findViewById(R.id.tv_chapter_title);
    wvContent = (WebView) view.findViewById(R.id.wv_content);
  }

}
