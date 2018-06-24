package alpaaka.ru.gsearch.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import alpaaka.ru.gsearch.presentation.presenter.splash.SplashContract;
import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity implements SplashContract.View {

    private static final int LOGIN = 0;
    private static final int CONTENT = 1;

    @Inject
    SplashContract.Presenter presenter;

    public SplashActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.takeView(this);
        if (presenter != null) {
            presenter.checkToken();
        }
    }

    @Override
    protected void onStop() {
        presenter.dropView();
        super.onStop();
    }

    @Override
    public void goToLogin() {
        goToScreen(LOGIN);
    }

    @Override
    public void goToContent() {
        goToScreen(CONTENT);
    }

    private void goToScreen(int code) {
        Intent i;
        if (code == LOGIN){
            i = new Intent(SplashActivity.this, LoginActivity.class);
        } else {
            i = new Intent(SplashActivity.this, ContentActivity.class);
        }
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }
}
