package alpaaka.ru.gsearch.di;

import android.app.Application;
import android.content.Context;

import alpaaka.ru.gsearch.sharedpref.ISharedPreferencesRepository;
import alpaaka.ru.gsearch.sharedpref.SharedPreferencesRepositoryImpl;
import dagger.Binds;
import dagger.Module;

@Module
abstract class ApplicationModule {

    @Binds
    abstract Context context(Application application);

    @Binds
    abstract ISharedPreferencesRepository bindSharedPreferencesRepository(SharedPreferencesRepositoryImpl impl);
}
