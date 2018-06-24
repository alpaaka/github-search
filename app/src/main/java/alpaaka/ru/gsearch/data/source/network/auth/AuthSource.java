package alpaaka.ru.gsearch.data.source.network.auth;

import javax.inject.Inject;

import alpaaka.ru.gsearch.BuildConfig;
import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import alpaaka.ru.gsearch.data.model.AuthToken;
import alpaaka.ru.gsearch.utils.AppExecutor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthSource implements IAuthSource {

    @Inject
    AuthService authService;
    private AppExecutor executor;

    @Inject
    AuthSource(AppExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void getAuthToken(final IAuthInteractor.LoadTokenCallback callback, String code) {
        Call<AuthToken> call = authService.getAccessToken(BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET, code);
        call.enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, final Response<AuthToken> response) {
                if (response.isSuccessful()) {
                    executor.getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onTokenLoaded(response.body().getAccessToken());
                        }
                    });
                } else {
                    executor.getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onDataNotAvailable(response.code());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                executor.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onConnectionError();
                    }
                });
            }
        });
    }
}
