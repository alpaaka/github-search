package alpaaka.ru.gsearch.di.modules;

import alpaaka.ru.gsearch.business.auth.AuthInteractor;
import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import alpaaka.ru.gsearch.di.ActivityScoped;
import alpaaka.ru.gsearch.presentation.presenter.contentactivity.ContentActivityContract;
import alpaaka.ru.gsearch.presentation.presenter.contentactivity.ContentPresenter;
import alpaaka.ru.gsearch.ui.activities.ContentActivity;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ContentActivityModule {

    @ActivityScoped
    @Binds
    abstract IAuthInteractor provideAuthInteractor(AuthInteractor authInteractor);

    @ActivityScoped
    @Binds
    abstract ContentActivityContract.Presenter providePresenter(ContentPresenter presenter);


}
