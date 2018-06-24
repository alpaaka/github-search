package alpaaka.ru.gsearch.presentation.presenter.contentactivity;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import alpaaka.ru.gsearch.business.auth.IAuthInteractor;

public class ContentPresenter implements ContentActivityContract.Presenter {

    @Inject
    IAuthInteractor authInteractor;
    private ContentActivityContract.View view;
    private boolean hasToken;

    @Inject
    ContentPresenter() {
    }

    @Override
    public void loadToken(String code) {
        if (view != null){
            view.showProgress(true);
        }
        authInteractor.loadToken(new IAuthInteractor.LoadTokenCallback() {
            @Override
            public void onTokenLoaded(String token) {
                if (view != null){
                    view.showProgress(false);
                    view.showInfoMessage(0);
                    view.inflateOptionsMenu(1);
                }
            }

            @Override
            public void onDataNotAvailable(int code) {
                if (view != null){
                    view.showProgress(false);
                    view.showInfoMessage(code);
                }
            }

            @Override
            public void onConnectionError() {
                if (view != null){
                    view.showProgress(false);
                    view.showInfoMessage(-1);
                }
            }
        }, code);
    }

    /**
     * Если токен уже есть то рисуем кнопку выхода
     * в другом случае отрисовываем кнопку логина
     */
    @Override
    public void drawProfileButton() {
        this.hasToken = authInteractor.checkToken();
        if (hasToken){
            if (view != null) {
                view.inflateOptionsMenu(1);
            }
        } else {
            if (view != null) {
                view.inflateOptionsMenu(0);
            }
        }
    }

    @Override
    public void onActionProfileClick() {
        if (hasToken){
            hasToken = false;
            if (view != null) {
                view.inflateOptionsMenu(0);
            }
            authInteractor.removeToken();
        } else {
            if (view != null) {
                view.showLoginPage();
            }
        }
    }


    @Override
    public void takeView(@NonNull ContentActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
