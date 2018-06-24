package alpaaka.ru.gsearch;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();
}
