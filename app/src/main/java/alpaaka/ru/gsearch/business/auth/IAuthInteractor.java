package alpaaka.ru.gsearch.business.auth;

public interface IAuthInteractor {

    interface LoadTokenCallback {

        void onTokenLoaded(String token);

        void onDataNotAvailable(int code);

        void onConnectionError();
    }

    void loadToken(LoadTokenCallback callback, String textWithCode);
}
