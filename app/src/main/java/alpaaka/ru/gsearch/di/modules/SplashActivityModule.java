package alpaaka.ru.gsearch.di.modules;

import alpaaka.ru.gsearch.di.ActivityScoped;
import alpaaka.ru.gsearch.presentation.presenter.splash.SplashContract;
import alpaaka.ru.gsearch.presentation.presenter.splash.SplashPresenter;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class SplashActivityModule {

    @ActivityScoped
    @Binds
    abstract SplashContract.Presenter providePresenter(SplashPresenter presenter);

}
