package alpaaka.ru.gsearch.business.search;

import android.os.Handler;

import java.util.ArrayList;

import javax.inject.Inject;

import alpaaka.ru.gsearch.data.model.Repository;
import alpaaka.ru.gsearch.data.source.DataSource;

public class SearchInteractor implements ISearchInteractor {

    @Inject
    DataSource dataSource;
    private Handler handler;
    private int count;
    private String previousRequest;

    @Inject
    SearchInteractor() {
        handler = new Handler();
    }

    @Override
    public void findRepositories(final LoadRepositoriesCallback callback, final String q) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!q.equals(previousRequest)) {
                    count = 1;
                    findRep(callback, q,
                            true, count);
                    previousRequest = q;
                } else {
                    findRep(callback, q,
                            false, ++count);
                }
            }
        }, 400);
    }

    @Override
    public void loadMore(final LoadRepositoriesCallback callback) {
        findRep(callback, previousRequest, false, ++count);
    }

    private void findRep(final LoadRepositoriesCallback callback, String q,
                         final boolean needRefresh,
                         int page){
        dataSource.findRep(new LoadRepositoriesCallback() {
            @Override
            public void onRepositoriesLoaded(ArrayList<Repository> list, boolean refresh) {
                callback.onRepositoriesLoaded(list, needRefresh);
            }

            @Override
            public void onDataNotAvailable(int code) {
                callback.onDataNotAvailable(code);
            }

            @Override
            public void onConnectionError() {
                callback.onConnectionError();
            }
        }, q, page);
    }
}
