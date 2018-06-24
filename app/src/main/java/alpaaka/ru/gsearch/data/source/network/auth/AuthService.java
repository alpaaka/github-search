package alpaaka.ru.gsearch.data.source.network.auth;

import alpaaka.ru.gsearch.data.model.AuthToken;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {

    @Headers("Accept:application/json")
    @POST("access_token")
    Call<AuthToken> getAccessToken(@Query("client_id") String clientId,
                                   @Query("client_secret") String clientSecret,
                                   @Query("code") String code);
}
