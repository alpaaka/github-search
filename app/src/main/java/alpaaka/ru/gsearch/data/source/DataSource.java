package alpaaka.ru.gsearch.data.source;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import alpaaka.ru.gsearch.data.source.network.auth.AuthSource;
import alpaaka.ru.gsearch.data.source.network.auth.IAuthSource;
import alpaaka.ru.gsearch.sharedpref.ISharedPreferencesRepository;

@Singleton
public class DataSource implements IAuthSource {

    private Context context;
    private AuthSource authSource;
    @Inject
    ISharedPreferencesRepository sharedPreferences;


    @Inject
    DataSource(Context context, AuthSource authSource) {
        this.context = context;
        this.authSource = authSource;
    }

    @Override
    public void getAuthToken(final IAuthInteractor.LoadTokenCallback callback, String code) {
        if (sharedPreferences.getToken().isEmpty()) {
            authSource.getAuthToken(new IAuthInteractor.LoadTokenCallback() {
                @Override
                public void onTokenLoaded(String token) {
                    sharedPreferences.setToken(token);
                    callback.onTokenLoaded(token);
                }

                @Override
                public void onDataNotAvailable(int code) {
                    callback.onDataNotAvailable(code);
                }

                @Override
                public void onConnectionError() {
                    callback.onConnectionError();
                }
            }, code);
        } else {
            callback.onTokenLoaded(sharedPreferences.getToken());
        }
    }
}
