package alpaaka.ru.gsearch.sharedpref;

public interface ISharedPreferencesRepository {

    void setToken(String token);
    void removeToken();
    String getToken();
}
