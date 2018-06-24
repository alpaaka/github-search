package alpaaka.ru.gsearch.data.source;

import android.content.Context;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import alpaaka.ru.gsearch.business.search.ISearchInteractor;
import alpaaka.ru.gsearch.data.model.Repository;
import alpaaka.ru.gsearch.data.source.network.api.ApiSource;
import alpaaka.ru.gsearch.data.source.network.api.IApiSource;
import alpaaka.ru.gsearch.data.source.network.auth.AuthSource;
import alpaaka.ru.gsearch.data.source.network.auth.IAuthSource;
import alpaaka.ru.gsearch.sharedpref.ISharedPreferencesRepository;

@Singleton
public class DataSource implements IAuthSource, IApiSource {

    private Context context;
    private AuthSource authSource;
    private ApiSource apiSource;

    private ArrayList<Repository> repositories;
    @Inject
    ISharedPreferencesRepository sharedPreferences;

    @Inject
    DataSource(Context context, AuthSource authSource, ApiSource apiSource) {
        this.context = context;
        this.authSource = authSource;
        this.apiSource = apiSource;
        this.repositories = new ArrayList<>();
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
        apiSource.findRep(callback, q, 0);
    }
}
