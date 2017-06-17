package khoattv.freemusic.networks.top_song;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by KhoaBeo on 5/30/2017.
 */

public interface GetTopSongsService {
  @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={idmusictype}/explicit=true/json")
  Call<MainObject> getTopSongs(@Path("idmusictype") String idMusicType);
}
