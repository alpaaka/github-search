package alpaaka.ru.gsearch.data.source.network.api;

import alpaaka.ru.gsearch.business.search.ISearchInteractor;

public interface IApiSource {

    void findRep(ISearchInteractor.LoadRepositoriesCallback callback, String q, int page);
}
