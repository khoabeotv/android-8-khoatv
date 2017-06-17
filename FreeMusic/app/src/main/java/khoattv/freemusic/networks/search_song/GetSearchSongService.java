package khoattv.freemusic.networks.search_song;

import khoattv.freemusic.networks.top_song.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public interface GetSearchSongService {
  @GET("http://api.mp3.zing.vn/api/mobile/search/song")
  Call<MainObject> getSearchSong(@Query("requestdata") String request);
}
