package alpaaka.ru.gsearch.presentation.presenter.contentactivity;

import alpaaka.ru.gsearch.BasePresenter;
import alpaaka.ru.gsearch.BaseView;

public class ContentActivityContract {

    public interface View extends BaseView<Presenter> {
        void showProgress(boolean visibility);
        void showInfoMessage(int code);
        void inflateOptionsMenu(int code);
        void showLoginPage();
    }

    public interface Presenter extends BasePresenter<View>{
        void loadToken(String code);
        void drawProfileButton();
        void onActionProfileClick();
    }
}
