package alpaaka.ru.gsearch.presentation.presenter.splash;

import alpaaka.ru.gsearch.BasePresenter;
import alpaaka.ru.gsearch.BaseView;

public class SplashContract {

    public interface View extends BaseView<Presenter> {
        void goToLogin();
        void goToContent();
    }

    public interface Presenter extends BasePresenter<View> {
        void checkToken();
    }
}
