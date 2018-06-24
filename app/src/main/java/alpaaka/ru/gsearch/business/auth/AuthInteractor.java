package alpaaka.ru.gsearch.business.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import alpaaka.ru.gsearch.data.model.AuthToken;
import alpaaka.ru.gsearch.data.source.DataSource;
import alpaaka.ru.gsearch.data.source.network.auth.IAuthSource;

public class AuthInteractor implements IAuthInteractor{

    @Inject
    DataSource dataSource;

    @Inject
    public AuthInteractor() {

    }

    @Override
    public void loadToken(String textWithCode) {
        dataSource.getAuthToken(new IAuthSource.LoadTokenCallback() {
            @Override
            public void onTokenLoaded(String token) {

            }

            @Override
            public void onDataNotAvailable(int code) {

            }

            @Override
            public void onConnectionError() {

            }
        }, getCode(textWithCode));
    }

    private String getCode(String text){
        Pattern code = Pattern.compile("code=(\\w{20})");
        Matcher m = code.matcher(text);
        if (m.find()){
           return m.group(1);
        }
        return "";
    }
}
