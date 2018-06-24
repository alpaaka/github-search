package alpaaka.ru.gsearch.presentation.presenter.contentactivity;

import alpaaka.ru.gsearch.BasePresenter;
import alpaaka.ru.gsearch.BaseView;

public class ContentActivityContract {

    public interface View extends BaseView<Presenter> {
        void showProgress(boolean visibility);
        void showInfoMessage(int code);
    }

    public interface Presenter extends BasePresenter<View>{
        void loadToken(String code);
    }
}
