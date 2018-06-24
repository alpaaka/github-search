package alpaaka.ru.gsearch.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

@Singleton
public class AppExecutor {

    private final Executor mainThread;

    public AppExecutor(Executor mainThread) {
        this.mainThread = mainThread;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
