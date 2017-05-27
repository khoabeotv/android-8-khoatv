package khoattv.freemusic.networks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by KhoaBeo on 5/23/2017.
 */

public interface MusicTypesService {
  @GET("data/media-types.json")
  Call<List<MediaType>> getMediaTypes();
}
