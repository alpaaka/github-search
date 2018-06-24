package alpaaka.ru.gsearch.presentation.presenter.contentactivity;

import javax.inject.Inject;

import alpaaka.ru.gsearch.business.auth.IAuthInteractor;

public class ContentPresenter implements ContentActivityContract.Presenter {

    @Inject
    IAuthInteractor authInteractor;
    private ContentActivityContract.View view;

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

    @Override
    public void takeView(ContentActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
