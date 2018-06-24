package alpaaka.ru.gsearch.data.source.network.api;

import java.util.ArrayList;

import javax.inject.Inject;

import alpaaka.ru.gsearch.business.search.ISearchInteractor;
import alpaaka.ru.gsearch.data.model.Repository;
import alpaaka.ru.gsearch.data.model.RepositoryResponse;
import alpaaka.ru.gsearch.utils.AppExecutor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiSource implements IApiSource{

    @Inject
    ApiService apiService;
    private AppExecutor appExecutor;

    @Inject
    ApiSource(AppExecutor appExecutor) {
        this.appExecutor = appExecutor;
    }

    @Override
    public void findRep(final ISearchInteractor.LoadRepositoriesCallback callback, String q, int page) {
        Call<RepositoryResponse> call = apiService.findRepositories(q, page);
        call.enqueue(new Callback<RepositoryResponse>() {
            @Override
            public void onResponse(Call<RepositoryResponse> call,
                                   final Response<RepositoryResponse> response) {
                if (response.isSuccessful()){
                    appExecutor.getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onRepositoriesLoaded(new ArrayList<Repository>(response.body()
                                            .getItems()),
                                    true);
                        }
                    });
                } else {
                    appExecutor.getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onDataNotAvailable(response.code());

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                appExecutor.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onConnectionError();

                    }
                });
            }
        });
    }
}
