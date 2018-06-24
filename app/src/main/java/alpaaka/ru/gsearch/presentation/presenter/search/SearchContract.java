package alpaaka.ru.gsearch.presentation.presenter.search;

import alpaaka.ru.gsearch.BasePresenter;
import alpaaka.ru.gsearch.BaseView;

public class SearchContract {

    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter<View>{

    }
}
