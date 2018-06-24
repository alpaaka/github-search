package alpaaka.ru.gsearch.data.model;

import com.google.gson.annotations.SerializedName;

public class AuthToken {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String type;

    public AuthToken(String accessToken, String type) {
        this.accessToken = accessToken;
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getType() {
        return type;
    }
}
