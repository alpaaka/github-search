package alpaaka.ru.gsearch.di.modules;

import alpaaka.ru.gsearch.business.auth.AuthInteractor;
import alpaaka.ru.gsearch.business.auth.IAuthInteractor;
import alpaaka.ru.gsearch.business.search.ISearchInteractor;
import alpaaka.ru.gsearch.business.search.SearchInteractor;
import alpaaka.ru.gsearch.di.ActivityScoped;
import alpaaka.ru.gsearch.di.FragmentScoped;
import alpaaka.ru.gsearch.presentation.presenter.contentactivity.ContentActivityContract;
import alpaaka.ru.gsearch.presentation.presenter.contentactivity.ContentPresenter;
import alpaaka.ru.gsearch.presentation.presenter.search.SearchContract;
import alpaaka.ru.gsearch.presentation.presenter.search.SearchPresenter;
import alpaaka.ru.gsearch.presentation.view.search.SearchFragment;
import alpaaka.ru.gsearch.ui.activities.ContentActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ContentActivityModule {

    @ActivityScoped
    @Binds
    abstract IAuthInteractor provideAuthInteractor(AuthInteractor authInteractor);

    @ActivityScoped
    @Binds
    abstract ISearchInteractor provideSearchInteractor(SearchInteractor searchInteractor);

    @ActivityScoped
    @Binds
    abstract ContentActivityContract.Presenter providePresenter(ContentPresenter presenter);

    @ActivityScoped
    @Binds
    abstract SearchContract.Presenter provideSearchPresenter(SearchPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchFragment imageViewerFragment();
}
