package alpaaka.ru.gsearch.data.source.network.auth;

import alpaaka.ru.gsearch.data.model.AuthToken;

public interface IAuthSource {

    interface LoadTokenCallback {

        void onTokenLoaded(String token);

        void onDataNotAvailable(int code);

        void onConnectionError();
    }

    void getAuthToken(LoadTokenCallback callback, String code);
}
