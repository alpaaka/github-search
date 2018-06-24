package alpaaka.ru.gsearch.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import dagger.android.support.DaggerAppCompatActivity;

public class ContentActivity extends DaggerAppCompatActivity{

    @Inject
    IAuthInteractor authInteractor;

    @Inject
    public ContentActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null && getIntent().getData() != null){
            authInteractor.loadToken(getIntent().getDataString());
        }
    }
}
