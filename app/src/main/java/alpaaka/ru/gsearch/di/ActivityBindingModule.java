package alpaaka.ru.gsearch.di;

import alpaaka.ru.gsearch.di.modules.ContentActivityModule;
import alpaaka.ru.gsearch.ui.activities.ContentActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = ContentActivityModule.class)
    abstract ContentActivity contentActivity();
}
