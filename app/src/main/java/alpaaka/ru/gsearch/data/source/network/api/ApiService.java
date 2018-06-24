package alpaaka.ru.gsearch.data.source.network.api;

import alpaaka.ru.gsearch.data.model.RepositoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/repositories")
    Call<RepositoryResponse> findRepositories(@Query("q") String q,
                                              @Query("page") int page);
}
