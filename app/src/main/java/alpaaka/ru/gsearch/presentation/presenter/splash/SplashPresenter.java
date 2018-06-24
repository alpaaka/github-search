package alpaaka.ru.gsearch.presentation.presenter.splash;

import javax.inject.Inject;

import alpaaka.ru.gsearch.sharedpref.ISharedPreferencesRepository;

public class SplashPresenter implements SplashContract.Presenter {

    @Inject
    ISharedPreferencesRepository preferencesRepository;
    private SplashContract.View view;

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void checkToken() {
        if (!preferencesRepository.getToken().isEmpty()) {
            if (view != null) {
                view.goToContent();
            }
        } else {
            if (view != null) {
                view.goToLogin();
            }
        }
    }

    @Override
    public void takeView(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
