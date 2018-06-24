package alpaaka.ru.gsearch.business.search;

import java.util.ArrayList;

import alpaaka.ru.gsearch.data.model.Repository;

public interface ISearchInteractor {

    interface LoadRepositoriesCallback {

        void onRepositoriesLoaded(ArrayList<Repository> list);

        void onDataNotAvailable(int code);

        void onConnectionError();
    }

    void findRepositories(LoadRepositoriesCallback callback, String q);
}
