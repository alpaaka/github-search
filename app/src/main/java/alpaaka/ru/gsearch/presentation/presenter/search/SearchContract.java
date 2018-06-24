package alpaaka.ru.gsearch.presentation.presenter.search;

import java.util.ArrayList;

import alpaaka.ru.gsearch.BasePresenter;
import alpaaka.ru.gsearch.BaseView;
import alpaaka.ru.gsearch.data.model.Repository;

public class SearchContract {

    public interface View extends BaseView<Presenter> {
        void dataLoaded(ArrayList<Repository> list, boolean refresh);
        void showProgress(boolean progress);
    }

    public interface Presenter extends BasePresenter<View>{
        void searchReps(String q);
        void loadMore();
    }
}
