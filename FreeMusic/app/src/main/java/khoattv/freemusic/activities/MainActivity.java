package khoattv.freemusic.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import khoattv.freemusic.R;
import khoattv.freemusic.networks.MusicType;
import khoattv.freemusic.networks.MediaType;
import khoattv.freemusic.networks.MusicTypesService;
import khoattv.freemusic.networks.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MusicTypesService musicTypesService = RetrofitFactory.getInstance().createService(MusicTypesService.class);
    musicTypesService.getMediaTypes().enqueue(new Callback<List<MediaType>>() {
      @Override
      public void onResponse(Call<List<MediaType>> call, Response<List<MediaType>> response) {
        MediaType mediaType = response.body().get(3);
        List<MusicType> musicTypes = mediaType.getSubgenres();
        for (MusicType musicType: musicTypes) {
          Log.d("MainActivity", musicType.getTranslationKey() + "--" + musicType.getId());
        }

      }

      @Override
      public void onFailure(Call<List<MediaType>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "aaaaa", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
