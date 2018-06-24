package alpaaka.ru.gsearch.data.source.network.auth;

import javax.inject.Inject;

import alpaaka.ru.gsearch.BuildConfig;
import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import alpaaka.ru.gsearch.data.model.AuthToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthSource implements IAuthSource{

    @Inject
    AuthService authService;

    @Inject
    AuthSource() {
    }

    @Override
    public void getAuthToken(final IAuthInteractor.LoadTokenCallback callback, String code) {
        Call<AuthToken> call = authService.getAccessToken(BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET, code);
        call.enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.isSuccessful()){
                    callback.onTokenLoaded(response.body().getAccessToken());
                } else {
                    callback.onDataNotAvailable(response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                callback.onConnectionError();
            }
        });
    }
}
