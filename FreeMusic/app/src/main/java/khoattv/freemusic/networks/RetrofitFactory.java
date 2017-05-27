package khoattv.freemusic.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KhoaBeo on 5/23/2017.
 */

public class RetrofitFactory {
  private static Retrofit retrofit;
  public static RetrofitFactory retrofitFactory = new RetrofitFactory();
  public static RetrofitFactory getInstance() {
    return retrofitFactory;
  }

  private RetrofitFactory() {
    retrofit = new Retrofit.Builder()
            .baseUrl("https://rss.itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public static <ServiceClass> ServiceClass createService(Class<ServiceClass> serviceClass) {
    return retrofit.create(serviceClass);
  }
}
