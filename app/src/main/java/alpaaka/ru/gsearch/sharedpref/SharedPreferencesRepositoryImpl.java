package alpaaka.ru.gsearch.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferencesRepositoryImpl implements ISharedPreferencesRepository{

    private static final String TOKEN = "alpaaka.ru.gsearch.token";

    private SharedPreferences prefs;

    @Inject
    public SharedPreferencesRepositoryImpl(Context context) {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void setToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    @Override
    public void removeToken() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN, null);
        editor.apply();
    }

    @Override
    public String getToken() {
        return prefs.getString(TOKEN, "");
    }
}
