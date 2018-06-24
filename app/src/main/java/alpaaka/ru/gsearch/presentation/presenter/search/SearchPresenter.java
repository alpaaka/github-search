package alpaaka.ru.gsearch.presentation.presenter.search;

import java.util.ArrayList;

import javax.inject.Inject;

import alpaaka.ru.gsearch.business.search.ISearchInteractor;
import alpaaka.ru.gsearch.data.model.Repository;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    @Inject
    ISearchInteractor interactor;

    @Inject
    SearchPresenter() {

    }

    @Override
    public void takeView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void searchReps(final String q) {
        view.showProgress(true);
        interactor.findRepositories(new ISearchInteractor.LoadRepositoriesCallback() {
            @Override
            public void onRepositoriesLoaded(ArrayList<Repository> list, boolean refresh) {
                if (view != null){
                    view.showProgress(false);
                    view.dataLoaded(list, refresh);
                }
            }

            @Override
            public void onDataNotAvailable(int code) {
                if (view != null){
                    view.showProgress(false);
                }
            }

            @Override
            public void onConnectionError() {
                if (view != null){
                    view.showProgress(false);
                }
            }
        }, q);
    }

    @Override
    public void loadMore() {
        view.showProgress(true);
        interactor.loadMore(new ISearchInteractor.LoadRepositoriesCallback() {
            @Override
            public void onRepositoriesLoaded(ArrayList<Repository> list, boolean refresh) {
                if (view != null){
                    view.showProgress(false);
                    view.dataLoaded(list, refresh);
                }
            }

            @Override
            public void onDataNotAvailable(int code) {
                if (view != null){
                    view.showProgress(false);
                }
            }

            @Override
            public void onConnectionError() {
                if (view != null){
                    view.showProgress(false);
                }
            }
        });
    }
}
