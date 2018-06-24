package alpaaka.ru.gsearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryResponse {

    @SerializedName("total_count")
    private long count;
    @SerializedName("incomplete_results")
    private boolean inResult;
    @SerializedName("items")
    private List<Repository> items;

    public long getCount() {
        return count;
    }

    public boolean isInResult() {
        return inResult;
    }

    public List<Repository> getItems() {
        return items;
    }
}
