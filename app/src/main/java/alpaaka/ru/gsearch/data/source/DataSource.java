package alpaaka.ru.gsearch.data.source;

import javax.inject.Inject;
import javax.inject.Singleton;

import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import alpaaka.ru.gsearch.business.search.ISearchInteractor;
import alpaaka.ru.gsearch.data.source.network.api.ApiSource;
import alpaaka.ru.gsearch.data.source.network.api.IApiSource;
import alpaaka.ru.gsearch.data.source.network.auth.AuthSource;
import alpaaka.ru.gsearch.data.source.network.auth.IAuthSource;
import alpaaka.ru.gsearch.sharedpref.ISharedPreferencesRepository;

@Singleton
public class DataSource implements IAuthSource, IApiSource {

    private AuthSource authSource;
    private ApiSource apiSource;

    @Inject
    ISharedPreferencesRepository sharedPreferences;

    @Inject
    DataSource(AuthSource authSource, ApiSource apiSource) {
        this.authSource = authSource;
        this.apiSource = apiSource;
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

    @Override
    public void findRep(ISearchInteractor.LoadRepositoriesCallback callback, String q, int page) {
        apiSource.findRep(callback, q, page);
    }
}
