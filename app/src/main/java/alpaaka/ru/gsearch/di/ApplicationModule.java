package alpaaka.ru.gsearch.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ApplicationModule {

    @Binds
    abstract Context context(Application application);
}
