package teambandau.randomquote.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import teambandau.randomquote.R;
import teambandau.randomquote.networks.ImageLoader;
import teambandau.randomquote.networks.QuoteLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

  private TextView tvQuote;
  private ImageView imageView;
  private ProgressBar progressBar;

  public QuoteFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_quote, container, false);
    tvQuote = (TextView) view.findViewById(R.id.tv_quote);
    imageView = (ImageView) view.findViewById(R.id.iv_background);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    new ImageLoader().setView(imageView, progressBar).execute();
    new QuoteLoader(tvQuote).execute();
    return view;
  }

}
