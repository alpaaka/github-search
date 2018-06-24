package alpaaka.ru.gsearch.business.search;

import android.os.Handler;

import javax.inject.Inject;

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
                if (!q.equals(previousRequest)){
                    dataSource.findRep(callback, q, 0);
                    previousRequest = q;
                } else {
                    dataSource.findRep(callback, q, ++count);
                }
            }
        }, 200);
    }
}
