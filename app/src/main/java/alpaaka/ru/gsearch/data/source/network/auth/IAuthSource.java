package alpaaka.ru.gsearch.data.source.network.auth;

import alpaaka.ru.gsearch.business.auth.IAuthInteractor;

public interface IAuthSource {

    void getAuthToken(IAuthInteractor.LoadTokenCallback callback, String code);
}
