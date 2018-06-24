package alpaaka.ru.gsearch.data.source;

import javax.inject.Singleton;

import alpaaka.ru.gsearch.BuildConfig;
import alpaaka.ru.gsearch.data.source.network.auth.AuthService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideRetrofitAuth() {
        return new Retrofit.Builder().baseUrl(BuildConfig.OAUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
