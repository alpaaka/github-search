package alpaaka.ru.gsearch.di;

import alpaaka.ru.gsearch.di.modules.ContentActivityModule;
import alpaaka.ru.gsearch.di.modules.SplashActivityModule;
import alpaaka.ru.gsearch.ui.activities.ContentActivity;
import alpaaka.ru.gsearch.ui.activities.SplashActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ContentActivityModule.class)
    abstract ContentActivity contentActivity();
}
