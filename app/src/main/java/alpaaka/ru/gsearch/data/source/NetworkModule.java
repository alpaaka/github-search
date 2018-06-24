package alpaaka.ru.gsearch.data.source;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import alpaaka.ru.gsearch.BuildConfig;
import alpaaka.ru.gsearch.data.source.network.api.ApiService;
import alpaaka.ru.gsearch.data.source.network.auth.AuthService;
import alpaaka.ru.gsearch.sharedpref.ISharedPreferencesRepository;
import alpaaka.ru.gsearch.utils.AppExecutor;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    OkHttpClient provideOkHttpClient(final ISharedPreferencesRepository spRep) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "token " + spRep.getToken())
                        .build();
                return chain.proceed(request);
            }
        });
        return builder.build();
    }

    @Provides
    @Singleton
    @Named("API")
    Retrofit provideRetrofitApi(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    ApiService provideApiService(@Named("API") Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    static AppExecutor provideAppExecutors() {
        return new AppExecutor(new AppExecutor.MainThreadExecutor());
    }
}
