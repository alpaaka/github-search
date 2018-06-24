package alpaaka.ru.gsearch.presentation.view.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import alpaaka.ru.gsearch.R;
import alpaaka.ru.gsearch.data.model.Repository;
import alpaaka.ru.gsearch.presentation.presenter.search.SearchContract;
import alpaaka.ru.gsearch.ui.adapters.RepositoriesRecyclerViewAdapter;

public class SearchFragment extends Fragment implements SearchContract.View,
        SearchView.OnQueryTextListener {

    @Inject
    SearchContract.Presenter presenter;
    private RepositoriesRecyclerViewAdapter adapter;

    @Inject
    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.search_result);
        adapter = new RepositoriesRecyclerViewAdapter(getContext(), new ArrayList<Repository>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),
                layoutManager.getOrientation()));
        recyclerView.setAdapter(adapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onStop() {
        presenter.dropView();
        super.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView sV = (SearchView) item.getActionView();
        sV.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (!s.isEmpty()) {
            presenter.searchReps(s);
        }
        return true;
    }

    @Override
    public void dataLoaded(ArrayList<Repository> list) {
        adapter.loadData(list);
    }
}
