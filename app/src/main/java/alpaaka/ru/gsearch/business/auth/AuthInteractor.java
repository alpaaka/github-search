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
    AuthInteractor() {

    }

    @Override
    public void loadToken(LoadTokenCallback callback, String textWithCode) {
        dataSource.getAuthToken(callback, getCode(textWithCode));
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
