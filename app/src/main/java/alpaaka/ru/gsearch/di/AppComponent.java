package alpaaka.ru.gsearch.di;

import android.app.Application;

import javax.inject.Singleton;

import alpaaka.ru.gsearch.App;
import alpaaka.ru.gsearch.data.source.NetworkModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {NetworkModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
