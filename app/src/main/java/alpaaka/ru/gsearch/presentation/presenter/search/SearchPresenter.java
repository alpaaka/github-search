package alpaaka.ru.gsearch.presentation.presenter.search;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    @Inject
    public SearchPresenter() {
    }

    @Override
    public void takeView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
