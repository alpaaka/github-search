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
                    dataSource.findRep(new LoadRepositoriesCallback() {
                        @Override
                        public void onRepositoriesLoaded(ArrayList<Repository> list, boolean refresh) {
                            callback.onRepositoriesLoaded(list, true);
                        }

                        @Override
                        public void onDataNotAvailable(int code) {
                            callback.onDataNotAvailable(code);
                        }

                        @Override
                        public void onConnectionError() {
                            callback.onConnectionError();
                        }
                    }, q, 1);
                    previousRequest = q;
                    count = 1;
                } else {
                    dataSource.findRep(new LoadRepositoriesCallback() {
                        @Override
                        public void onRepositoriesLoaded(ArrayList<Repository> list, boolean refresh) {
                            callback.onRepositoriesLoaded(list, false);
                        }

                        @Override
                        public void onDataNotAvailable(int code) {
                            callback.onDataNotAvailable(code);
                        }

                        @Override
                        public void onConnectionError() {
                            callback.onConnectionError();
                        }
                    }, q, ++count);
                }
            }
        }, 400);
    }

    @Override
    public void loadMore(final LoadRepositoriesCallback callback) {
        dataSource.findRep(new LoadRepositoriesCallback() {
            @Override
            public void onRepositoriesLoaded(ArrayList<Repository> list, boolean refresh) {
                callback.onRepositoriesLoaded(list, false);
            }

            @Override
            public void onDataNotAvailable(int code) {
                callback.onDataNotAvailable(code);
            }

            @Override
            public void onConnectionError() {
                callback.onConnectionError();
            }
        }, previousRequest, ++count);
    }
}
